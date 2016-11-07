package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BismuthBronzeSawCompenent extends SawToolComponent {
	public BismuthBronzeSawCompenent() {
		this.setCuttingPowerByte((byte) 1);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BISMUTH_BRONZE_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "bismuthBronze"+ReferenceName.ITEM_SAW_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.bismuthBronzeSaw);
	}
}
