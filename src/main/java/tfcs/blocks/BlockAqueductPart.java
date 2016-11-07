package tfcs.blocks;

import java.util.Random;

import com.bioxx.tfc.api.TFCBlocks;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityAqueduct;
import tfcs.util.WaterPacket;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockAqueductPart extends BlockTFCS {

	IIcon[] icon;

	public BlockAqueductPart(String type) {
		super(Material.rock);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setBlockName(ReferenceName.BLOCK_AQUEDUCT_PART_NAME + "." + type);
		this.setTickRandomly(true);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		icon = new IIcon[] { iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_side"),
				iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_top") };
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		switch (side) {
		case 0:
		case 1:
			return icon[1];
		default:
			return icon[0];
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
		updateTick(world, x, y, z, world.rand);
		return super.onBlockActivated(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		placeWater(world, x - 1, y, z, hasWater(world, x + 1, y - 1, z));
		placeWater(world, x + 1, y, z, hasWater(world, x - 1, y - 1, z));
		placeWater(world, x, y, z - 1, hasWater(world, x, y - 1, z + 1));
		placeWater(world, x, y, z + 1, hasWater(world, x, y - 1, z - 1));
	}

	private void placeWater(World world, int x, int y, int z,Boolean b) {
		if(world.isAirBlock(x, y, z)&&b){
			world.setBlock(x, y, z, TFCBlocks.freshWater);
			return;
		}
		if(world.getBlock(x, y, z).equals(TFCBlocks.freshWater)&&!b){
			world.setBlockToAir(x, y, z);
			return;
		}
		
	}

	private boolean hasWater(World world, int x, int y, int z) {
		if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityAqueduct)
			if (((TileEntityAqueduct) world.getTileEntity(x, y, z)).getWaterPacket().getWater() >= (WaterPacket.getMaxWater() / 4))
				return true;
		return false;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		RemoveWater(world,x,y,z);
		super.onBlockHarvested(world, x, y, z, meta, player);
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion e) {
		RemoveWater(world, x, y, z);
		super.onBlockDestroyedByExplosion(world, x, y, z, e);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		RemoveWater(world, x, y, z);
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
	
	private void RemoveWater(World world, int x, int y, int z) {
		if(world.getBlock(x + 1, y, z).equals(TFCBlocks.freshWater))
			world.setBlockToAir(x + 1, y, z);
		if(world.getBlock(x - 1, y, z).equals(TFCBlocks.freshWater))
			world.setBlockToAir(x - 1, y, z);
		if(world.getBlock(x, y, z + 1).equals(TFCBlocks.freshWater))
			world.setBlockToAir(x, y, z + 1);
		if(world.getBlock(x, y, z - 1).equals(TFCBlocks.freshWater))
			world.setBlockToAir(x, y, z - 1);
	}
	
}
