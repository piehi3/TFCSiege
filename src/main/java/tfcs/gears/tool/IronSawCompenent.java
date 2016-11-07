package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class IronSawCompenent extends SawToolComponent {
	public IronSawCompenent() {
		this.setCuttingPowerByte((byte) 2);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.IRON_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "iron"+ReferenceName.ITEM_SAW_NAME;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.ironSaw);
	}
}
