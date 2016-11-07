package tfcs.blocks.devices;

import com.bioxx.tfc.api.TFCBlocks;

import tfcs.core.TFCSBlocks;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityBomb;
import tfcs.tileentities.TileEntityCandle;
import tfcs.tileentities.TileEntityWall;
import tfcs.util.BlockHelper;
import tfcs.util.ItemStackHelper;
import tfcs.util.NBTHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockBomb extends BlockTFCSContainer {

	String type;
	float explotionModifier;

	public BlockBomb(String type, float explotionModifier) {
		super(Material.rock);
		this.setBlockName(ReferenceName.BLOCK_BOMB_NAME + "." + type);
		this.type = type;
		this.explotionModifier = explotionModifier;
		this.setHardness(5.0F);
		this.setResistance(1.0F);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {}
	
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		super.onBlockPlacedBy(world, x, y, z, entity, is);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityBomb) {
			if (NBTHelper.hasKey(is, "GUNPOWDER")) {
				((TileEntityBomb) te).setGunpowder(NBTHelper.getFloat(is, "GUNPOWDER"));
				((TileEntityBomb) te).setGunpowderStorage(NBTHelper.getInt(is, "GUNPOWDER_STORAGE"));
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xCoord, float yCoord, float zCoord) {
		TileEntityBomb tileentity = (TileEntityBomb) world.getTileEntity(x, y, z);
		if (tileentity != null) {
			ItemStack itemstack = player.getCurrentEquippedItem();
			if (player.getCurrentEquippedItem() == null) {
				if (player.isSneaking()) {
					if (tileentity.isClosed()) {
						ItemStack is = new ItemStack(this, 1, 0);
						NBTHelper.setFloat(is, "GUNPOWDER", tileentity.getGunpowder());
						NBTHelper.setInt(is, "GUNPOWDER_STORAGE", tileentity.getGunpowderStorage());
						ItemStackHelper.dropItemStackInWorld(world, x, y, z, is);
					}
					world.setBlockToAir(x, y, z);
				} else {
					tileentity.toggleOpen();
				}
				return true;
			} else if (itemstack.getItem() == Item.getItemFromBlock(TFCBlocks.torch)) {
				BlockHelper.light(player, tileentity, tileentity);
				return true;
			} else if (BlockHelper.addGunPowder(tileentity, itemstack)) {
				ItemStackHelper.decreaseItemStackFromPlayer(itemstack, player);
				return true;
			}
		}
		return false;
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
		TileEntityBomb te = (TileEntityBomb) world.getTileEntity(x, y, z);
		if (te != null)
			te.explode();
		super.onBlockExploded(world, x, y, z, explosion);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		TileEntityBomb tileentity = new TileEntityBomb();
		tileentity.setExplotionModifier(this.explotionModifier);
		tileentity.setType(type.equals("iron") ? 2 : 1);
		return tileentity;
	}

}
