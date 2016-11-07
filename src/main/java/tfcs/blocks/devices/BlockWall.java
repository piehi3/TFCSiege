package tfcs.blocks.devices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bioxx.tfc.Blocks.Terrain.BlockCobble;
import com.bioxx.tfc.Blocks.Terrain.BlockCollapsible;
import com.bioxx.tfc.Blocks.Terrain.BlockSand;
import com.bioxx.tfc.Core.TFC_Sounds;
import com.bioxx.tfc.Entities.EntityFallingBlockTFC;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.entity.EntityFallingBlockTFCS;
import tfcs.reference.Reference;
import tfcs.tileentities.TileEntityWall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWall extends BlockTFCSContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] wallIcon;
	public IIcon baseIcon;

	String uName;
	String mName;
	String tName;
	Material material;
	int maxHitPoint;

	public BlockWall(Material material, String uName, String mName, String tName, int maxHitPoint) {
		super(material);
		this.setBlockName(uName + "." + mName + "." + tName);
		this.uName = uName;
		this.mName = mName;
		this.tName = tName;
		this.material = material;
		this.maxHitPoint = maxHitPoint;
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
		this.setResistance(50.0F);
		this.setHardness(-1.0F);
		if (mName.equals("thatch"))
			this.setStepSound(Block.soundTypeGrass);
		else if (mName.equals("wood"))
			this.setStepSound(Block.soundTypeWood);
		else
			this.setStepSound(Block.soundTypeStone);
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return material.equals(Material.wood) ? 5 : 0;
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return material.equals(Material.wood) ? 100 : 0;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		/*
		 * TileEntityWall tileentity = (TileEntityWall) world.getTileEntity(x,
		 * y, z); if(tileentity != null) if(tileentity.damageWall(5)){
		 * tileentity.getWorldObj().setBlockToAir(x, y, z);
		 * tileentity.getWorldObj().markBlockForUpdate(x, y, z); }
		 */
		return super.isFlammable(world, x, y, z, face);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		wallIcon = new IIcon[11];
		baseIcon = par1IconRegister.registerIcon(Reference.ModID + ":" + uName + "." + mName + "." + tName + "_" + -1);
		for (int i = 0; i < wallIcon.length; ++i)
			wallIcon[i] = par1IconRegister.registerIcon(Reference.ModID + ":" + uName + "." + mName + "." + tName + "_" + i);
	}

	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		TileEntity tileentity = block.getTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityWall)
			switch (side) {
			case 0:
			case 1:
				return mName.equals("stone") ? wallIcon[((TileEntityWall) tileentity).getRenderIcon()] : baseIcon;
			default:
				return wallIcon[((TileEntityWall) tileentity).getRenderIcon()];
			}
		return baseIcon;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		switch (side) {
		case 0:
		case 1:
			return baseIcon;
		default:
			return wallIcon!= null ? wallIcon[0] : baseIcon;
		}
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
		// explosion.func_77277_b().clear();
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityWall)
			if (((TileEntityWall) tileentity).damageWall((int) explosion.explosionSize * 10))
				super.onBlockExploded(world, x, y, z, explosion);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityWall)
			if (entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().getItem() instanceof ItemTool) {
				Object[] set = ((ItemTool) entityplayer.getCurrentEquippedItem().getItem()).getToolClasses(entityplayer.getCurrentEquippedItem()).toArray();
				for (int i = 0; i < set.length; i++) {
					if (set[i].equals("pickaxe")) {
						if (canDestroy(world, x, y, z)) {
							entityplayer.getCurrentEquippedItem().attemptDamageItem(1, world.rand);
							if (((TileEntityWall) tileentity).damageWall(25)) {
								world.playSoundEffect(x, y, z, this.soundTypeMetal.getBreakSound(), 10, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.5F);
								this.dropBlockAsItem(world, x, y, z, world.getBlock(x, y, z).getDamageValue(world, x, y, z), 1);
								world.setBlockToAir(x, y, z);
							} else {
								world.playSoundEffect(x, y, z, this.soundTypeMetal.getBreakSound(), 2, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 1.0F);
							}
						}
					}
				}
			}
		return super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		TileEntityWall tileentity = new TileEntityWall();
		tileentity.setMaxHitPoints(this.maxHitPoint);
		return tileentity;
	}

	public boolean canDestroy(World world, int x, int y, int z) {
		byte b = 0;
		b += isBlockWall(world, x + 1, y, z);
		b += isBlockWall(world, x - 1, y, z);
		b += isBlockWall(world, x, y + 1, z);
		b += isBlockWall(world, x, y - 1, z);
		b += isBlockWall(world, x, y, z + 1);
		b += isBlockWall(world, x, y, z - 1);
		return b < 2;
	}

	private int isBlockWall(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) instanceof BlockWall ? 1 : 0;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		world.scheduleBlockUpdate(i, j, k, this, tickRate(world));
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block l) {
		world.scheduleBlockUpdate(i, j, k, this, tickRate(world));
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		Block block = world.getBlock(i, j, k);
		if (material.equals(Material.rock) && world.getTileEntity(i, j, k) != null && world.getTileEntity(i, j, k) instanceof TileEntityWall) {
			int maxHitPoints = ((TileEntityWall) world.getTileEntity(i, j, k)).getMaxHitPoints();
			int hitPoints = ((TileEntityWall) world.getTileEntity(i, j, k)).getHitPoints();
			if (world.doChunksNearChunkExist(i, j, k, 1) && !BlockCollapsible.isNearSupport(world, i, j, k, 4, 0)) {
				int meta = world.getBlockMetadata(i, j, k);

				boolean canFallOneBelow = BlockCollapsible.canFallBelow(world, i, j - 1, k);
				byte count = 0;
				List<Integer> sides = new ArrayList<Integer>();

				if (world.isAirBlock(i + 1, j, k)) {
					count++;
					if (BlockCollapsible.canFallBelow(world, i + 1, j - 1, k))
						sides.add(0);
				}
				if (world.isAirBlock(i, j, k + 1)) {
					count++;
					if (BlockCollapsible.canFallBelow(world, i, j - 1, k + 1))
						sides.add(1);
				}
				if (world.isAirBlock(i - 1, j, k)) {
					count++;
					if (BlockCollapsible.canFallBelow(world, i - 1, j - 1, k))
						sides.add(2);
				}
				if (world.isAirBlock(i, j, k - 1)) {
					count++;
					if (BlockCollapsible.canFallBelow(world, i, j - 1, k - 1))
						sides.add(3);
				}

				if (!canFallOneBelow && count > 2 && !sides.isEmpty()) {
					switch (sides.get(random.nextInt(sides.size()))) {
					case 0: {
						world.setBlockToAir(i, j, k);
						world.setBlock(i + 1, j, k, block, meta, 0x2);
						setWallData(world, i + 1, k, k, maxHitPoints, hitPoints);
						tryToFall(world, i + 1, j, k, block, maxHitPoints, hitPoints);
						break;
					}
					case 1: {
						world.setBlockToAir(i, j, k);
						world.setBlock(i, j, k + 1, block, meta, 0x2);
						setWallData(world, i, k, k + 1, maxHitPoints, hitPoints);
						tryToFall(world, i, j, k + 1, block, maxHitPoints, hitPoints);
						break;
					}
					case 2: {
						world.setBlockToAir(i, j, k);
						world.setBlock(i - 1, j, k, block, meta, 0x2);
						setWallData(world, i - 1, k, k, maxHitPoints, hitPoints);
						tryToFall(world, i - 1, j, k, block, maxHitPoints, hitPoints);
						break;
					}
					case 3: {
						world.setBlockToAir(i, j, k);
						world.setBlock(i, j, k - 1, block, meta, 0x2);
						setWallData(world, i, k, k - 1, maxHitPoints, hitPoints);
						tryToFall(world, i, j, k - 1, block, maxHitPoints, hitPoints);
						break;
					}
					}
				} else if (canFallOneBelow) {
					tryToFall(world, i, j, k, block, maxHitPoints, hitPoints);
				}
			}
		}
	}

	private void setWallData(World world, int x, int y, int z, int maxHitPoints, int hitPoints) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityWall) {
			((TileEntityWall) tileentity).setWallHitPoints(maxHitPoints, hitPoints);
			world.markBlockForUpdate(x, y, z);
		}
	}

	public void tryToFall(World world, int x, int y, int z, Block block, int maxHitPoints, int hitPoints) {
		if (!world.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);
			if (BlockCollapsible.canFallBelow(world, x, y - 1, z) && y >= 0 && (!BlockCollapsible.isNearSupport(world, x, y, z, 4, 0) || block instanceof BlockSand)) {
				byte byte0 = 32;

				if (world.checkChunksExist(x - byte0, y - byte0, z - byte0, x + byte0, y + byte0, z + byte0)) {
					if (!world.isRemote) {
						EntityFallingBlockTFCS entityfallingblock = new EntityFallingBlockTFCS(world, x + 0.5F, y + 0.5F, z + 0.5F, block, meta, maxHitPoints, hitPoints);
						world.spawnEntityInWorld(entityfallingblock);
						if (block instanceof BlockCobble)
							world.playSoundAtEntity(entityfallingblock, TFC_Sounds.FALLININGROCKSHORT, 1.0F, 0.8F + (world.rand.nextFloat() / 2));
						else
							world.playSoundAtEntity(entityfallingblock, TFC_Sounds.FALLININGDIRTSHORT, 1.0F, 0.8F + (world.rand.nextFloat() / 2));
					}
				} else {
					world.setBlockToAir(x, y, z);

					while (BlockCollapsible.canFallBelow(world, x, y - 1, z) && y > 0) {
						--y;
					}

					if (y > 0) {
						world.setBlock(x, y, z, block, meta, 0x2);
						setWallData(world, x, y, z, maxHitPoints, hitPoints);
					}
				}
			}
		}
	}
}
