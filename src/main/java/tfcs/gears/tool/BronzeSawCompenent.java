package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BronzeSawCompenent extends SawToolComponent {
	public BronzeSawCompenent() {
		this.setCuttingPowerByte((byte) 1);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BRONZE_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "bronze"+ReferenceName.ITEM_SAW_NAME;
	}
	
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.bronzeSaw);
	}
	
}
