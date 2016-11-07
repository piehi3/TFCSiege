package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class IronCoilCompenent extends CoilToolComponent {
	public IronCoilCompenent() {
		setCoilResistance(2);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "iron";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.IRON_COMPONENET_TEXTURE;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.ironCoil);
	}
	
}
