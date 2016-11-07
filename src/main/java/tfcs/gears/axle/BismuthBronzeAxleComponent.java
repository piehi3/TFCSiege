package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BismuthBronzeAxleComponent extends AxleComponent {
	public BismuthBronzeAxleComponent() {
		this.setMaxRotation(55);
		this.setMinTorque(1.2);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BISMUTH_BRONZE_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BISMUTH_BRONZE_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.bismuthBronzeAxle,1);
	}
}
