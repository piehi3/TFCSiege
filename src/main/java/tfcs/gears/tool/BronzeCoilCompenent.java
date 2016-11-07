package tfcs.gears.tool;

import net.minecraft.util.ResourceLocation;
import tfcs.gears.CoilToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BronzeCoilCompenent extends CoilToolComponent {
	public BronzeCoilCompenent() {
		setCoilResistance(1.3);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_COIL_NAME + "bronze";
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BRONZE_COMPONENET_TEXTURE;
	}
}
