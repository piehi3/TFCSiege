package tfcs.gears.tool;

import com.bioxx.tfc.api.TFCBlocks;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tfcs.core.RotationPacket;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;

public class WaterWheelToolComponent extends ToolComponent{

	private byte waterNumber = 0;

	public WaterWheelToolComponent() {

	}

	@Override
	public void update(TileEntityGearFrame tileentity) {
		tileentity.addRotation(new RotationPacket(waterNumber, 35 * waterNumber));
	}
	
	@Override
	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		waterNumber = getWaterNumber(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, tileentity);
		return waterNumber > 0;
	}
	
	private byte getWaterNumber(World world, int x, int y, int z, TileEntityGearFrame tileentity) {
		byte count = 0;
		for (int i = 1; i <= getSize(); i++) {
			for (int j = -getSize(); j <= getSize(); j++) {
				if (tileentity.getAxe(1) == 1) {
					count += isWater(world, x + j, y - i, z);
				} else if (tileentity.getAxe(2) == 1) {
					count += isWater(world, x, y - i, z + j);
				}
			}
		}
		return count;
	}

	private byte isWater(World world, int x, int y, int z) {
		return (byte) (world.getBlock(x, y, z).equals(TFCBlocks.freshWaterStationary) ? 1 : 0);
	}

	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		RenderHelper.renderCircle(tessellator, rotation, 8, getSize(), 0.1, 1, 0.1,0.4,0,0);
		RenderHelper.renderCircle(tessellator, rotation, 8, getSize(), 0.1, 1, 0.1,-0.4,0,0);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.waterNumber = tag.getByte("WATER_NUMBER");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setByte("WATER_NUMBER", this.waterNumber);
	}

	@Override
	public String getName() {
		return ReferenceName.ITEM_WATER_WHEEL_NAME;
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.WOODEN_COMPONENET_TEXTURE;
	}

	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.waterWheel, 1, 0);
	}
}
