package tfcs.util;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import tfcs.tileentities.TileEntityGearFrame;

public class RenderHelper {

	public static void renderCoil(Tessellator tessellator, double r, double subDiv, double size, double width, double maxRotation, double w) {
		double a = 360 / subDiv;
		double s = size / 10;
		tessellator.startDrawingQuads();
		for (double i = 0; i < maxRotation * 360;) {
			double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5, x1c = 0.5, y1c = 0.5, x2c = 0.5, y2c = 0.5;

			double v = -i * 0.00001;

			x1 += size * CalculationHelper.sin(i + r);
			y1 += size * CalculationHelper.cos(i + r);

			x1c += (width + size) * CalculationHelper.sin(i + r);
			y1c += (width + size) * CalculationHelper.cos(i + r);

			i += a;
			size += s;

			x2 += size * CalculationHelper.sin(i + r);
			y2 += size * CalculationHelper.cos(i + r);

			x2c += (width + size) * CalculationHelper.sin(i + r);
			y2c += (width + size) * CalculationHelper.cos(i + r);

			tessellator.addVertexWithUV(0.5 + w + v, y1, x1, 0, 0);
			tessellator.addVertexWithUV(0.5 + w + v, y2, x2, 1, 0);
			tessellator.addVertexWithUV(0.5 - w - v, y2, x2, 1, 1);
			tessellator.addVertexWithUV(0.5 - w - v, y1, x1, 0, 1);

			tessellator.addVertexWithUV(0.5 - w - v, y1c, x1c, 0, 0);
			tessellator.addVertexWithUV(0.5 - w - v, y2c, x2c, 1, 0);
			tessellator.addVertexWithUV(0.5 + w + v, y2c, x2c, 1, 1);
			tessellator.addVertexWithUV(0.5 + w + v, y1c, x1c, 0, 1);

			tessellator.addVertexWithUV(0.5 + w + v, y1, x1, 0, 0);
			tessellator.addVertexWithUV(0.5 + w + v, y1c, x1c, 1, 0);
			tessellator.addVertexWithUV(0.5 + w + v, y2c, x2c, 1, 1);
			tessellator.addVertexWithUV(0.5 + w + v, y2, x2, 0, 1);

			tessellator.addVertexWithUV(0.5 - w - v, y2, x2, 0, 1);
			tessellator.addVertexWithUV(0.5 - w - v, y2c, x2c, 1, 1);
			tessellator.addVertexWithUV(0.5 - w - v, y1c, x1c, 1, 0);
			tessellator.addVertexWithUV(0.5 - w - v, y1, x1, 0, 0);
		}

		tessellator.draw();
	}

