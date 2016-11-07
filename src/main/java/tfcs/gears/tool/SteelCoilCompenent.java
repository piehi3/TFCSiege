package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class SteelCoilCompenent extends CoilToolComponent {
	public SteelCoilCompenent() {
		setCoilResistance(3);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "steel";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.steelCoil);
	}
	
}
