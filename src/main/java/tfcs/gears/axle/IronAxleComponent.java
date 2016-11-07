package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class IronAxleComponent extends AxleComponent {
	public IronAxleComponent() {
		this.setMaxRotation(70);
		this.setMinTorque(1.2);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.IRON_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_IRON_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.ironAxle,1);
	}
}
