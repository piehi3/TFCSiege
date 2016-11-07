package tfcs.blocks.devices;

import java.util.List;
import java.util.Random;

import com.bioxx.tfc.TileEntities.TEForge;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;

import tfcs.core.TFCSBlocks;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityMultiFuel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMultiFuel extends BlockTFCSContainer {

	boolean isLit;

	public BlockMultiFuel(Boolean isLit) {
		super(Material.ground);
		this.isLit = isLit;
		this.setBlockName(ReferenceName.BlOCK_MULTI_FUEL_NAME + (isLit == true ? "ON" : "OFF"));
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.5F);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setTickRandomly(true);
		if (isLit)
			this.setLightLevel(0.7F);
	}
	
	 @Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMultiFuel();
	}
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
		return false;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 8; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int md = world.getBlockMetadata(x, y, z) + 1;

		if (md == 8)
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);

		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + (0.125f * md), z + 1);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z) {
		int meta = bAccess.getBlockMetadata(x, y, z) + 1;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125f * meta, 1.0F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		if (entityplayer.getCurrentEquippedItem() != null)
			if (entityplayer.getCurrentEquippedItem().getItem().equals(TFCItems.flintSteel)) {
				lightFuelBlock(world, x, y, z, world.getBlockMetadata(x, y, z));
				entityplayer.getCurrentEquippedItem().damageItem(1, entityplayer);
				return true;
			}
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (this.isLit) {
			switch (rand.nextInt(4)) {
			case 0:
				if (world.getBlock(x + 1, y, z).equals(TFCSBlocks.multiFuelOFF))
					lightFuelBlock(world, x + 1, y, z, world.getBlockMetadata(x + 1, y, z));
				break;
			case 1:
				if (world.getBlock(x - 1, y, z).equals(TFCSBlocks.multiFuelOFF))
					lightFuelBlock(world, x - 1, y, z, world.getBlockMetadata(x - 1, y, z));
				break;
			case 2:
				if (world.getBlock(x, y, z + 1).equals(TFCSBlocks.multiFuelOFF))
					lightFuelBlock(world, x, y, z + 1, world.getBlockMetadata(x, y, z + 1));
				break;
			case 3:
				if (world.getBlock(x, y, z - 1).equals(TFCSBlocks.multiFuelOFF))
					lightFuelBlock(world, x, y, z - 1, world.getBlockMetadata(x, y, z - 1));
				break;
			default:
				break;
			}
		}
	}

	private void lightFuelBlock(World world, int x, int y, int z,int meta) {
		NBTTagCompound tag = new NBTTagCompound();
		((TileEntityMultiFuel)world.getTileEntity(x, y, z)).writeToNBT(tag);
		world.setBlock(x, y, z, TFCSBlocks.multiFuelON, meta, 2);
		((TileEntityMultiFuel)world.getTileEntity(x, y, z)).readFromNBT(tag);
	}
	
	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		if (!this.isLit) {
			return;
		} else {
			float md = 1 - ((world.getBlockMetadata(i, j, k) + 1) * 0.125F);
			float f = i + 0.5F;
			float f1 = j + 0.9F + random.nextFloat() * 6F / 16F;
			float f2 = k + 0.5F;
			// float f3 = 0.52F;
			float f4 = random.nextFloat() * 0.6F;
			float f5 = random.nextFloat() * -0.6F;
			float f6 = random.nextFloat() * -0.6F;
			world.spawnParticle("smoke", f + f4 - 0.3F, f1 - md, f2 + f5 + 0.3F, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f4 - 0.3F, f1 - md, f2 + f5 + 0.3F, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("smoke", f + f5 + 0.3F, f1 - md, f2 + f4 - 0.3F, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f5 + 0.3F, f1 - md, f2 + f4 - 0.3F, 0.0D, 0.0D, 0.0D);
		}
	}

}
