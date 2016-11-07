package tfcs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import tfcs.reference.ReferenceResource;
import tfcs.util.NBTHelper;

public class RenderLongBow implements IItemRenderer {

	public RenderLongBow() {
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
		renderBow(type, item);
	}

	private void renderBow(ItemRenderType type, ItemStack itemstack) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		switch (type) {
		case ENTITY:
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			break;
		case EQUIPPED:
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			GL11.glRotatef(-45, 0, 1, 0);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			GL11.glTranslatef(0.5F, -0.2F, 0.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			GL11.glRotatef(70, 0, 0, 1);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			GL11.glRotatef(-135, 0, 1, 0);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			if(NBTHelper.getInt(itemstack, "PRE_LOADED")<10){
				GL11.glTranslatef(0.5F, 0.5F, 0.5F);
				GL11.glRotatef(-15, 0, 0, 1);
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			}
			break;
		case INVENTORY:
			GL11.glScaled(1, 0.4, 1);
			GL11.glTranslatef(0.0F, 1.0F, 0.0F);
			break;
		default:
			break;
		}
		drawMainBow(tessellator, ((double) NBTHelper.getInt(itemstack, "POST_LOADED")) / 200,NBTHelper.getBoolean(itemstack, "IS_LOADED"));
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void drawBowString(Tessellator tessellator, double w, double h, double h2, double h3, double s1, double s2) {
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOOL_COMPONENET_TEXTURE);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 0 + w, 0, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 0 + w, 1, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 1 - w, 1, 1);
		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 1 - w, 0, 1);

		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 1 - w, 0, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 1 - w, 1, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 0 + w, 1, 1);
		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 0 + w, 0, 1);

		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 1 - w, 0, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 1 - w, 1, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 0 + w, 1, 1);
		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 0 + w, 0, 1);

		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 0 + w, 0, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 0 + w, 1, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 1 - w, 1, 1);
		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 1 - w, 0, 1);
		tessellator.draw();
	}

	private void drawMainBow(Tessellator tessellator, double s,boolean b) {
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.IRON_COMPONENET_TEXTURE);
		drawCube(tessellator, 0.44, 0, 0, 0, 0, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOODEN_COMPONENET_TEXTURE);
		drawCube(tessellator, 0.45, 0, 2 - s, 1, 0.5 + s, 0);
		drawBowString(tessellator, 0.46, 0, 2 - s, 0.5, 0.5 + s, 0.5 + (s * 4));
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOODEN_COMPONENET_TEXTURE);
		drawCube(tessellator, 0.45, 0, -1, -2 + s, 0, 0.5 + s);
		drawBowString(tessellator, 0.46, 0, -0.5, -2 + s, 0.5 + (s * 4), 0.5 + s);
		if(b)
			drawArrow(tessellator, 0.48, 0, 0, -2, (s*4), (s*4));
	}

	private void drawArrow(Tessellator tessellator, double w, double h, double h2, double h3, double s1, double s2) {
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.WOODEN_COMPONENET_TEXTURE);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 + h + h3 + s2, 0 + w, 0 + w, 0, 0);
		tessellator.addVertexWithUV(0 + h + h3 + s2, 1 - w, 1 - w, 1, 0);
		tessellator.addVertexWithUV(1 - h + h2 + s1, 1 - w, 1 - w, 1, 1);
		tessellator.addVertexWithUV(1 - h + h2 + s1, 0 + w, 0 + w, 0, 1);

		tessellator.addVertexWithUV(1 - h + h2 + s1, 0 + w, 0 + w, 0, 0);
		tessellator.addVertexWithUV(1 - h + h2 + s1, 1 - w, 1 - w, 1, 0);
		tessellator.addVertexWithUV(0 + h + h3 + s2, 1 - w, 1 - w, 1, 1);
		tessellator.addVertexWithUV(0 + h + h3 + s2, 0 + w, 0 + w, 0, 1);

		tessellator.addVertexWithUV(0 + h + h3 + s2, 0 + w, 1 - w, 0, 0);
		tessellator.addVertexWithUV(0 + h + h3 + s2, 1 - w, 0 + w, 1, 0);
		tessellator.addVertexWithUV(1 - h + h2 + s1, 1 - w, 0 + w, 1, 1);
		tessellator.addVertexWithUV(1 - h + h2 + s1, 0 + w, 1 - w, 0, 1);

		tessellator.addVertexWithUV(1 - h + h2 + s1, 0 + w, 1 - w, 0, 0);
		tessellator.addVertexWithUV(1 - h + h2 + s1, 1 - w, 0 + w, 1, 0);
		tessellator.addVertexWithUV(0 + h + h3 + s2, 1 - w, 0 + w, 1, 1);
		tessellator.addVertexWithUV(0 + h + h3 + s2, 0 + w, 1 - w, 0, 1);
		tessellator.draw();
	}

	private void drawCube(Tessellator tessellator, double w, double h, double h2, double h3, double s1, double s2) {
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 0 + w, 0, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 0 + w, 1, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 0 + w, 1, 1);
		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 0 + w, 0, 1);

		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 1 - w, 0, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 1 - w, 1, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 0 + w, 1, 1);
		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 0 + w, 1, 1);

		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 0 + w, 0, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 0 + w, 1, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 1 - w, 1, 1);
		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 1 - w, 0, 1);

		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 1 - w, 0, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 1 - w, 1, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 1 - w, 1, 1);
		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 1 - w, 0, 1);

		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 1 - w, 0, 0);
		tessellator.addVertexWithUV(0 + w + s2, 0 + h + h3, 0 + w, 1, 0);
		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 0 + w, 1, 1);
		tessellator.addVertexWithUV(1 - w + s2, 0 + h + h3, 1 - w, 0, 1);

		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 0 + w, 0, 0);
		tessellator.addVertexWithUV(0 + w + s1, 1 - h + h2, 1 - w, 1, 0);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 1 - w, 1, 1);
		tessellator.addVertexWithUV(1 - w + s1, 1 - h + h2, 0 + w, 0, 1);
		tessellator.draw();
	}

}
