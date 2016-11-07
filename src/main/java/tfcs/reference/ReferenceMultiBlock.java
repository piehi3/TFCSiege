package tfcs.reference;

import com.bioxx.tfc.api.TFCBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import tfcs.core.TFCSBlocks;
import tfcs.util.MultiBlockStructure;

public class ReferenceMultiBlock {

	public static MultiBlockStructure PORT_STRUCTURE;
	public static MultiBlockStructure BOAT_STRUCTURE;

	public static void init() {
		PORT_STRUCTURE = new MultiBlockStructure(new Block[11][3][7]);
		for (int i = 0; i < 11; i++)
			for (int j = 0; j < 7; j++)
				PORT_STRUCTURE.setBlock(TFCBlocks.saltWaterStationary, i, 0, j);
		for (int i = 0; i < 11; i++)
			PORT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 0);
		for (int i = 0; i < 5; i++)
			PORT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 1);
		for (int i = 6; i < 11; i++)
			PORT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 1);
		PORT_STRUCTURE.setBlock(TFCBlocks.anvil, 5, 2, 1);
		// PORT_STRUCTURE.setBlock(TFCSBlocks.port, 5, 1, 1);
		PORT_STRUCTURE.setNullToAir();

		BOAT_STRUCTURE = new MultiBlockStructure(new Block[11][11][5]);
		for (int i = 1; i < 9; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 0, 1);
		for (int i = 0; i < 11; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 0, 2);
		for (int i = 1; i < 9; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 0, 3);
		for (int i = 2; i < 8; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 0);
		for (int i = 1; i < 10; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 1);
		for (int i = 0; i < 11; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 2);
		for (int i = 1; i < 10; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 3);
		for (int i = 2; i < 8; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.planks, i, 1, 4);
		for (int i = 1; i < 9; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.fence, i, 2, 0);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 0, 2, 1);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 1, 2, 1);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 8, 2, 1);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 9, 2, 1);

		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 0, 2, 2);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 9, 2, 2);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 10, 2, 2);

		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 0, 2, 3);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 1, 2, 3);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 8, 2, 3);
		BOAT_STRUCTURE.setBlock(TFCBlocks.fence, 9, 2, 3);
		for (int i = 1; i < 9; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.fence, i, 2, 4);

		for (int i = 2; i < 10; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.logNatural, 3, i, 2);

		for (int i = 2; i < 9; i++)
			BOAT_STRUCTURE.setBlock(TFCBlocks.logNatural, 6, i, 2);

		for (int i = 6; i < 10; i++)
			for (int j = 0; j < 5; j++)
				BOAT_STRUCTURE.setBlock(Blocks.wool, 4, i, j);

		for (int i = 6; i < 9; i++)
			for (int j = 0; j < 5; j++)
				BOAT_STRUCTURE.setBlock(Blocks.wool, 7, i, j);
		BOAT_STRUCTURE.setNullToAir();
	}

}
