package tfcs.blocks.devices;

import java.util.ArrayList;
import java.util.Random;

import com.bioxx.tfc.Items.ItemBlocks.ItemTorch;

import tfcs.core.TFCSBlocks;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityCandle;
import tfcs.util.ItemStackHelper;
import tfcs.util.NBTHelper;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCandle extends BlockTFCSContainer {

	public BlockCandle(boolean isON) {
		super(Material.clay);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_OPTICS);
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
		this.setHardness(0.8F);
		if (isON) {
			this.setLightLevel(0.8F);
			this.setBlockName(ReferenceName.BLOCK_CANDLE_NAME + ".on");
		} else {
			this.setBlockName(ReferenceName.BLOCK_CANDLE_NAME + ".off");
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityCandle();
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
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (world.getBlock(x, y, z).equals(TFCSBlocks.candleON))
			entity.setFire(2);
		super.onEntityWalking(world, x, y, z, entity);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> array = new ArrayList<ItemStack>();
		return array;
	}

	@Override
	protected void dropBlockAsItem(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {
		super.dropBlockAsItem(p_149642_1_, p_149642_2_, p_149642_3_, p_149642_4_, p_149642_5_);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		super.onBlockPlacedBy(world, x, y, z, entity, is);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityCandle) {
			if (NBTHelper.hasKey(is, "BURN_TIME"))
				((TileEntityCandle) te).setBurnTime(NBTHelper.getInt(is, "BURN_TIME"));
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityCandle) {
			if (is != null && is.getItem() instanceof ItemTorch) {
				((TileEntityCandle) te).lightCandle();
				return true;
			} else if (is == null) {
				if (!player.isSneaking()) {
					((TileEntityCandle) te).snuffCandle();
				} else if (world.getBlock(x, y, z).equals(TFCSBlocks.candleOFF)) {
					if (te != null && te instanceof TileEntityCandle) {
						ItemStack itemstack = new ItemStack(TFCSBlocks.candleOFF, 1, 0);
						NBTHelper.setInt(itemstack, "BURN_TIME", ((TileEntityCandle) te).getBurnTime());
						ItemStackHelper.dropItemStackInWorld(world, x, y, z, itemstack);
						world.setBlockToAir(x, y, z);
					}
				}
				return true;
			}
		}
		return false;
	}

}
