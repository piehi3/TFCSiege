package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class RedSteelCoilCompenent extends CoilToolComponent {
	public RedSteelCoilCompenent() {
		setCoilResistance(6);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "redSteel";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.RED_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.redSteelCoil);
	}
	
}
