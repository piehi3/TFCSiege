package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class SteelAxleComponent extends AxleComponent {
	public SteelAxleComponent() {
		this.setMaxRotation(80);
		this.setMinTorque(1.1);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_STEEL_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.steelAxle,1);
	}
}
