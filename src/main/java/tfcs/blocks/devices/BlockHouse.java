package tfcs.blocks.devices;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityHouse;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockHouse extends BlockTFCSContainer{

	public BlockHouse() {
		super(Material.anvil);
		this.setBlockName(ReferenceName.BlOCK_FOUNDATION_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setHardness(24);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		TileEntityHouse te = (TileEntityHouse) world.getTileEntity(x, y, z);
		te.setAsHome(entityplayer);
		if(te.isHome())
		entityplayer.addChatMessage(new ChatComponentText("House Has Been Set"));
		return super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);
	}

	/*@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return ReferenceMultiBlock.PORT_STRUCTURE.getMultiBlockStructureWithFourRotations(world, x, y, z, false, -5, -1, -1) != -1;
	}*/

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int meta, float sideX, float sideY, float sideZ, int side) {
		return super.onBlockPlaced(world, x, y, z, meta, sideX, sideY, sideZ, side);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return super.canBlockStay(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityHouse();
	}

}
