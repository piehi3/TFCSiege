package tfcs.blocks.devices;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceMultiBlock;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityPort;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPort extends BlockTFCSContainer {

	public BlockPort() {
		super(Material.anvil);
		this.setBlockName(ReferenceName.BlOCK_PORT_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		/*if (canPlaceBlockAt(world, x, y, z))
			switch (ReferenceMultiBlock.PORT_STRUCTURE.getMultiBlockStructureWithFourRotations(world, x, y, z, false, -5, -1, -1)) {
			case 0:
				ReferenceMultiBlock.BOAT_STRUCTURE.buildStructure(world, x - 5, y, z + 1);
				break;
			case 1:
				ReferenceMultiBlock.BOAT_STRUCTURE.buildStructureWith90DegreeRotation(world, x + 1, y, z - 5);
				break;
			case 2:
				ReferenceMultiBlock.BOAT_STRUCTURE.buildStructureWith180DegreeRotation(world, x - 5, y, z - 5);
				break;
			case 3:
				ReferenceMultiBlock.BOAT_STRUCTURE.buildStructureWith270DegreeRotation(world, x - 5, y, z - 5);
				break;
			default:
				break;
			}*/

		return super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return ReferenceMultiBlock.PORT_STRUCTURE.getMultiBlockStructureWithFourRotations(world, x, y, z, false, -5, -1, -1) != -1;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int meta, float sideX, float sideY, float sideZ, int side) {
		return super.onBlockPlaced(world, x, y, z, meta, sideX, sideY, sideZ, side);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		// ReferenceMultiBlock.PORT_STRUCTURE.hasInstanceMultiBlockStructureWithFourRotations(world,
		// x, y, z,false);
		return super.canBlockStay(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPort();
	}

}
