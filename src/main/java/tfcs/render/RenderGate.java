package tfcs.render;

import org.lwjgl.opengl.GL11;

import tfcs.reference.Reference;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGate;
import tfcs.util.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

public class RenderGate extends RenderBase implements IItemRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityGate te = (TileEntityGate) tileentity;
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + te.getRenderPostion(), (float) z);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(getTextureFromByte(getUnlocalizedName(te.getWorldObj().getBlock(te.xCoord, te.yCoord, te.zCoord).getUnlocalizedName())));
		render(tessellator, te);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void render(Tessellator tessellator, TileEntityGate te) {
		double w = 0.3;
		double d = 2 * (0.5 - w);
		RenderHelper.renderArcRectangle(tessellator, w, w, w, w, w, w);
		if (isGate(te.getWorldObj(), te.xCoord - 1, te.yCoord, te.zCoord))
			RenderHelper.renderArcRectangle(tessellator, w + d, 0, w, w, w, w);
		if (isGate(te.getWorldObj(), te.xCoord + 1, te.yCoord, te.zCoord))
			RenderHelper.renderArcRectangle(tessellator, 0, w + d, w, w, w, w);
		// if (isGate(te.getWorldObj(), te.xCoord, te.yCoord - 1, te.zCoord))
		RenderHelper.renderArcRectangle(tessellator, w, w, w + d, 0, w, w);
		// if (isGate(te.getWorldObj(), te.xCoord, te.yCoord + 1, te.zCoord))
		RenderHelper.renderArcRectangle(tessellator, w, w, 0, w + d, w, w);
		if (isGate(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord - 1))
			RenderHelper.renderArcRectangle(tessellator, w, w, w, w, w + d, 0);
		if (isGate(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord + 1))
			RenderHelper.renderArcRectangle(tessellator, w, w, w, w, 0, w + d);
	}

	private void renderWithOutTile(Tessellator tessellator) {
		double w = 0.3;
		double d = 2 * (0.5 - w);
		RenderHelper.renderArcRectangle(tessellator, w, w, w, w, w, w);
		RenderHelper.renderArcRectangle(tessellator, w + d, 0, w, w, w, w);
		RenderHelper.renderArcRectangle(tessellator, 0, w + d, w, w, w, w);
		RenderHelper.renderArcRectangle(tessellator, w, w, w + d, 0, w, w);
		RenderHelper.renderArcRectangle(tessellator, w, w, 0, w + d, w, w);
		RenderHelper.renderArcRectangle(tessellator, w, w, w, w, w + d, 0);
		RenderHelper.renderArcRectangle(tessellator, w, w, w, w, 0, w + d);
	}

	private ResourceLocation getTextureFromByte(String s) {
		if (s.equals("tile.tfcs:tfcs:gate.copper"))
			return ReferenceResource.COPPER_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.bronze"))
			return ReferenceResource.BRONZE_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.blackBronze"))
			return ReferenceResource.BLACK_BRONZE_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.bismuthBronze"))
			return ReferenceResource.BISMUTH_BRONZE_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.iron"))
			return ReferenceResource.IRON_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.steel"))
			return ReferenceResource.STEEL_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.blackSteel"))
			return ReferenceResource.BLACK_STEEL_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.redSteel"))
			return ReferenceResource.RED_STEEL_COMPONENET_TEXTURE;
		if (s.equals("tile.tfcs:tfcs:gate.blueSteel"))
			return ReferenceResource.BLUE_STEEL_COMPONENET_TEXTURE;
		return ReferenceResource.WOODEN_COMPONENET_TEXTURE;
	}

	public String getUnlocalizedName(String s) {
		return String.format("tile.%s%s", Reference.ModID.toLowerCase() + ":", getUnwrappedUnlocalizedName(s));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	private boolean isGate(World world, int x, int y, int z) {
		TileEntity te = (TileEntity) world.getTileEntity(x, y, z);
		return te != null && te instanceof TileEntityGate;
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
		Minecraft.getMinecraft().renderEngine.bindTexture(getTextureFromByte(getUnlocalizedName(item.getUnlocalizedName())));
		renderWithOutTile(tessellator);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
