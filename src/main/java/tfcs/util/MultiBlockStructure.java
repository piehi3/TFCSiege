package tfcs.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MultiBlockStructure {

	Block[][][] blockList;

	public MultiBlockStructure(Block[][][] blockList) {
		this.blockList = blockList;
	}

	public Block[][][] getBlockList() {
		return blockList;
	}

	public void setBlock(Block block, int x, int y, int z) {
		blockList[x][y][z] = block;
	}

	public void setNullToAir() {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					if (blockList[i][j][k] == null)
						blockList[i][j][k] = Blocks.air;
	}

	public void buildStructure(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlock(x + i, y + j, z + k, blockList[i][j][k]);
	}

	public void buildStructureWith90DegreeRotation(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlock(x + k, y + j, z + i, blockList[i][j][k]);
	}

	public void buildStructureWith180DegreeRotation(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlock(x + (-i + (blockList.length - 1)), y + j, z + (-k + (blockList[i][j].length - 1)), blockList[i][j][k]);
	}

	public void buildStructureWith270DegreeRotation(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlock(x + (-k + (blockList[i][j].length - 1)), y + j, z + (-i + (blockList.length - 1)), blockList[i][j][k]);
	}
	
	public void clearBlocks(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlockToAir(x + i, y + j, z + k);
	}

	public void clearBlocksWith90DegreeRotation(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlockToAir(x + k, y + j, z + i);
	}

	public void clearBlocksWith180DegreeRotation(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlockToAir(x + (-i + (blockList.length - 1)), y + j, z + (-k + (blockList[i][j].length - 1)));
	}

	public void clearBlocksWith270DegreeRotation(World world, int x, int y, int z) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					world.setBlockToAir(x + (-k + (blockList[i][j].length - 1)), y + j, z + (-i + (blockList.length - 1)));
	}

	public boolean hasMultiBlockStructure(World world, int x, int y, int z, boolean useAirBlocks) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					if (!blockList[i][j][k].equals(Blocks.air) || useAirBlocks)
						if (!world.getBlock(x + i, y + j, z + k).equals(blockList[i][j][k]))
							return false;
		return true;
	}

	private boolean has90DegreeStructure(World world, int x, int y, int z, boolean useAirBlocks) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					if (!blockList[i][j][k].equals(Blocks.air) || useAirBlocks)
						if (!world.getBlock(x + k, y + j, z + i).equals(blockList[i][j][k]))
							return false;
		return true;
	}

	private boolean has180DegreeStructure(World world, int x, int y, int z, boolean useAirBlocks) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					if (!blockList[i][j][k].equals(Blocks.air) || useAirBlocks)
						if (!world.getBlock(x + (-i + (blockList.length - 1)), y + j, z + (-k + (blockList[i][j].length - 1))).equals(blockList[i][j][k]))
							return false;
		return true;
	}

	private boolean has270DegreeStructure(World world, int x, int y, int z, boolean useAirBlocks) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					if (!blockList[i][j][k].equals(Blocks.air) || useAirBlocks)
						if (!world.getBlock(x + (-k + (blockList[i][j].length - 1)), y + j, z + (-i + (blockList.length - 1))).equals(blockList[i][j][k]))
							return false;
		return true;
	}
	
	public boolean hasMultiBlockStructureWithFourRotations(World world, int x, int y, int z, boolean useAirBlocks, int siftX, int siftY, int siftZ) {
		return getMultiBlockStructureWithFourRotations(world, x, y, z, useAirBlocks, siftX, siftY, siftZ)!=-1;
	}

	public int getMultiBlockStructureWithFourRotations(World world, int x, int y, int z, boolean useAirBlocks, int siftX, int siftY, int siftZ) {
		if (hasMultiBlockStructure(world, x + siftX, y + siftY, z + siftZ, useAirBlocks))
			return 0;
		else if (has90DegreeStructure(world, x + siftZ, y + siftY, z + siftX, useAirBlocks))
			return 1;
		else if (has180DegreeStructure(world, x + siftX, y + siftY, z + siftX, useAirBlocks))
			return 2;
		else if (has270DegreeStructure(world, x + siftX, y + siftY, z + siftX, useAirBlocks))
			return 3;
		else
			return -1;
	}

	public boolean hasInstanceMultiBlockStructure(World world, int x, int y, int z, boolean useAirBlocks) {
		for (int i = 0; i < blockList.length; i++)
			for (int j = 0; j < blockList[i].length; j++)
				for (int k = 0; k < blockList[i][j].length; k++)
					if (!blockList[i][j][k].equals(Blocks.air) || useAirBlocks)
						if (!blockList[i][j][k].getClass().isAssignableFrom(world.getBlock(x, y, z).getClass()))
							return false;
		return true;
	}

}
