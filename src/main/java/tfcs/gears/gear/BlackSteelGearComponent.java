package tfcs.gears.gear;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;


public class BlackSteelGearComponent extends GearComponent {
	public BlackSteelGearComponent() {
		this.setMaxRotation(0.9);
		this.setMinTorque(1.1);
		this.setToothSize(6);
		this.setGearSubDivid(20);
		this.setDepth(0.1);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BLACK_STEEL_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackSteelGear,1,(int)this.getSize()-1);
	}
}
