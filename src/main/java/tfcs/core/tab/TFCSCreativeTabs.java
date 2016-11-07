package tfcs.core.tab;

import java.util.List;

import tfcs.core.TFCSBlocks;
import tfcs.core.TFCSItems;
import tfcs.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TFCSCreativeTabs {
	public static final CreativeTabs TCFS_GEARS = new CreativeTabs(Reference.ModID) {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(TFCSBlocks.gearFrame);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.ModIDUpperCase + " Gears";
		}

		@SuppressWarnings("rawtypes")
		@SideOnly(Side.CLIENT)
		public void displayAllReleventItems(List itemList) {
			super.displayAllReleventItems(itemList);
			itemList.add(new ItemStack(TFCSItems.windmill, 1, 1));
			itemList.add(new ItemStack(TFCSItems.windmill, 1, 2));
			itemList.add(new ItemStack(TFCSItems.windmill, 1, 3));
			itemList.add(new ItemStack(TFCSItems.windmill, 1, 4));
			itemList.add(new ItemStack(TFCSItems.windmill, 1, 5));
			
			itemList.add(new ItemStack(TFCSItems.waterWheel, 1, 1));
			itemList.add(new ItemStack(TFCSItems.waterWheel, 1, 2));
			itemList.add(new ItemStack(TFCSItems.waterWheel, 1, 3));
			itemList.add(new ItemStack(TFCSItems.waterWheel, 1, 4));
			itemList.add(new ItemStack(TFCSItems.waterWheel, 1, 5));
			
			itemList.add(new ItemStack(TFCSItems.blueSteelGear, 1, 2));
			itemList.add(new ItemStack(TFCSItems.blueSteelGear, 1, 4));
		}
	};
	
	public static final CreativeTabs TCFS_SIEGE = new CreativeTabs(Reference.ModID) {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(TFCSBlocks.mortar);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.ModIDUpperCase + " Siege";
		}

		@SuppressWarnings("rawtypes")
		@SideOnly(Side.CLIENT)
		public void displayAllReleventItems(List itemList) {
			super.displayAllReleventItems(itemList);
		}
	};
	
	public static final CreativeTabs TCFS_OPTICS = new CreativeTabs(Reference.ModID) {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(TFCSBlocks.candleON);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.ModIDUpperCase + " Optics";
		}

		@SuppressWarnings("rawtypes")
		@SideOnly(Side.CLIENT)
		public void displayAllReleventItems(List itemList) {
			super.displayAllReleventItems(itemList);
		}
	};
	
	public static final CreativeTabs TCFS_CHEMISTRY = new CreativeTabs(Reference.ModID) {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(TFCSBlocks.mortar);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.ModIDUpperCase + " Chemistry";
		}

		@SuppressWarnings("rawtypes")
		@SideOnly(Side.CLIENT)
		public void displayAllReleventItems(List itemList) {
			super.displayAllReleventItems(itemList);
		}
	};
	
}
