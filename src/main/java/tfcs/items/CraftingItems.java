package tfcs.items;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;

public class CraftingItems extends ItemTFCS{
public CraftingItems(String type,String material ){
	this.setUnlocalizedName(ReferenceName.ITEM_CRAFTING_NAME + "." + type +"."+ material);
	this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
	
}

}
