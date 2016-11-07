package tfcs.util;

import tfcs.reference.Reference;
import net.minecraft.util.ResourceLocation;

public class ResourceHelper {
	private static final String blocks = ":textures/blocks/";
	private static final String models = ":textures/models/";
	public static ResourceLocation getResourceLocationFromBlocks(String texture){
		return new ResourceLocation(Reference.ModID + blocks + texture + ".png");
	}
	public static ResourceLocation getResourceLocationFromEntity(String texture){
		return new ResourceLocation(Reference.ModID + models + texture + ".png");
	}
}
