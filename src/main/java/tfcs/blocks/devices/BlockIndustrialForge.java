package tfcs.blocks.devices;

import com.bioxx.tfc.api.TFCBlocks;

import tfcs.core.IndustrialFuelManager;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityIndustrialForge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockIndustrialForge extends BlockTFCSContainer {

	int industrialForgeMaxSize = 5;

	public BlockIndustrialForge() {
		super(Material.rock);
		this.setBlockName(ReferenceName.BlOCK_INDUSTRIAL_FORGE_NAME);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.5F);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		if (entityplayer.getCurrentEquippedItem() != null) {
			if (world.getTileEntity(x, y, z) instanceof TileEntityIndustrialForge) {
				ItemStack itemstack = entityplayer.getCurrentEquippedItem();
				if (IndustrialFuelManager.instance.hasItemKey(itemstack.getItem(), itemstack.getItemDamage())) {
					TileEntityIndustrialForge te = (TileEntityIndustrialForge) world.getTileEntity(x, y, z);
					te.addFuelToPile(IndustrialFuelManager.instance.getFrameComponentFromItem(itemstack.getItem(), itemstack.getItemDamage()));
				}
			}
		}
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return hasMuiltiBlockStructure(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityIndustrialForge();
	}

	private boolean hasMuiltiBlockStructure(World world, int x, int y, int z) {
		int size = getMultiBlockSize(world, x, y, z);
		if (size > 0) {
			for (int i = -(size - 1); i < size; i++)
				for (int k = -(size - 1); k < size; k++)
					if (!(world.getBlock(x + i, y, z + k).equals(TFCBlocks.fireBrick) || (i == 0 && k == 0)))
						return false;
			for (int i = -(size - 1); i < size; i++)
				if (!(world.getBlock(x + i, y + 1, z - size).equals(TFCBlocks.fireBrick)))
					return false;
			for (int i = -(size - 1); i < size; i++)
				if (!(world.getBlock(x + i, y + 1, z + size).equals(TFCBlocks.fireBrick)))
					return false;
			for (int i = -(size - 1); i < size; i++)
				if (!(world.getBlock(x - size, y + 1, z + i).equals(TFCBlocks.fireBrick)))
					return false;
			for (int i = -(size - 1); i < size; i++)
				if (!(world.getBlock(x + size, y + 1, z - i).equals(TFCBlocks.fireBrick)))
					return false;
			return true;
		}
		return false;
	}

	private int getMultiBlockSize(World world, int x, int y, int z) {
		for (int i = 1; i < industrialForgeMaxSize; i++)
			if (world.getBlock(x + i, y + 1, z).equals(TFCBlocks.fireBrick))
				return i;
		return 0;
	}

}
