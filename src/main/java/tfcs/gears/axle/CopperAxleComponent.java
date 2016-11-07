package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class CopperAxleComponent extends AxleComponent {
	public CopperAxleComponent() {
		this.setMaxRotation(50);
		this.setMinTorque(2);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.COPPER_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_COPPER_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.copperAxle,1);
	}
}
