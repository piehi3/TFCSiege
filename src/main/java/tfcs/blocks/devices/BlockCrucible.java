package tfcs.blocks.devices;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityTFCSCrucible;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockCrucible extends BlockTFCSContainer {

	public BlockCrucible() {
		super(Material.iron);
		this.setBlockName(ReferenceName.BlOCK_CRUCIBLE_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (world.getTileEntity(x, y, z) instanceof TileEntityTFCSCrucible)
			if (((TileEntityTFCSCrucible) world.getTileEntity(x, y, z)).checkForStructure(world, x, y, z))
				((TileEntityTFCSCrucible) world.getTileEntity(x, y, z)).setValide();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTFCSCrucible();
	}

}
