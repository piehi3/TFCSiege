package tfcs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import tfcs.reference.Reference;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityCandle;
import tfcs.util.NBTHelper;
import tfcs.util.RenderHelper;

public class RenderCandle extends RenderBase implements IItemRenderer {
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityCandle te = (TileEntityCandle) tileentity;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(ReferenceResource.TALLOW_COMPONENET_TEXTURE);
		render(tessellator, te);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void render(Tessellator tessellator, TileEntityCandle te) {
		double w = 0.3;
		double w2 = 0.45;
		double h = te.getRenderHight();
		RenderHelper.renderArcRectangle(tessellator, w, w, h, 0, w, w);
		this.bindTexture(ReferenceResource.WOOL_COMPONENET_TEXTURE);
		RenderHelper.renderArcRectangle(tessellator, w2, w2, h - 0.2, 0, w2, w2);
	}

	private void renderWithOutTile(Tessellator tessellator,ItemStack is) {
		double w = 0.3;
		double w2 = 0.45;
		double h = 0.2;
		if(NBTHelper.hasKey(is, "BURN_TIME"))
			h = 1.0-((((double)NBTHelper.getInt(is, "BURN_TIME"))/((double)Reference.MAX_CANDLE_BRUN_TIME))*0.8);
		RenderHelper.renderArcRectangle(tessellator, w, w, h, 0, w, w);
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOOL_COMPONENET_TEXTURE);
		RenderHelper.renderArcRectangle(tessellator, w2, w2, h - 0.2, 0, w2, w2);
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
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.TALLOW_COMPONENET_TEXTURE);
		renderWithOutTile(tessellator,item);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
