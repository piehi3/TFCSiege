package tfcs.util;

import net.minecraft.item.ItemStack;

public class SharpnessHelper implements ISharpenable{
	
	public static boolean sharpen(ItemStack itemstack, int i) {
		if(NBTHelper.getInt(itemstack, "SHARPNESS")<=MAX_SHARPNESS)
			if(NBTHelper.getInt(itemstack, "SHARPNESS")+i<=MAX_SHARPNESS){
				NBTHelper.setInt(itemstack, "SHARPNESS", NBTHelper.getInt(itemstack, "SHARPNESS")+i);
			}else{
				NBTHelper.setInt(itemstack, "SHARPNESS", MAX_SHARPNESS);
			}
		return false;
	}

	public static boolean dull(ItemStack itemstack, int i) {
		if(NBTHelper.getInt(itemstack, "SHARPNESS")>0)
			if(NBTHelper.getInt(itemstack, "SHARPNESS")-i>=0){
				NBTHelper.setInt(itemstack, "SHARPNESS", NBTHelper.getInt(itemstack, "SHARPNESS")-i);
			}else{
				NBTHelper.setInt(itemstack, "SHARPNESS", 0);
			}
		return false;
	}
}
