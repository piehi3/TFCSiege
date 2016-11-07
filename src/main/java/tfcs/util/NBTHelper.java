package tfcs.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

	private static void initNBTTagCompound(ItemStack itemstack) {
		if (itemstack.stackTagCompound == null) {
			itemstack.setTagCompound(new NBTTagCompound());
		}
	}

	public static Boolean hasKey(ItemStack itemstack, String key) {
		if (itemstack.hasTagCompound()) {
			return itemstack.stackTagCompound.hasKey(key);
		} else {
			return false;
		}
	}

	public static void setInt(ItemStack itemstack, String key, int value) {
		initNBTTagCompound(itemstack);
		itemstack.stackTagCompound.setInteger(key, value);
	}

	public static int getInt(ItemStack itemstack, String key) {
		initNBTTagCompound(itemstack);
		return itemstack.stackTagCompound.getInteger(key);
	}
	
	public static void setIntArray(ItemStack itemstack, String key, int[] value) {
		initNBTTagCompound(itemstack);
		itemstack.stackTagCompound.setIntArray(key, value);
	}
	
	public static int[] getIntArray(ItemStack itemstack, String key) {
		initNBTTagCompound(itemstack);
		return itemstack.stackTagCompound.getIntArray(key);
	}

	public static void setFloat(ItemStack itemstack, String key, float value) {
		initNBTTagCompound(itemstack);
		itemstack.stackTagCompound.setFloat(key, value);
	}

	public static float getFloat(ItemStack itemstack, String key) {
		initNBTTagCompound(itemstack);
		return itemstack.stackTagCompound.getFloat(key);
	}

	public static void setString(ItemStack itemstack, String key, String value) {
		initNBTTagCompound(itemstack);
		itemstack.stackTagCompound.setString(key, value);
	}

	public static String getString(ItemStack itemstack, String key) {
		initNBTTagCompound(itemstack);
		return itemstack.stackTagCompound.getString(key);
	}

	public static void setBoolean(ItemStack itemstack, String key, Boolean value) {
		initNBTTagCompound(itemstack);
		itemstack.stackTagCompound.setBoolean(key, value);
	}

	public static Boolean getBoolean(ItemStack itemstack, String key) {
		initNBTTagCompound(itemstack);
		return itemstack.stackTagCompound.getBoolean(key);
	}
}
