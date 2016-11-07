package tfcs.gears.gear;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;


public class RedSteelGearComponent extends GearComponent {
	public RedSteelGearComponent() {
		this.setMaxRotation(1);
		this.setMinTorque(1);
		this.setToothSize(6);
		this.setGearSubDivid(32);
		this.setDepth(0.05);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.RED_STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_RED_STEEL_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.redSteelGear,1,(int)this.getSize()-1);
	}
}
