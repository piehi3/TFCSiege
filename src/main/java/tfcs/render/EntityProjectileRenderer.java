package tfcs.render;

import org.lwjgl.opengl.GL11;

import tfcs.reference.ReferenceResource;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityProjectileRenderer extends Render {

	public EntityProjectileRenderer() {
		super();
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float sizeX, float sizeY) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glDisable(GL11.GL_LIGHTING);
		render(tessellator);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void render(Tessellator tessellator) {
		double barrelSize = 0.2;
		tessellator.startDrawingQuads();
		// Outside
		tessellator.addVertexWithUV(1 - barrelSize, 0 + barrelSize, 0 + barrelSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 0 + barrelSize, 0 + barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 1 - barrelSize, 0 + barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1 - barrelSize, 0 + barrelSize, 0, 0);

		tessellator.addVertexWithUV(0 + barrelSize, 0 + barrelSize, 0 + barrelSize, 0, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 0 + barrelSize, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(0 + barrelSize, 1 - barrelSize, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 1 - barrelSize, 0 + barrelSize, 0, 0);

		tessellator.addVertexWithUV(1 - barrelSize, 1 - barrelSize, 1 - barrelSize, 0, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 1 - barrelSize, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 0 + barrelSize, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0 + barrelSize, 1 - barrelSize, 0, 1);

		tessellator.addVertexWithUV(1 - barrelSize, 1 - barrelSize, 0 + barrelSize, 0, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1 - barrelSize, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 0 + barrelSize, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0 + barrelSize, 0 + barrelSize, 0, 1);

		tessellator.addVertexWithUV(0 + barrelSize, 1 - barrelSize, 0 + barrelSize, 0, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 1 - barrelSize, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 1 - barrelSize, 1 - barrelSize, 1, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 1 - barrelSize, 0 + barrelSize, 0, 1);
		
		tessellator.addVertexWithUV(0 + barrelSize, 0 + barrelSize, 1 - barrelSize, 1, 0);
		tessellator.addVertexWithUV(0 + barrelSize, 0 + barrelSize, 0 + barrelSize, 0, 0);
		tessellator.addVertexWithUV(1 - barrelSize, 0 + barrelSize, 0 + barrelSize, 0, 1);
		tessellator.addVertexWithUV(1 - barrelSize, 0 + barrelSize, 1 - barrelSize, 1, 1);

		tessellator.draw();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ReferenceResource.IRON_COMPONENET_TEXTURE;
	}

}
