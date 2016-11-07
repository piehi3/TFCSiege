package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlackSteelSawCompenent extends SawToolComponent {
	public BlackSteelSawCompenent() {
		this.setCuttingPowerByte((byte) 5);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "blackSteel"+ReferenceName.ITEM_SAW_NAME;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackSteelSaw);
	}
}
