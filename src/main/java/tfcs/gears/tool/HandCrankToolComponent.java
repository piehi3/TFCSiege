package tfcs.gears.tool;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.RotationPacket;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;

public class HandCrankToolComponent extends ToolComponent {

	@Override
	public void onActivated(TileEntityGearFrame tileentity, EntityPlayer player) {
		int b = player.isSneaking() ? -1 : 1;
		if (!player.capabilities.isCreativeMode)
			tileentity.addRotation(new RotationPacket(2 * b, 30));
		else
			tileentity.addRotation(new RotationPacket(2 * b, 1000000000));
	}

	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		GL11.glTranslated(0.5, 0.5, 0.5);
		GL11.glRotated(rotation, 1, 0, 0);
		GL11.glTranslated(-0.5, -0.5, -0.5);
		RenderHelper.renderArcRectangle(tessellator, 0.4, 0.4, 0.4, 0.4, 0.4, -0.2);
		RenderHelper.renderArcRectangle(tessellator, 0.4, 0.1, 0.45, 0.45, 1.1, -0.19);
	}

	@Override
	public String getName() {
		return ReferenceName.ITEM_HAND_CRANK_NAME;
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.WOODEN_COMPONENET_TEXTURE;
	}

	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.handCrank, 1, 0);
	}

}
