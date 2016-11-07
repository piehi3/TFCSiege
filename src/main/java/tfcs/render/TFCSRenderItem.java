package tfcs.render;

import org.lwjgl.opengl.GL11;

import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class TFCSRenderItem implements IItemRenderer {

	public TFCSRenderItem() {
		// TODO Auto-generated constructor stub
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
			return (helper == ItemRendererHelper.EQUIPPED_BLOCK);
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
		renderFrame();
	}

	private void renderFrame() {
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOODEN_COMPONENET_TEXTURE);
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glDisable(GL11.GL_LIGHTING);
		renderCorners(tessellator);
		renderArms(tessellator);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void renderCorners(Tessellator tessellator) {
		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0, 0.9, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0, 0.9, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0, 0.9, 0.9, 0);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0, 0.9, 0.9, 0);

		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.9, 0, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.9, 0, 0, 0.9);
		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.9, 0, 0.9, 0);
		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.9, 0, 0.9, 0);
	}

	private void renderArms(Tessellator tessellator) {
		RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0, 0.9, 0, 0.9);

		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0, 0.9, 0.1, 0.1);

		RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0, 0.9, 0.9, 0);

		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0, 0.9, 0.1, 0.1);

		RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0.9, 0, 0, 0.9);

		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.9, 0, 0.1, 0.1);

		RenderHelper.renderArcRectangle(tessellator, 0.1, 0.1, 0.9, 0, 0.9, 0);

		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.9, 0, 0.1, 0.1);

		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.1, 0.1, 0, 0.9);

		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.1, 0.1, 0, 0.9);

		RenderHelper.renderArcRectangle(tessellator, 0, 0.9, 0.1, 0.1, 0.9, 0);

		RenderHelper.renderArcRectangle(tessellator, 0.9, 0, 0.1, 0.1, 0.9, 0);
	}

}
