package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BismuthBronzeCoilCompenent extends CoilToolComponent {
	public BismuthBronzeCoilCompenent() {
		setCoilResistance(1.5);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "bismuthBronze";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BISMUTH_BRONZE_COMPONENET_TEXTURE;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.bismuthBronzeCoil);
	}
	
}
