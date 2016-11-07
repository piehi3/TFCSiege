package tfcs.gears.gear;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class StoneGearComponent extends GearComponent{
	public StoneGearComponent() {
		this.setMaxRotation(0.3);
		this.setMinTorque(2);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.STONE_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_STONE_GEAR_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.stoneGear,1,(int)this.getSize()-1);
	}
}
