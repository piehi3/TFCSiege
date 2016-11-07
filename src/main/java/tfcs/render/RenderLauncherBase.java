package tfcs.render;

import org.lwjgl.opengl.GL11;

import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityLauncherBase;
import tfcs.util.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class RenderLauncherBase extends RenderBase implements IItemRenderer {
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityLauncherBase te = (TileEntityLauncherBase) tileentity;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(ReferenceResource.WOODEN_COMPONENET_TEXTURE);
		render(tessellator, te);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void render(Tessellator tessellator, TileEntityLauncherBase te) {
		GL11.glTranslated(0.5, 0.5, 0.5);
		GL11.glRotated(te.getYaw(), 0, 1, 0);
		GL11.glTranslated(-0.5, -0.5, -0.5);
		GL11.glRotatef(90, 0, 0, 1);
		RenderHelper.renderFilledPolygon(tessellator, 2, 8, 0.6, 0.1, -0.4, -1, 0);
		GL11.glRotatef(-90, 0, 0, 1);
		RenderHelper.renderArcRectangle(tessellator, 0.8, 0, 0.2, 0, 0.4, 0.4);
		RenderHelper.renderArcRectangle(tessellator, 0, 0.8, 0.2, 0, 0.4, 0.4);
		double r = 0.05;
		RenderHelper.renderArcRectangle(tessellator, 0.2, 0.2, 0.2 + r, 0.6 + r, 0.4 + r, 0.4 + r);
		if (te.getComponent() != null) {
			this.bindTexture(te.getComponent().getResourceLocation());
			te.getComponent().render(te, tessellator, te.getPitch());
		}
		GL11.glTranslated(0.5, 0.5, 0.5);
		GL11.glRotated(-te.getYaw(), 0, 1, 0);
		GL11.glTranslated(-0.5, -0.5, -0.5);
	}

	private void renderWithOutTile(Tessellator tessellator, ItemStack is) {
		GL11.glRotatef(90, 0, 0, 1);
		RenderHelper.renderFilledPolygon(tessellator, 2, 8, 0.6, 0.1, -0.4, -1, 0);
		GL11.glRotatef(-90, 0, 0, 1);
		RenderHelper.renderArcRectangle(tessellator, 0.8, 0, 0.2, 0, 0.4, 0.4);
		RenderHelper.renderArcRectangle(tessellator, 0, 0.8, 0.2, 0, 0.4, 0.4);
		double r = 0.05;
		RenderHelper.renderArcRectangle(tessellator, 0.2, 0.2, 0.2 + r, 0.6 + r, 0.4 + r, 0.4 + r);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOODEN_COMPONENET_TEXTURE);
		renderWithOutTile(tessellator, item);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
