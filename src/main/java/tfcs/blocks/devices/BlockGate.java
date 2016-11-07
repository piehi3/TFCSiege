package tfcs.blocks.devices;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.Reference;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityGate;

public class BlockGate extends BlockWall {

	
	private int maxHitPoint;
	private byte type;
	
	public BlockGate(Material material, String name, int maxHitPoint,byte type) {
		super(material,"","","",0);
		this.maxHitPoint = maxHitPoint;
		this.type = type;
		this.setBlockName(ReferenceName.BLOCK_GATE_NAME+"."+name);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
		this.setResistance(50.0F);
		this.setHardness(-1.0F);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.baseIcon = iconRegister.registerIcon(Reference.ModID + ":" + ReferenceName.BLOCK_GATE_NAME);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		TileEntityGate tileentity = new TileEntityGate();
		tileentity.setMaxHitPoints(this.maxHitPoint);
		return tileentity.setTexture(type);
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
		//TileEntity te = world.getTileEntity(x, y, z);
		//if(te != null && te instanceof TileEntityGate)
			
		return super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);
	}
	
}
