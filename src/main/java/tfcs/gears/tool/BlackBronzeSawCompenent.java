package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlackBronzeSawCompenent extends SawToolComponent {
	public BlackBronzeSawCompenent() {
		this.setCuttingPowerByte((byte) 1);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_BRONZE_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "blackBronze"+ReferenceName.ITEM_SAW_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackBronzeSaw);
	}
}
