package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlackSteelCoilCompenent extends CoilToolComponent {
	public BlackSteelCoilCompenent() {
		setCoilResistance(3.5);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "blackSteel";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackSteelCoil);
	}
	
}
