package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlackSteelAxleComponent extends AxleComponent {
	public BlackSteelAxleComponent() {
		this.setMaxRotation(90);
		this.setMinTorque(1.01);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_STEEL_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BLACK_STEEL_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackSteelAxle,1);
	}
}
