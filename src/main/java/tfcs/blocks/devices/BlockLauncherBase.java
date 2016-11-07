package tfcs.blocks.devices;

import com.bioxx.tfc.api.TFCBlocks;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.entity.ProjectileCannonBall;
import tfcs.items.ItemProjectile;
import tfcs.items.gears.ItemLauncherComponent;
import tfcs.items.gears.ItemToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityLauncherBase;
import tfcs.util.ItemStackHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockLauncherBase extends BlockTFCSContainer {

	public BlockLauncherBase() {
		super(Material.rock);
		this.setBlockName(ReferenceName.BlOCK_LAUNCHER_BASE_NAME);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.5F);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8F, 1.0F);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityLauncherBase();
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
		TileEntityLauncherBase tileentity = (TileEntityLauncherBase) world.getTileEntity(x, y, z);
		if (tileentity != null) {
			ItemStack itemstack = player.getCurrentEquippedItem();
			if (itemstack != null) {
				if (itemstack.getItem() == Item.getItemFromBlock(TFCBlocks.torch)) {
					tileentity.light();
				} else if (itemstack.getItem() == Items.gunpowder) {
					if (tileentity.addGunPowder(1)) {
						ItemStackHelper.decreaseItemStackFromPlayer(itemstack, player);
					} else {
						// player.addChatMessage(new
						// ChatComponentText("I'm full"));
					}
				} else if (itemstack.getItem() instanceof ItemProjectile) {
					if (((ItemProjectile) itemstack.getItem()).getProjectile(world) instanceof ProjectileCannonBall)
						if (tileentity.setPogectile(((ItemProjectile) itemstack.getItem()).getProjectile(world)))
							ItemStackHelper.decreaseItemStackFromPlayer(itemstack, player);
				} else if (itemstack.getItem() instanceof ItemLauncherComponent) {
					if (tileentity.setComponent(((ItemLauncherComponent) itemstack.getItem()).getFrameComponent() + "_" + (itemstack.getItemDamage() + 1)))
						ItemStackHelper.decreaseItemStackFromPlayer(itemstack, player);
				}
			} else {
				if (player.isSneaking()) {
					tileentity.addPitch(-1);
				} else {
					tileentity.addPitch(1);
				}
			}
		}
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return !world.isAirBlock(x, y - 1, z);
	}

}
