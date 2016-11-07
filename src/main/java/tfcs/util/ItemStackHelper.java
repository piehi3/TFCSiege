package tfcs.util;

import tfcs.items.tool.ItemLauncher;
import tfcs.items.tool.ItemTemperedBroadsword;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemStackHelper {
	public static void decreaseItemStackFromPlayer(ItemStack itemstack, EntityPlayer player) {
		if (itemstack != null) {
			if (!player.capabilities.isCreativeMode) {
				--itemstack.stackSize;
				if (itemstack.stackSize <= 0) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
				}
			}
		}
	}
	public static void dropItemStackInWorld(World world,int x,int y,int z,ItemStack itemstack) {
	  float f = 0.7F;
      double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
      double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
      double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
      EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, itemstack);
      entityitem.delayBeforeCanPickup = 10;
      if(!world.isRemote)
    	  world.spawnEntityInWorld(entityitem);
	}
	
	public static boolean isWeapon(ItemStack is){
		if(is==null)
			return false;
		if(is.getItem() instanceof ItemSword)
			return true;
		if(is.getItem() instanceof ItemTemperedBroadsword)
			return true;
		if(is.getItem() instanceof ItemLauncher)
			return true;
		if(is.getItem() instanceof ItemBow)
			return true;
		return false;
	}
	
	public static boolean isWeaponOrTool(ItemStack is){
		return isWeapon(is) || (is!=null&&is.getItem() instanceof ItemTool);
	}
}