	public static void renderCircle(Tessellator tessellator, double r, double subDiv, double size, double width, double maxRotation, double w, double x, double y, double z) {
		double a = 360 / subDiv;
		tessellator.startDrawingQuads();
		for (double i = 0; i < maxRotation * 360;) {
			double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5, x1c = 0.5, y1c = 0.5, x2c = 0.5, y2c = 0.5;

			x1 += size * CalculationHelper.sin(i + r);
			y1 += size * CalculationHelper.cos(i + r);

			x1c += (width + size) * CalculationHelper.sin(i + r);
			y1c += (width + size) * CalculationHelper.cos(i + r);

			i += a;

			x2 += size * CalculationHelper.sin(i + r);
			y2 += size * CalculationHelper.cos(i + r);

			x2c += (width + size) * CalculationHelper.sin(i + r);
			y2c += (width + size) * CalculationHelper.cos(i + r);

			tessellator.addVertexWithUV(0.5 + w + x, y1 + y, x1 + z, 0, 0);
			tessellator.addVertexWithUV(0.5 + w + x, y2 + y, x2 + z, 1, 0);
			tessellator.addVertexWithUV(0.5 - w + x, y2 + y, x2 + z, 1, 1);
			tessellator.addVertexWithUV(0.5 - w + x, y1 + y, x1 + z, 0, 1);

			tessellator.addVertexWithUV(0.5 - w + x, y1c + y, x1c + z, 0, 0);
			tessellator.addVertexWithUV(0.5 - w + x, y2c + y, x2c + z, 1, 0);
			tessellator.addVertexWithUV(0.5 + w + x, y2c + y, x2c + z, 1, 1);
			tessellator.addVertexWithUV(0.5 + w + x, y1c + y, x1c + z, 0, 1);

			tessellator.addVertexWithUV(0.5 + w + x, y1 + y, x1 + z, 0, 0);
			tessellator.addVertexWithUV(0.5 + w + x, y1c + y, x1c + z, 1, 0);
			tessellator.addVertexWithUV(0.5 + w + x, y2c + y, x2c + z, 1, 1);
			tessellator.addVertexWithUV(0.5 + w + x, y2 + y, x2 + z, 0, 1);

			tessellator.addVertexWithUV(0.5 - w + x, y2 + y, x2 + z, 0, 1);
			tessellator.addVertexWithUV(0.5 - w + x, y2c + y, x2c + z, 1, 1);
			tessellator.addVertexWithUV(0.5 - w + x, y1c + y, x1c + z, 1, 0);
			tessellator.addVertexWithUV(0.5 - w + x, y1 + y, x1 + z, 0, 0);
		}

		tessellator.draw();
	}

	public static void renderPolygon(TileEntityGearFrame tileentity, Tessellator tessellator, double r, double subDiv, double size, double width, boolean hasTeeth, int toothSize, double x, double y,
			double z) {
		double a = 360 / subDiv;
		tessellator.startDrawing(GL11.GL_TRIANGLES);
		for (double i = 0; i < 360;) {
			double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5;

			x1 += size * CalculationHelper.sin(i + r);
			y1 += size * CalculationHelper.cos(i + r);

			i += a;

			x2 += size * CalculationHelper.sin(i + r);
			y2 += size * CalculationHelper.cos(i + r);

			tessellator.addVertexWithUV(0.5 + width + x, 0.5 + y, 0.5 + z, 0, 0);
			tessellator.addVertexWithUV(0.5 + width + x, y1 + y, x1 + z, 0, 1);
			tessellator.addVertexWithUV(0.5 + width + x, y2 + y, x2 + z, 1, 1);

			tessellator.addVertexWithUV(0.5 - width + x, 0.5 + y, 0.5 + z, 0, 0);
			tessellator.addVertexWithUV(0.5 - width + x, y2 + y, x2 + z, 1, 1);
			tessellator.addVertexWithUV(0.5 - width + x, y1 + y, x1 + z, 0, 1);
		}

		tessellator.draw();

		if (!hasTeeth) {
			tessellator.startDrawingQuads();
			for (double i = 0; i < 360;) {
				double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5;

				x1 += size * CalculationHelper.sin(i + r);
				y1 += size * CalculationHelper.cos(i + r);

				i += a;

				x2 += size * CalculationHelper.sin(i + r);
				y2 += size * CalculationHelper.cos(i + r);

				tessellator.addVertexWithUV(0.5 - width + x, y1 + y, x1 + z, 0, 0);
				tessellator.addVertexWithUV(0.5 - width + x, y2 + y, x2 + z, 1, 0);
				tessellator.addVertexWithUV(0.5 + width + x, y2 + y, x2 + z, 1, 1);
				tessellator.addVertexWithUV(0.5 + width + x, y1 + y, x1 + z, 0, 1);
			}

			tessellator.draw();
		} else {
			tessellator.startDrawing(GL11.GL_TRIANGLES);
			for (double i = 0; i < 360;) {
				double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5, x3 = 0.5, y3 = 0.5;

				x1 += size * CalculationHelper.sin(i + r);
				y1 += size * CalculationHelper.cos(i + r);

				i += a;

				x2 += size * CalculationHelper.sin(i + r);
				y2 += size * CalculationHelper.cos(i + r);

				x3 += (((toothSize + 1) * size) / toothSize) * CalculationHelper.sin(i - (a / 2) + r);
				y3 += (((toothSize + 1) * size) / toothSize) * CalculationHelper.cos(i - (a / 2) + r);

				tessellator.addVertexWithUV(0.5 - width + x, y1 + y, x1 + z, 0, 0);
				tessellator.addVertexWithUV(0.5 - width + x, y2 + y, x2 + z, 1, 1);
				tessellator.addVertexWithUV(0.5 + x, y3 + y, x3 + z, 0, 1);

				tessellator.addVertexWithUV(0.5 + width + x, y2 + y, x2 + z, 1, 1);
				tessellator.addVertexWithUV(0.5 + width + x, y1 + y, x1 + z, 0, 0);
				tessellator.addVertexWithUV(0.5 + x, y3 + y, x3 + z, 0, 1);

				tessellator.addVertexWithUV(0.5 + width + x, y1 + y, x1 + z, 1, 1);
				tessellator.addVertexWithUV(0.5 - width + x, y1 + y, x1 + z, 0, 0);
				tessellator.addVertexWithUV(0.5 + x, y3 + y, x3 + z, 0, 1);

				tessellator.addVertexWithUV(0.5 + x, y3 + y, x3 + z, 0, 1);
				tessellator.addVertexWithUV(0.5 - width + x, y2 + y, x2 + z, 0, 0);
				tessellator.addVertexWithUV(0.5 + width + x, y2 + y, x2 + z, 1, 1);
			}
			tessellator.draw();
		}
	}

