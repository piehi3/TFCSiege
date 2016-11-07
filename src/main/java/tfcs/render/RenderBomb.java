package tfcs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import tfcs.core.TFCSBlocks;
import tfcs.core.TFCSItems;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityBomb;
import tfcs.util.BlockHelper;
import tfcs.util.CalculationHelper;
import tfcs.util.RenderHelper;

public class RenderBomb extends RenderBase implements IItemRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityBomb te = (TileEntityBomb) tileentity;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glDisable(GL11.GL_LIGHTING);
		if (te.getType() == 1)
			this.bindTexture(ReferenceResource.CERAMIC_COMPONENET_TEXTURE);
		else if (te.getType() == 2)
			this.bindTexture(ReferenceResource.IRON_COMPONENET_TEXTURE);
		render(tessellator, te);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void render(Tessellator tessellator, TileEntityBomb te) {

		GL11.glRotated(90, 0, 0, 1);
		RenderHelper.renderCircle(tessellator, 0, 8, 0.4, 0.1, 1, 0.4, -0.1, -1, 0);
		GL11.glRotated(-90, 0, 0, 1);

		GL11.glRotated(90, 0, 0, 1);
		RenderHelper.renderFilledPolygon(tessellator, 0, 8, 0.4, 0.05, -0.45, -1, 0);
		GL11.glRotated(-90, 0, 0, 1);

		if (te.isClosed()) {
			GL11.glRotated(90, 0, 0, 1);
			RenderHelper.renderFilledPolygon(tessellator, 0, 8, 0.4, 0.05, 0.35, -1, 0);
			GL11.glRotated(-90, 0, 0, 1);

			this.bindTexture(ReferenceResource.WOOL_COMPONENET_TEXTURE);
			double w2 = 0.45;
			RenderHelper.renderArcRectangle(tessellator, w2, w2, 0, 0.5, w2, w2);
		}

		if (BlockHelper.doesBlockHaveGunPowder(te)) {
			this.bindTexture(ReferenceResource.GUNPOWDER_COMPONENET_TEXTURE);
			GL11.glRotated(90, 0, 0, 1);
			RenderHelper.renderFilledPolygon(tessellator, 0, 8, 0.4, 0.0, -0.35 + BlockHelper.getRenderGunpowder(te, 0.6), -1, 0);
			GL11.glRotated(-90, 0, 0, 1);
		}
	}

	private void renderWithOutTile(Tessellator tessellator, ItemStack is) {
		GL11.glRotated(90, 0, 0, 1);
		RenderHelper.renderCircle(tessellator, 0, 8, 0.4, 0.1, 1, 0.4, -0.1, -1, 0);
		GL11.glRotated(-90, 0, 0, 1);

		GL11.glRotated(90, 0, 0, 1);
		RenderHelper.renderFilledPolygon(tessellator, 0, 8, 0.4, 0.05, -0.45, -1, 0);
		GL11.glRotated(-90, 0, 0, 1);

		GL11.glRotated(90, 0, 0, 1);
		RenderHelper.renderFilledPolygon(tessellator, 0, 8, 0.4, 0.05, 0.35, -1, 0);
		GL11.glRotated(-90, 0, 0, 1);

		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOOL_COMPONENET_TEXTURE);
		double w2 = 0.45;
		RenderHelper.renderArcRectangle(tessellator, w2, w2, 0, 0.5, w2, w2);

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
		if (item.getItem().equals(Item.getItemFromBlock(TFCSBlocks.ceramicBomb)))
			Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.CERAMIC_COMPONENET_TEXTURE);
		else if (item.getItem().equals(Item.getItemFromBlock(TFCSBlocks.ironBomb)))
			Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.IRON_COMPONENET_TEXTURE);
		renderWithOutTile(tessellator, item);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
