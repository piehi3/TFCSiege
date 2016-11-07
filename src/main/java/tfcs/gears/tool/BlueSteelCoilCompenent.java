package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlueSteelCoilCompenent extends CoilToolComponent {
	public BlueSteelCoilCompenent() {
		setCoilResistance(0.1);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "blueSteel";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLUE_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blueSteelCoil);
	}
	
}
