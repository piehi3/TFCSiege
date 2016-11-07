package tfcs.blocks.devices;

import com.bioxx.tfc.api.TFCBlocks;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.items.ItemProjectile;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityMortar;
import tfcs.util.ItemStackHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockMortar extends BlockTFCSContainer {

	public BlockMortar() {
		super(Material.rock);
		this.setBlockName(ReferenceName.BlOCK_MORTAR_NAME);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.5F);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMortar();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xCoord, float yCoord, float zCoord) {
		TileEntityMortar tileentity = (TileEntityMortar) world.getTileEntity(x, y, z);
		if (tileentity != null) {
			ItemStack itemstack = player.getCurrentEquippedItem();
			if (itemstack == null) {
				switch (side) {
				case 5:
					tileentity.addYaw(1);
					break;
				case 4:
					tileentity.addYaw(-1);
					break;
				case 3:
					tileentity.addPitch(-1);
					break;
				case 2:
					tileentity.addPitch(1);
					break;
				default:
					break;
				}
			} else if (itemstack.getItem() == Item.getItemFromBlock(TFCBlocks.torch)) {
				tileentity.setSohuldFire(true);
			} else if (itemstack.getItem() == Items.gunpowder) {
				if (tileentity.addGunPowder(1)) {
					ItemStackHelper.decreaseItemStackFromPlayer(itemstack, player);
				} else {
					player.addChatMessage(new ChatComponentText("I'm full"));
				}
			} else if (itemstack.getItem() instanceof ItemProjectile) {
				tileentity.setPogectile(((ItemProjectile) itemstack.getItem()).getProjectile(world));
			}
		}
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z) == TFCBlocks.dirt || world.getBlock(x, y - 1, z) == TFCBlocks.dirt2 || world.getBlock(x, y - 1, z) == TFCBlocks.sand
				|| world.getBlock(x, y - 1, z) == TFCBlocks.sand2 || world.getBlock(x, y - 1, z) == TFCBlocks.snow || world.getBlock(x, y - 1, z) == TFCBlocks.grass
				|| world.getBlock(x, y - 1, z) == TFCBlocks.grass2 || world.getBlock(x, y - 1, z) == TFCBlocks.gravel || world.getBlock(x, y - 1, z) == TFCBlocks.gravel2;
	}
}
