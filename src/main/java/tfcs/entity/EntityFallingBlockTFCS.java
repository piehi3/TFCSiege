package tfcs.entity;

import tfcs.tileentities.TileEntityWall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Entities.EntityFallingBlockTFC;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;

public class EntityFallingBlockTFCS extends EntityFallingBlockTFC {

	private int maxHitPoints;
	private int hitPoints;

	public EntityFallingBlockTFCS(World world) {
		super(world);
	}

	public EntityFallingBlockTFCS(World world, double x, double y, double z, Block b) {
		super(world, x, y, z, b);
	}

	public EntityFallingBlockTFCS(World world, double x, double y, double z, Block b, int meta,int maxHitPoints,int hitPoints) {
		super(world, x, y, z, b, meta);
		this.maxHitPoints = maxHitPoints;
		this.hitPoints = hitPoints;
	}

	private boolean canDestroy(Block b) {
		return !(b == TFCBlocks.charcoal || b == TFCBlocks.molten);
	}
	
	public boolean canReplace(World world, int x, int y, int z) {
		Block b = world.getBlock(x, y, z);
		if (canDestroy(b) && (b.isAir(world, x, y, z) || !b.isOpaqueCube() && !b.renderAsNormalBlock() && !worldObj.isSideSolid(x, y, z, ForgeDirection.UP)))
			return setBlockWithDrops(worldObj, x, y, z, getBlock(), this.blockMeta);
		else if (b instanceof BlockOre && TFCOptions.enableCaveInsDestroyOre)
			return world.setBlockToAir(x, y, z);
		return false;
	}

	public boolean setBlockWithDrops(World world, int x, int y, int z, Block b, int meta) {
		Block block = world.getBlock(x, y, z);
		if (block.getMaterial() != Material.air) {
			int l = world.getBlockMetadata(x, y, z);
			world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (l << 12));
			block.dropBlockAsItem(world, x, y, z, l, 0);
		}
		boolean bool = world.setBlock(x, y, z, b, meta, 3);
		if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityWall) {
			((TileEntityWall) world.getTileEntity(x, y, z)).setWallHitPoints(maxHitPoints, hitPoints);
			world.markBlockForUpdate(x, y, z);
		}
		return bool;
	}
}
