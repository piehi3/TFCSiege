package tfcs.items.gears;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;


public class ItemLauncherComponent extends ItemFrameComponent {
	String type;
	public ItemLauncherComponent(String type) {
		this.type = type;
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
		this.setUnlocalizedName(ReferenceName.ITEM_CANNON_BARREL_NAME+"."+type);
	}
	
	@Override
	public String getFrameComponent() {
		return ReferenceName.ITEM_CANNON_BARREL_NAME+"."+type;
	}
	
}
