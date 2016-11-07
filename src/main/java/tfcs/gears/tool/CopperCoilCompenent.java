package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class CopperCoilCompenent extends CoilToolComponent {

	public CopperCoilCompenent() {
		setCoilResistance(1.1);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "copper";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.COPPER_COMPONENET_TEXTURE;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.copperCoil);
	}
	
}
