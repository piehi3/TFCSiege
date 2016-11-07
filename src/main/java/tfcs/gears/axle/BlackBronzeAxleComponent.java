package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlackBronzeAxleComponent extends AxleComponent {
	public BlackBronzeAxleComponent() {
		this.setMaxRotation(70);
		this.setMinTorque(2);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_BRONZE_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BLACK_BRONZE_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackBronzeAxle,1);
	}
}