	public static void renderFilledPolygon(Tessellator tessellator, double r, double subDiv, double size, double width, double x, double y, double z) {
		double a = 360 / subDiv;
		tessellator.startDrawing(GL11.GL_TRIANGLES);
		for (double i = 0; i < 360;) {
			double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5;

			x1 += size * CalculationHelper.sin(i + r);
			y1 += size * CalculationHelper.cos(i + r);

			i += a;

			x2 += size * CalculationHelper.sin(i + r);
			y2 += size * CalculationHelper.cos(i + r);

			tessellator.addVertexWithUV(0.5 + width + x, 0.5 + y, 0.5 + z, 0, 0);
			tessellator.addVertexWithUV(0.5 + width + x, y1 + y, x1 + z, 0, 1);
			tessellator.addVertexWithUV(0.5 + width + x, y2 + y, x2 + z, 1, 1);

			tessellator.addVertexWithUV(0.5 - width + x, 0.5 + y, 0.5 + z, 0, 0);
			tessellator.addVertexWithUV(0.5 - width + x, y2 + y, x2 + z, 1, 1);
			tessellator.addVertexWithUV(0.5 - width + x, y1 + y, x1 + z, 0, 1);
		}

		tessellator.draw();

		tessellator.startDrawingQuads();
		for (double i = 0; i < 360;) {
			double x1 = 0.5, y1 = 0.5, x2 = 0.5, y2 = 0.5;

			x1 += size * CalculationHelper.sin(i + r);
			y1 += size * CalculationHelper.cos(i + r);

			i += a;

			x2 += size * CalculationHelper.sin(i + r);
			y2 += size * CalculationHelper.cos(i + r);

			tessellator.addVertexWithUV(0.5 - width + x, y1 + y, x1 + z, 0, 0);
			tessellator.addVertexWithUV(0.5 - width + x, y2 + y, x2 + z, 1, 0);
			tessellator.addVertexWithUV(0.5 + width + x, y2 + y, x2 + z, 1, 1);
			tessellator.addVertexWithUV(0.5 + width + x, y1 + y, x1 + z, 0, 1);
		}

		tessellator.draw();
	}

