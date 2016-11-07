package tfcs.gears.gear;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;


public class BronzeGearComponent extends GearComponent {
	public BronzeGearComponent() {
		this.setMaxRotation(0.6);
		this.setMinTorque(1.6);
		this.setToothSize(5);
		this.setGearSubDivid(16);
		this.setDepth(0.1);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BRONZE_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BRONZE_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.bronzeGear,1,(int)this.getSize()-1);
	}
}
