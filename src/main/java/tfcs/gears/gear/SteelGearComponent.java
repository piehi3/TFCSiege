package tfcs.gears.gear;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;


public class SteelGearComponent extends GearComponent {
	public SteelGearComponent() {
		this.setMaxRotation(0.8);
		this.setMinTorque(1.2);
		this.setToothSize(5);
		this.setGearSubDivid(20);
		this.setDepth(0.1);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_STEEL_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.steelGear,1,(int)this.getSize()-1);
	}
}
