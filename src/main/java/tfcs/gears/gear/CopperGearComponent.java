package tfcs.gears.gear;

import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CopperGearComponent extends GearComponent {
	public CopperGearComponent() {
		this.setMaxRotation(0.5);
		this.setMinTorque(2);
		this.setToothSize(5);
		this.setGearSubDivid(10);
		this.setDepth(0.15);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.COPPER_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_COPPER_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.copperGear,1,(int)this.getSize()-1);
	}
}
