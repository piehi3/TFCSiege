package tfcs.gears.gear;

import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WoodenGearComponent extends GearComponent{
	public WoodenGearComponent() {
		this.setMaxRotation(0.6);
		this.setMinTorque(1.5);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.WOODEN_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_WOODEN_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.woodenGear,1,(int)this.getSize()-1);
	}
}
