package tfcs.gears.gear;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;


public class BlueSteelGearComponent extends GearComponent {
	public BlueSteelGearComponent() {
		this.setMaxRotation(1);
		this.setMinTorque(1);
		this.setToothSize(6);
		this.setGearSubDivid(32);
		this.setDepth(0.05);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLUE_STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BLUE_STEEL_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blueSteelGear,1,(int)this.getSize()-1);
	}
}