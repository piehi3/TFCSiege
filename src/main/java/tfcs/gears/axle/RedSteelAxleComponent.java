package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class RedSteelAxleComponent extends AxleComponent {
	public RedSteelAxleComponent() {
		this.setMaxRotation(200);
		this.setMinTorque(1);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.RED_STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_RED_STEEL_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.redSteelAxle,1);
	}
}
