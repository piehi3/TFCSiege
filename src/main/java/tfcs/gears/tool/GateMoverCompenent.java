package tfcs.gears.tool;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tfcs.core.RotationPacket;
import tfcs.core.TFCSBlocks;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGate;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;

public class GateMoverCompenent extends ToolComponent {

	@Override
	public void onRotated(RotationPacket packet, TileEntityGearFrame tileentity) {
		moveGate(tileentity, packet.getSpeed() < 0 ? -1 : 1, packet.getSpeed());
	}

	private void moveGate(TileEntityGearFrame tileentity, int r, double rotation) {
		if (tileentity.getAxe(1) == 1) {
			moveRow(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord + 1, 0, 1 * r, 0, rotation);
			moveRow(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord - 1, 0, -1 * r, 0, rotation);
		} else if (tileentity.getAxe(2) == 1) {
			moveRow(tileentity.getWorldObj(), tileentity.xCoord + 1, tileentity.yCoord, tileentity.zCoord, 0, 1 * r, 0, rotation);
			moveRow(tileentity.getWorldObj(), tileentity.xCoord - 1, tileentity.yCoord, tileentity.zCoord, 0, -1 * r, 0, rotation);
		} else if (tileentity.getAxe(3) == 1) {
			// moveRow(tileentity.getWorldObj(), tileentity.xCoord,
			// tileentity.yCoord + 1, tileentity.zCoord, 1);
			// moveRow(tileentity.getWorldObj(), tileentity.xCoord,
			// tileentity.yCoord - 1, tileentity.zCoord, -1);
		}
	}

	private void moveRow(World world, int x, int y, int z, int motionX, int motionY, int motionZ, double rotation) {
		if (world.getTileEntity(x + motionX, y + motionY, z + motionZ) != null && world.getTileEntity(x + motionX, y + motionY, z + motionZ) instanceof TileEntityGate) {
			int[] start = getStartBlock(world, x, y, z, motionX, motionY, motionZ);
			moveBlock(world, start[0], start[1], start[2], motionX, motionY, motionZ, rotation);
		}
	}

	private boolean moveBlock(World world, int x, int y, int z, int motionX, int motionY, int motionZ, double rotation) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityGate) {
			if (world.isAirBlock(x - motionX, y - motionY, z - motionZ)
					|| (world.getTileEntity(x - motionX, y - motionY, z - motionZ) != null && world.getTileEntity(x - motionX, y - motionY, z - motionZ) instanceof TileEntityGate)) {
				if (((TileEntityGate) te).onGateMove(rotation)) {
					((TileEntityGate) te).shiftGate(motionX, motionY, motionZ);
				}
				return moveBlock(world, x + motionX, y + motionY, z + motionZ, motionX, motionY, motionZ, rotation);
			}
		}
		return true;
	}

	private int[] getStartBlock(World world, int x, int y, int z, int motionX, int motionY, int motionZ) {
		TileEntity te = world.getTileEntity(x - motionX, y - motionY, z - motionZ);
		if (te != null && te instanceof TileEntityGate)
			return getStartBlock(world, x - motionX, y - motionY, z - motionZ, motionX, motionY, motionZ);
		return new int[] { x, y, z };
	}

	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		RenderHelper.renderCircle(tessellator, rotation, 16, 0.7, 0.1, 1, 0.2, 0, 0, 0);
	}

	@Override
	public String getName() {
		return ReferenceName.ITEM_GATE_MOVER_NAME;
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.WOODEN_COMPONENET_TEXTURE;
	}

	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.gateMover, 1, 0);
	}

}
