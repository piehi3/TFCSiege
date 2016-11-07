package tfcs.gears.tool;

import org.lwjgl.opengl.GL11;

import tfcs.core.RotationPacket;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class WindmillToolComponent extends ToolComponent{

	public WindmillToolComponent() {

	}
	
	@Override
	public void update(TileEntityGearFrame tileentity) {
		tileentity.addRotation(new RotationPacket(getRotationalSpeed(tileentity), getRotationalTorque(tileentity)));
	}

	private double getRotationalSpeed(TileEntityGearFrame tileentity) {
		return 0.5 * getSize() * Math.log(tileentity.yCoord + 1);
	}

	private double getRotationalTorque(TileEntityGearFrame tileentity) {
		return getRotationalSpeed(tileentity) * getRotationalSpeed(tileentity);
	}

	@Override
	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		if (tileentity.getHasAxle()) {
			int d = (int) ((getSize() - 1) / 2);
			for (int i = 0; i < getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					for (int k = 0; k < getSize(); k++) {
						if (tileentity.getWorldObj().getBlock(tileentity.xCoord-d+i, tileentity.yCoord-d+j, tileentity.zCoord-d+k) != null && !tileentity.getWorldObj().getBlock(tileentity.xCoord-d+i, tileentity.yCoord-d+j, tileentity.zCoord-d+k).equals(Blocks.air)&&!tileentity.getWorldObj().getBlock(tileentity.xCoord-d+i, tileentity.yCoord-d+j, tileentity.zCoord-d+k).getMaterial().equals(Material.wood)) {
							return false;
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {

		drawBlade(tessellator, rotation, 90 + rotation);
		drawBlade(tessellator, rotation, 90);
		drawBlade(tessellator, rotation, 90);
		drawBlade(tessellator, rotation, 90);
		rotate(-rotation);

	}

	private void drawBlade(Tessellator tessellator, double rotation, double r) {
		rotate(r);
		tessellator.startDrawingQuads();
		for (int i = 0; i < getSize(); i++) {
			tessellator.addVertexWithUV(0.4, 2 + i, 1, 1, 1);
			tessellator.addVertexWithUV(0.4, 2 + i, 0, 1, 0);
			tessellator.addVertexWithUV(0.4, 1 + i, 0, 0, 0);
			tessellator.addVertexWithUV(0.4, 1 + i, 1, 0, 1);

			tessellator.addVertexWithUV(0.6, 2 + i, 0, 1, 0);
			tessellator.addVertexWithUV(0.6, 2 + i, 1, 1, 1);
			tessellator.addVertexWithUV(0.6, 1 + i, 1, 0, 1);
			tessellator.addVertexWithUV(0.6, 1 + i, 0, 0, 0);

			tessellator.addVertexWithUV(0.6, 2 + i, 1, 1, 1);
			tessellator.addVertexWithUV(0.6, 2 + i, 0, 1, 0);
			tessellator.addVertexWithUV(0.4, 2 + i, 0, 0, 0);
			tessellator.addVertexWithUV(0.4, 2 + i, 1, 0, 1);

			tessellator.addVertexWithUV(0.4, 2 + i, 0, 1, 1);
			tessellator.addVertexWithUV(0.6, 2 + i, 0, 1, 0);
			tessellator.addVertexWithUV(0.6, 1 + i, 0, 0, 0);
			tessellator.addVertexWithUV(0.4, 1 + i, 0, 0, 1);

			tessellator.addVertexWithUV(0.6, 2 + i, 1, 1, 0);
			tessellator.addVertexWithUV(0.4, 2 + i, 1, 1, 1);
			tessellator.addVertexWithUV(0.4, 1 + i, 1, 0, 1);
			tessellator.addVertexWithUV(0.6, 1 + i, 1, 0, 0);

		}
		tessellator.draw();
	}

	private void rotate(double r) {
		GL11.glTranslated(0.5, 0.5, 0.5);
		GL11.glRotated(r, 1, 0, 0);
		GL11.glTranslated(-0.5, -0.5, -0.5);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.WINDMILL_COMPONENET_TEXTURE;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.setSize(tag.getInteger("WINDMILL_TOOL_SIZE"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("WINDMILL_TOOL_SIZE", this.getSize());
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_WINDMILL_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.windmill,1,(int)this.getSize()-1);
	}
}
