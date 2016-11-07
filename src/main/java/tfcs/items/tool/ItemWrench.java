package tfcs.items.tool;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tfcs.core.TFCSBlocks;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.items.ItemTFCS;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.tileentities.TileEntityLauncherBase;
import tfcs.util.ItemStackHelper;

public class ItemWrench extends ItemTFCS {
	public ItemWrench() {
		this.setUnlocalizedName(ReferenceName.ITEM_WRENCH_NAME);
		this.setMaxDamage(100);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_GEARS);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int meta, float xCoord, float yCoord, float zCoord) {
		TileEntityGearFrame tileentity = (TileEntityGearFrame) world.getTileEntity(x, y, z);
		if (tileentity != null) {
			if (tileentity instanceof TileEntityGearFrame) {
				if (tileentity instanceof TileEntityLauncherBase) {
					if (player.isSneaking()) {
						if (((TileEntityLauncherBase) tileentity).hasComponent()) {
							((TileEntityLauncherBase) tileentity).getComponent().drop(player, tileentity);
							((TileEntityLauncherBase) tileentity).removeComponent();
						} else {
							ItemStackHelper.dropItemStackInWorld(world, x, y, z, new ItemStack(TFCSBlocks.launcherBase));
							world.setBlockToAir(x, y, z);
						}
					}
				} else {
					if (!player.isSneaking()) {
						switch ((int) tileentity.getAxeLocation()) {
						case -1:
							tileentity.setAxeLocation(0);
							break;
						case 0:
							tileentity.setAxeLocation(1);
							break;
						case 1:
							tileentity.setAxeLocation(-1);
							break;
						default:
							break;
						}
					} else {
						if (tileentity.getHasTool()) {
							tileentity.getToolComponent().drop(player, tileentity);
						} else if (tileentity.getHasGear()) {
							tileentity.getGearComponent().drop(player, tileentity);
						} else if (tileentity.getHasAxle()) {
							tileentity.getAxleComponent().drop(player, tileentity);
						} else {
							ItemStackHelper.dropItemStackInWorld(world, x, y, z, new ItemStack(TFCSBlocks.gearFrame));
							world.setBlockToAir(x, y, z);
						}
					}
				}
			}
		} else {
			Block b = world.getBlock(x, y, z);
			player.addChatComponentMessage(new ChatComponentText("Localized Name: " + b.getLocalizedName()));
			player.addChatComponentMessage(new ChatComponentText("Unlocalized Name: " + b.getUnlocalizedName()));
			player.addChatComponentMessage(new ChatComponentText("Hardness Name: " + b.getBlockHardness(world, x, y, z)));
			player.addChatComponentMessage(new ChatComponentText("Resistance Name: " + b.getExplosionResistance(((Entity) player))));
		}
		itemstack.damageItem(1, player);
		return false;
	}

}
