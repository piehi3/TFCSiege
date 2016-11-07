package tfcs.blocks.devices;

import tfcs.core.GearRegistry;
import tfcs.core.TFCSBlocks;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.gears.AxleComponent;
import tfcs.gears.GearComponent;
import tfcs.gears.ToolComponent;
import tfcs.items.gears.ItemAxleComponent;
import tfcs.items.gears.ItemFrameComponent;
import tfcs.items.gears.ItemGearComponent;
import tfcs.items.gears.ItemToolComponent;
import tfcs.items.tool.ItemWrench;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.ItemStackHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockGearFrame extends BlockTFCSContainer {
	public BlockGearFrame() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(1.5F);
		this.setBlockName(ReferenceName.BLOCK_GEAR_FRAME_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_GEARS);
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
		if (world.getTileEntity(x, y, z) != null) {
			if (world.getTileEntity(x, y, z) instanceof TileEntityGearFrame) {
				TileEntityGearFrame te = (TileEntityGearFrame) world.getTileEntity(x, y, z);
				if (entityplayer.getCurrentEquippedItem() == null) {
					if (entityplayer.capabilities.isCreativeMode) {
						// entityplayer.addChatComponentMessage(new
						// ChatComponentText(te.getAxe(1) + "" + te.getAxe(2) +
						// "" + te.getAxe(3)));
					}
					// entityplayer.attackEntityFrom(DamageSource.anvil, 1);
				} else if (entityplayer.getCurrentEquippedItem().getItem() instanceof ItemFrameComponent) {
					GearRegistry gr = GearRegistry.instance;
					if (entityplayer.getCurrentEquippedItem().getItem() instanceof ItemGearComponent) {
						GearComponent gearComponent = (GearComponent) gr.getFrameComponentFromString(((ItemFrameComponent) entityplayer.getCurrentEquippedItem().getItem()).getFrameComponent() + "_"
								+ (entityplayer.getCurrentEquippedItem().getItemDamage() + 1));
						te.setFrameComponent(gearComponent, false);
						ItemStackHelper.decreaseItemStackFromPlayer(entityplayer.getCurrentEquippedItem(), entityplayer);
					} else if (entityplayer.getCurrentEquippedItem().getItem() instanceof ItemAxleComponent) {
						AxleComponent gearComponent = (AxleComponent) gr.getFrameComponentFromString(((ItemFrameComponent) entityplayer.getCurrentEquippedItem().getItem()).getFrameComponent() + "_"
								+ (entityplayer.getCurrentEquippedItem().getItemDamage() + 1));
						te.setFrameComponent(gearComponent, false);
						ItemStackHelper.decreaseItemStackFromPlayer(entityplayer.getCurrentEquippedItem(), entityplayer);
					} else if (entityplayer.getCurrentEquippedItem().getItem() instanceof ItemToolComponent) {
						ToolComponent gearComponent = (ToolComponent) gr.getFrameComponentFromString(((ItemFrameComponent) entityplayer.getCurrentEquippedItem().getItem()).getFrameComponent() + "_"
								+ (entityplayer.getCurrentEquippedItem().getItemDamage() + 1));
						te.setToolComponent(gearComponent);
						ItemStackHelper.decreaseItemStackFromPlayer(entityplayer.getCurrentEquippedItem(), entityplayer);
					}
				} else if (entityplayer.getCurrentEquippedItem().getItem() instanceof ItemWrench) {
					return false;
				}
				if (te.getToolComponent() != null) {
					te.getToolComponent().onActivated(te, entityplayer);
				}
			}
		}
		return true;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		TileEntityGearFrame tileentity = (TileEntityGearFrame) world.getTileEntity(x, y, z);
		if (tileentity != null)
			if (tileentity.getHasTool())
				tileentity.getToolComponent().setActive(tileentity.getToolComponent().onFrameComponentPlace(tileentity));
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
		TileEntityGearFrame tileentity = (TileEntityGearFrame) world.getTileEntity(i, j, k);
		if (this == TFCSBlocks.gearFrame) {
			int l = MathHelper.floor_double(entityliving.rotationYaw * 4F / 360F + 0.5D) & 3;
			if (entityliving.rotationPitch > 45) {// y+
				tileentity.setAxe(3);
			} else if (entityliving.rotationPitch < -45) {// y-
				tileentity.setAxe(3);
			} else if (l == 0) {// z+
				tileentity.setAxe(2);
			} else if (l == 1) {// x-
				tileentity.setAxe(1);
			} else if (l == 2) {// z-
				tileentity.setAxe(2);
			} else if (l == 3) {// x+
				tileentity.setAxe(1);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityGearFrame();
	}
}
