package tfcs.render;

import org.lwjgl.opengl.GL11;

import tfcs.core.TFCSBlocks;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityAqueduct;
import tfcs.util.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

public class RenderAqueduct extends TileEntitySpecialRenderer implements IItemRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityAqueduct te = (TileEntityAqueduct) tileentity;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(ReferenceResource.BRICKS_COMPONENET_TEXTURE);
		render(tessellator, te);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void render(Tessellator tessellator, TileEntityAqueduct te) {
		RenderHelper.renderArcRectangle(tessellator, 0, 0, 0, 0, 0, 0);
		double h = te.getRenderWater();
		if (h > 0) {
			// GL11.glEnable(GL11.GL_BLEND);
			this.bindTexture(ReferenceResource.WATER_COMPONENET_TEXTURE);
			int t = 1;
			RenderHelper.renderArcRectangleWithMovingTexture(tessellator, 0, 0, 0 - te.getRenderWater(), 1, 0, 0, 0);

			if (shouldDrop(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord))
				RenderHelper.renderArcRectangleWithMovingTexture(tessellator, 0, 0, 0, 0, 0, 0, 0);
			// GL11.glDisable(GL11.GL_BLEND);
		}
	}

	private boolean shouldDrop(World world, int x, int y, int z) {
		if (isFull(world, x + 1, y - 1, z))
			return true;
		if (isFull(world, x - 1, y - 1, z))
			return true;
		if (isFull(world, x, y - 1, z + 1))
			return true;
		if (isFull(world, x, y - 1, z - 1))
			return true;
		return false;
	}

	private boolean isFull(World world, int x, int y, int z) {
		if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityAqueduct)
			if (((TileEntityAqueduct) world.getTileEntity(x, y, z)).getRenderWater() > 0)
				return true;
		return false;
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
		Minecraft.getMinecraft().renderEngine.bindTexture(ReferenceResource.BRICKS_COMPONENET_TEXTURE);
		RenderHelper.renderArcRectangle(tessellator, 0, 0, 0, 0, 0, 0);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
