package tfcs.util;

import tfcs.core.TFCSBlocks;

import com.bioxx.tfc.api.TFCBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class ResistanceHelper {
	public static float getResistanceFromBlock(World world,int x,int y,int z) {
		Block block = world.getBlock(x, y, z);
		if(block!=null||!block.getMaterial().equals(Material.air))
			if(block instanceof IResistance)
				return ((IResistance)block).getResistance();
			else if(block.equals(TFCBlocks.stoneIgExCobble))
				return 0.1F;
			else if(block.equals(TFCBlocks.stoneIgInCobble))
				return 0.2F;
			else if(block.equals(TFCBlocks.stoneMMCobble))
				return 0.075F;
			else if(block.equals(TFCBlocks.stoneSedCobble))
				return 0.05F;
			else if(block.equals(TFCSBlocks.foundation))
				return 4.0F;
		return 0;
	}
}
