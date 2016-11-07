package tfcs.gears.axle;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.AxleComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class StoneAxleComponent extends AxleComponent{
	public StoneAxleComponent() {
		this.setMaxRotation(40);
		this.setMinTorque(3);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.STONE_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_STONE_AXLE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.stoneAxle,1);
	}
}
