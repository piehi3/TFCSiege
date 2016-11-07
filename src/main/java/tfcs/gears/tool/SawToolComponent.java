package tfcs.gears.tool;

import org.lwjgl.opengl.GL11;

import com.bioxx.tfc.api.TFCItems;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tfcs.gears.FrameComponent;
import tfcs.gears.ToolComponent;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.CalculationHelper;
import tfcs.util.ItemStackHelper;

public class SawToolComponent extends ToolComponent {

	byte cuttingPower;
	
	public SawToolComponent() {
		this.setMaxRotation(1);
		this.setMinTorque(4);
	}

	@Override
	public void onActivated(TileEntityGearFrame tileentity, EntityPlayer player) {
		if (!tileentity.getWorldObj().isRemote) {
			if (Math.abs(tileentity.getRotationSpeed()) >= 12 && tileentity.getTorque() >= 3) {
				if (player.getCurrentEquippedItem() != null) {
					if (player.getCurrentEquippedItem().getItem().equals(TFCItems.logs)) {
						player.entityDropItem(new ItemStack(TFCItems.singlePlank, 10 + cuttingPower, player.getCurrentEquippedItem().getItemDamage()), 1.5F);
						ItemStackHelper.decreaseItemStackFromPlayer(player.getCurrentEquippedItem(), player);
					}
				}
			}
		}
	}

	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		renderPolygon(tessellator, rotation);
	}

	private void renderPolygon(Tessellator tessellator, double r) {
		double a = 360 / 8;
		double size = 0.5;
		double width = 0.05;
		int teeth = 4;
		tessellator.startDrawing(GL11.GL_TRIANGLES);
		for (double i = 0 + r; i < 360 + r;) {
			double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5;

			x1 += size * CalculationHelper.sin(i);
			y1 += size * CalculationHelper.cos(i);

			i += a;

			x2 += size * CalculationHelper.sin(i);
			y2 += size * CalculationHelper.cos(i);

			tessellator.addVertexWithUV(0.5 + width, 0.5, 0.5, 0, 0);
			tessellator.addVertexWithUV(0.5 + width, y1, x1, 0, 1);
			tessellator.addVertexWithUV(0.5 + width, y2, x2, 1, 1);

			tessellator.addVertexWithUV(0.5 - width, 0.5, 0.5, 0, 0);
			tessellator.addVertexWithUV(0.5 - width, y2, x2, 1, 1);
			tessellator.addVertexWithUV(0.5 - width, y1, x1, 0, 1);
		}

		tessellator.draw();

		tessellator.startDrawing(GL11.GL_TRIANGLES);
		for (double i = 0 + r; i < 360 + r;) {
			double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5, x3 = 0.5, y3 = 0.5;

			x1 += size * CalculationHelper.sin(i);
			y1 += size * CalculationHelper.cos(i);

			i += a;

			x2 += size * CalculationHelper.sin(i);
			y2 += size * CalculationHelper.cos(i);

			x3 += ((5 * size) / teeth) * CalculationHelper.sin(i);
			y3 += ((5 * size) / teeth) * CalculationHelper.cos(i);

			tessellator.addVertexWithUV(0.5 - width, y1, x1, 0, 0);
			tessellator.addVertexWithUV(0.5 - width, y2, x2, 1, 1);
			tessellator.addVertexWithUV(0.5, y3, x3, 0, 1);

			tessellator.addVertexWithUV(0.5 + width, y2, x2, 1, 1);
			tessellator.addVertexWithUV(0.5 + width, y1, x1, 0, 0);
			tessellator.addVertexWithUV(0.5, y3, x3, 0, 1);

			tessellator.addVertexWithUV(0.5 + width, y1, x1, 1, 1);
			tessellator.addVertexWithUV(0.5 - width, y1, x1, 0, 0);
			tessellator.addVertexWithUV(0.5, y3, x3, 0, 1);

			tessellator.addVertexWithUV(0.5, y3, x3, 0, 1);
			tessellator.addVertexWithUV(0.5 - width, y2, x2, 0, 0);
			tessellator.addVertexWithUV(0.5 + width, y2, x2, 1, 1);
		}

		tessellator.draw();

	}

	@Override
	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		return tileentity.getHasAxle();
	}

	public FrameComponent setCuttingPowerByte(byte cuttingPower) {
		this.cuttingPower = cuttingPower;
		return this;
	}
	
}
