package tfcs.gears.tool;

import net.minecraft.util.ResourceLocation;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;

public class BlueSteelSawCompenent extends SawToolComponent {
	public BlueSteelSawCompenent() {
		this.setCuttingPowerByte((byte) 6);
	}
	
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLUE_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return "blueSteel"+ReferenceName.ITEM_SAW_NAME;
	}
}
