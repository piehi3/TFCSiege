package tfcs.blocks.devices;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.guild.Guild;
import tfcs.reference.Reference;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityGuild;

public class BlockGuild extends BlockWall{
	
	String name;
	
	public BlockGuild(Material material, String name, int maxHitPoint) {
		super(material,"","","",0);
		this.maxHitPoint = maxHitPoint;
		this.setBlockName(ReferenceName.BLOCK_GUILD_NAME+"."+name);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_OPTICS);
		this.setResistance(50.0F);
		this.setHardness(-1.0F);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.baseIcon = iconRegister.registerIcon(Reference.ModID + ":" + ReferenceName.BLOCK_GATE_NAME);
	}
	
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		return this.blockIcon;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		TileEntityGuild tileentity = new TileEntityGuild();
		tileentity.setMaxHitPoints(this.maxHitPoint);
		return tileentity;
	}
	
	/*@Override
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
	}*/
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		return super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);
	}
}
