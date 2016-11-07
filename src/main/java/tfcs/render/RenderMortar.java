package tfcs.render;

import org.lwjgl.opengl.GL11;

import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityMortar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class RenderMortar extends RenderBase implements IItemRenderer {
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		double wallSize = 0.1;
		double barrelSize = 0.2;
		TileEntityMortar te = (TileEntityMortar) tileentity;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glTranslated(0.5, -0.3, 0.5);
		GL11.glRotated(te.getRenderPitch(), 1, 0, 0);
		GL11.glRotated(te.getRenderYaw(), 0, 0, 1);
		GL11.glTranslated(-0.5, 0.3, -0.5);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(ReferenceResource.CERAMIC_COMPONENET_TEXTURE);
		render(tessellator, wallSize, barrelSize);
		if (te.getGunPowder() > 0) {
			this.bindTexture(ReferenceResource.GUNPOWDER_COMPONENET_TEXTURE);
			renderGunPowerder(tessellator, wallSize, barrelSize, te);
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

	}

	private void renderGunPowerder(Tessellator tessellator, double wallSize, double barrelSize, TileEntityMortar te) {
		tessellator.startDrawingQuads();
		double c = wallSize + barrelSize;
		double a = (te.getGunPowder() / te.getMaxGunPowder()) * 0.7;
		tessellator.addVertexWithUV(0 + c, a, 0 + c, 0, 1);
		tessellator.addVertexWithUV(0 + c, a, 1 - c, 1, 1);
		tessellator.addVertexWithUV(1 - c, a, 1 - c, 1, 0);
		tessellator.addVertexWithUV(1 - c, a, 0 + c, 0, 0);

		tessellator.draw();
	}

	private void render(Tessellator tessellator, double wallSize, double barrelSize) {
		tessellator.startDrawingQuads();
		// Outside
		tessellator.addVertexWithUV(1 - barrelSize, 0, 0 + barrelSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1, 0 + barrelSize, 0, 0);

		tessellator.addVertexWithUV(0 + barrelSize, 0, 0 + barrelSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 0 + barrelSize, 0, 0);

		tessellator.addVertexWithUV(1 - barrelSize, 1, 1 - barrelSize, 0, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0, 1 - barrelSize, 0, 1);

		tessellator.addVertexWithUV(1 - barrelSize, 1, 0 + barrelSize, 0, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 0, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0, 0 + barrelSize, 0, 1);

		barrelSize += wallSize;
		// Inside
		tessellator.addVertexWithUV(1 - barrelSize, 1, 0 + barrelSize, 0, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0, 0 + barrelSize, 0, 1);

		tessellator.addVertexWithUV(0 + barrelSize, 1, 0 + barrelSize, 0, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 0 + barrelSize, 0, 1);

		tessellator.addVertexWithUV(1 - barrelSize, 0, 1 - barrelSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1, 1 - barrelSize, 0, 0);

		tessellator.addVertexWithUV(1 - barrelSize, 0, 0 + barrelSize, 0, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 1, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1, 0 + barrelSize, 0, 0);

		// Top
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 1, 1 - barrelSize + wallSize, 0, 0);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 1, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 1, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 1, 1 - barrelSize + wallSize, 0, 1);

		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 1, 0 + barrelSize - wallSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 1, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 1, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 1, 0 + barrelSize - wallSize, 0, 0);

		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 1, 1 - barrelSize, 0, 0);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 1, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 1, 1 - barrelSize, 0, 1);

		tessellator.addVertexWithUV(0 + barrelSize, 1, 1 - barrelSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 1, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 1, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 1, 1 - barrelSize, 0, 0);

		// Bottom
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 0, 1 - barrelSize + wallSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 0, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 0, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 0, 1 - barrelSize + wallSize, 0, 0);

		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 0, 0 + barrelSize - wallSize, 0, 0);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 0, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 0, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 0, 0 + barrelSize - wallSize, 0, 1);

		tessellator.addVertexWithUV(1 - barrelSize, 0, 1 - barrelSize, 0, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 0, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize + wallSize, 0, 1 - barrelSize, 0, 0);

		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 0, 1 - barrelSize, 0, 0);
		tessellator.addVertexWithUV(0 + barrelSize - wallSize, 0, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 0, 1 - barrelSize, 0, 1);

		tessellator.draw();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch (type) {
		case ENTITY: {
			return (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION || helper == ItemRendererHelper.BLOCK_3D);
		}
		case EQUIPPED: {
			return (helper == ItemRendererHelper.BLOCK_3D || helper == ItemRendererHelper.EQUIPPED_BLOCK);
		}
		case EQUIPPED_FIRST_PERSON: {
			return (helper == ItemRendererHelper.BLOCK_3D || helper == ItemRendererHelper.EQUIPPED_BLOCK);
		}
		case INVENTORY: {
			return (helper == ItemRendererHelper.INVENTORY_BLOCK);
		}
		default: {
			return false;
		}
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.CERAMIC_COMPONENET_TEXTURE);
		render(tessellator, 0.1, 0.2);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
