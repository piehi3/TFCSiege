package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class SteelSawCompenent extends SawToolComponent {
	public SteelSawCompenent() {
		this.setCuttingPowerByte((byte) 4);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "steel"+ReferenceName.ITEM_SAW_NAME;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.steelSaw);
	}
	
}
