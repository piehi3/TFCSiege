package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlackBronzeCoilCompenent extends CoilToolComponent {
	public BlackBronzeCoilCompenent() {
		setCoilResistance(1.5);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "blackBronze";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_BRONZE_COMPONENET_TEXTURE;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackBronzeCoil);
	}
}
