package tfcs.util;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class BlockHelper {

	public static int shiftCoord(double d) {
		return d < 0 ? ((int) d) - 1 : (int) d;
	}

	public static boolean doesBlockHaveGunPowder(IGunpowder te) {
		return te.getGunpowderStorage() > 0;
	}

	public static double getRenderGunpowder(IGunpowder te, double scale) {
		return scale * (((double) te.getGunpowderStorage()) / ((double) te.getMaxGunpowderStorage()));
	}

	public static boolean addGunPowder(IGunpowder te, ItemStack itemstack) {
		if (itemstack.getItem().equals(Items.gunpowder)&&!te.isClosed()){
			if (te.getGunpowderStorage() < te.getMaxGunpowderStorage()) {
				te.setGunpowder(te.getGunpowder() + 0.2F);
				te.setGunpowderStorage(te.getGunpowderStorage() + 1);
				te.updateBlock();
				return true;
			}
		}
		return false;
	}

	public static void light(Entity shooter, IGunpowder te, TileEntity tileentity) {
		te.setShooter(shooter);
		if (te.getFuse() == te.getMaxFuse()) {
			if (BlockHelper.doesBlockHaveGunPowder(te)) {
				if (te.isClosed()) {
					te.setFuse(te.getFuse() - 1);
					tileentity.getWorldObj().playSoundEffect(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, "game.tnt.primed", (float) te.getFuse() - 1,
							(1.0F + (tileentity.getWorldObj().rand.nextFloat() - tileentity.getWorldObj().rand.nextFloat()) * 0.2F) * 1.0F);
					te.updateBlock();
				}
			}
		}
	}

}
