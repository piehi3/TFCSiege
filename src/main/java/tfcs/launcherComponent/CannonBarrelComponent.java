package tfcs.launcherComponent;

import org.lwjgl.opengl.GL11;

import tfcs.core.TFCSItems;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CannonBarrelComponent extends LauncherComponent {

	private int maxGunPowder;
	
	public CannonBarrelComponent() {

	}

	@Override
	public String getName() {
		return ReferenceName.ITEM_CANNON_BARREL_NAME;
	}

	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.copperCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.COPPER_COMPONENET_TEXTURE;
	}

	public int getMaxGunPowder() {
		return maxGunPowder;
	}
	
	public void setMaxGunPowder(int maxGunPowder) {
		this.maxGunPowder = maxGunPowder;
	}
	
	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		GL11.glTranslated(0.5, 0.5, 0.5);
		GL11.glRotated(rotation, 1, 0, 0);
		GL11.glTranslated(-0.5, -0.5, -0.5);
		GL11.glRotatef(90, 0, 1, 0);
		RenderHelper.renderCircle(tessellator, 0, 8, 0.2, 0.1, 1, 0.4, -1.0, 0.2, 0);
		RenderHelper.renderCircle(tessellator, 0, 8, 0.1, 0.1, 1, 0.6, 0, 0.2, 0);
		RenderHelper.renderCircle(tessellator, 0, 8, 0.2, 0.05, 1, 0.05, 0.65, 0.2, 0);
		RenderHelper.renderFilledPolygon(tessellator, 0, 8, 0.2, 0.05, -1.45, 0.2, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		GL11.glTranslated(0.5, 0.5, 0.5);
		GL11.glRotated(rotation, 1, 0, 0);
		GL11.glTranslated(-0.5, -0.5, -0.5);
	}

}
