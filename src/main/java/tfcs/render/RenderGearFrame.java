package tfcs.render;

import org.lwjgl.opengl.GL11;

import tfcs.core.TFCSBlocks;
import tfcs.gears.GearComponent;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderGearFrame extends RenderBase {

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityGearFrame te = (TileEntityGearFrame) tileentity;

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GL11.glRotated(90, te.getAxe(1), te.getAxe(2), te.getAxe(3));
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glDisable(GL11.GL_LIGHTING);
		render(te, tessellator, te.getRotation());
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

	}

	private void render(TileEntityGearFrame tileentity, Tessellator tessellator, double r) {
		tessellator.setBrightness(tileentity.getBlockType().getMixedBrightnessForBlock(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) - 1);
		renderFrame(tileentity, tessellator);
		if (tileentity.getRenderHasGear())
			if (tileentity.getGearComponent().isShouldRender())
				renderGear(tileentity, tessellator, r);
		if (tileentity.getHasAxle())
			if (tileentity.getAxleComponent().isShouldRender())
				renderAxle(tileentity, tessellator, r);
		if (tileentity.getHasTool())
			if (tileentity.getToolComponent().isShouldRender())
				renderTool(tileentity, tessellator, r);
	}

	private void renderTool(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		this.bindTexture(tileentity.getToolComponent().getResourceLocationFromTileEntity(tileentity));
		tileentity.getToolComponent().render(tileentity, tessellator, rotation);
	}

	private void renderAxle(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		this.bindTexture(tileentity.getAxleComponent().getResourceLocation());
		RenderHelper.renderPolygon(tileentity, tessellator, rotation, 8, 0.1, 0.5, false, 0, 0, 0, 0);
	}

	private void renderGear(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		GearComponent g = tileentity.getGearComponent();
		double d = (0.5 + g.getDepth()) * tileentity.getAxeLocation();
		if (tileentity.isOffset())
			d = 0.2;
		this.bindTexture(g.getResourceLocation());
		RenderHelper.renderPolygon(tileentity, tessellator, rotation, g.getGearSubDivid(), g.getRenderSize(), g.getDepth(), g.getHasTeeth(), g.getToothSize(), d, 0, 0);
	}

	private void renderFrame(TileEntityGearFrame te, Tessellator tessellator) {
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glRotated(-90, te.getAxe(1), te.getAxe(2), te.getAxe(3));
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		this.bindTexture(ReferenceResource.WOODEN_COMPONENET_TEXTURE);
		renderCorners(te, tessellator);
		renderArms(te, tessellator);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glRotated(90, te.getAxe(1), te.getAxe(2), te.getAxe(3));
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void renderCorners(TileEntityGearFrame tileentity, Tessellator tessellator) {
		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0, 0.9, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0, 0.9, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0, 0.9, 0.9, 0);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0, 0.9, 0.9, 0);

		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.9, 0, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.9, 0, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.9, 0, 0.9, 0);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.9, 0, 0.9, 0);
	}

	private void renderArms(TileEntityGearFrame tileentity, Tessellator tessellator) {
		boolean[] conect = getConections(tileentity);
		if (conect[2] && conect[4])
			RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0, 0.9, 0, 0.9);
		if (conect[1] && conect[2])
			RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0, 0.9, 0.1, 0.1);
		if (conect[2] && conect[5])
			RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0, 0.9, 0.9, 0);
		if (conect[0] && conect[2])
			RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0, 0.9, 0.1, 0.1);
		if (conect[3] && conect[4])
			RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0.9, 0, 0, 0.9);
		if (conect[1] && conect[3])
			RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.9, 0, 0.1, 0.1);
		if (conect[3] && conect[5])
			RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0.9, 0, 0.9, 0);
		if (conect[0] && conect[3])
			RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.9, 0, 0.1, 0.1);
		if (conect[0] && conect[4])
			RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.1, 0.1, 0, 0.9);
		if (conect[1] && conect[4])
			RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.1, 0.1, 0, 0.9);
		if (conect[0] && conect[5])
			RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.1, 0.1, 0.9, 0);
		if (conect[1] && conect[5])
			RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.1, 0.1, 0.9, 0);
	}

	private boolean[] getConections(TileEntityGearFrame te) {
		return new boolean[] { isConectedBlock(te, 1, 0, 0), isConectedBlock(te, -1, 0, 0), isConectedBlock(te, 0, 1, 0), isConectedBlock(te, 0, -1, 0), isConectedBlock(te, 0, 0, 1),
				isConectedBlock(te, 0, 0, -1) };
	}

	private boolean isConectedBlock(TileEntityGearFrame te, int x, int y, int z) {
		return !te.getWorldObj().getBlock(te.xCoord + x, te.yCoord + y, te.zCoord + z).getUnlocalizedName().equals(TFCSBlocks.gearFrame.getUnlocalizedName());
	}

}
