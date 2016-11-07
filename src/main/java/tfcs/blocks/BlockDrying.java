package tfcs.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tfcs.core.TFCSBlocks;
import tfcs.core.TFCSItems;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.util.ItemStackHelper;

public class BlockDrying extends BlockTFCS {

	String type;

	public BlockDrying(String type) {
		super(Material.rock);
		this.type = type;
		this.setBlockName(ReferenceName.BLOCK_DRYING_NAME + "." + type);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setTickRandomly(true);
		this.setBlockBounds(0.4F, 0.0F, 0.2F, 0.6F, 0.2F, 0.8F);
		this.setLightOpacity(0);
		this.setHardness(1.0F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (type.equals("undryed") && rand.nextInt(200) == 0 && world.canBlockSeeTheSky(x, y, z) && world.rainingStrength == 0) {
			world.setBlock(x, y, z, TFCSBlocks.dryedMudBrick);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
		if (entityplayer.isSneaking()) {
			world.setBlockToAir(x, y, z);
			ItemStackHelper.dropItemStackInWorld(world, x, y, z, new ItemStack(TFCSItems.mudBrick, 1, type.equals("dryed") ? 1 : 0));
			return true;
		}
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

}
