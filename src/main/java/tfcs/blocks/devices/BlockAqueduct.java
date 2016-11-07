package tfcs.blocks.devices;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityAqueduct;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAqueduct extends BlockTFCSContainer {

	public BlockAqueduct() {
		super(Material.rock);
		this.setBlockName(ReferenceName.BLOCK_AQUEDUCT_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setTickRandomly(true);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityAqueduct();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		return super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);
	}
	
}
