package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class CopperSawCompenent extends SawToolComponent {

	public CopperSawCompenent() {
		this.setCuttingPowerByte((byte) 0);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.COPPER_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "copper"+ReferenceName.ITEM_SAW_NAME;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.copperSaw);
	}
	
}
