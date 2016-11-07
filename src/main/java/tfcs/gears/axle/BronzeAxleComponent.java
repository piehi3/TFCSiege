package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BronzeAxleComponent extends AxleComponent {
	public BronzeAxleComponent() {
		this.setMaxRotation(60);
		this.setMinTorque(1.5);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BRONZE_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BRONZE_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.bronzeAxle,1);
	}
}