	public static void renderRectangle(Tessellator tessellator, double x1, double x2, double y1, double y2, double z1, double z2) {
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 0 - x2, 0 - y2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 1 + x1, 0 - y2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 1 + x1, 1 + y2);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 0 - x2, 1 + y2);

		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - z2, 0 - y2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + z1, 0 - y2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 1 + z1, 1 + y1);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 1 + z1, 1 + y1);

		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 0 - z2, 0 - y2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 1 + z1, 0 - y2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + z1, 1 + y1);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - z2, 1 + y1);

		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - x2, 0 - y2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + x1, 0 - y2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + x1, 1 + y1);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - x2, 1 + y1);

		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - x2, 0 - z2);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 1 + x1, 0 - z2);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 1 + x1, 1 + z1);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - x2, 1 + z1);

		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 0 - x2, 0 - z2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + x1, 0 - z2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + x1, 1 + z1);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 0 - x2, 1 + z1);
		tessellator.draw();
	}

	public static void renderArcRectangle(Tessellator tessellator, double x1, double x2, double y1, double y2, double z1, double z2) {
		tessellator.startDrawingQuads();
		x1 = -x1;
		y1 = -y1;
		z1 = -z1;
		x2 = -x2;
		y2 = -y2;
		z2 = -z2;
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 0 - y2, 0 - x2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 1 + y1, 0 - x2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 1 + y1, 1 + x1);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 0 - y2, 1 + x1);

		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - y2, 0 - z2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + y1, 0 - z2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 1 + y1, 1 + z1);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 0 - y2, 1 + z1);

		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 0 - y2, 0 - z2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 1 + y1, 0 - z2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + y1, 1 + z1);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - y2, 1 + z1);

		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - y2, 0 - x2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + y1, 0 - x2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + y1, 1 + x1);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - y2, 1 + x1);

		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - z2, 0 - x2);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 1 + z1, 0 - x2);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 1 + z1, 1 + x1);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - z2, 1 + x1);

		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 0 - z2, 0 - x2);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + z1, 0 - x2);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + z1, 1 + x1);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 0 - z2, 1 + x1);
		tessellator.draw();
	}
	
	public static void renderArcRectangleWithMovingTexture(Tessellator tessellator, double x1, double x2, double y1, double y2, double z1, double z2,double t) {
		tessellator.startDrawingQuads();
		x1 = -x1;
		y1 = -y1;
		z1 = -z1;
		x2 = -x2;
		y2 = -y2;
		z2 = -z2;
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 0 - y2, 0 - x2 - t);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 1 + y1, 0 - x2 - t);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 1 + y1, 1 + x1 - t);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 0 - y2, 1 + x1 - t);

		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - y2, 0 - z2 - t);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + y1, 0 - z2 - t);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 1 + y1, 1 + z1 - t);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 0 - y2, 1 + z1 - t);

		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 0 - y2, 0 - z2 - t);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 1 + y1, 0 - z2 - t);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + y1, 1 + z1 - t);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - y2, 1 + z1 - t);

		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - y2, 0 - x2 - t);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + y1, 0 - x2 - t);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + y1, 1 + x1 - t);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - y2, 1 + x1 - t);

		tessellator.addVertexWithUV(0 - x2, 0 - y2, 1 + z1, 0 - z2, 0 - x2 - t);
		tessellator.addVertexWithUV(0 - x2, 0 - y2, 0 - z2, 1 + z1, 0 - x2 - t);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 0 - z2, 1 + z1, 1 + x1 - t);
		tessellator.addVertexWithUV(1 + x1, 0 - y2, 1 + z1, 0 - z2, 1 + x1 - t);

		tessellator.addVertexWithUV(0 - x2, 1 + y1, 0 - z2, 0 - z2, 0 - x2 - t);
		tessellator.addVertexWithUV(0 - x2, 1 + y1, 1 + z1, 1 + z1, 0 - x2 - t);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 1 + z1, 1 + z1, 1 + x1 - t);
		tessellator.addVertexWithUV(1 + x1, 1 + y1, 0 - z2, 0 - z2, 1 + x1 - t);
		tessellator.draw();
	}
	
	public static void renderArc() {
		// TODO Auto-generated method stub

	}
	
}
