package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlueSteelAxleComponent extends AxleComponent {
	public BlueSteelAxleComponent() {
		this.setMaxRotation(100);
		this.setMinTorque(0.5);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLUE_STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BLUE_STEEL_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blueSteelAxle,1);
	}
}
