package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class RedSteelSawCompenent extends SawToolComponent {
	public RedSteelSawCompenent() {
		this.setCuttingPowerByte((byte) 6);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.RED_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "redSteel"+ReferenceName.ITEM_SAW_NAME;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.redSteelSaw);
	}
	
}
