package tfcs.gears.tool;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.ISharpenable;
import tfcs.util.RenderHelper;
import tfcs.util.SharpnessHelper;

public class GrindstoneToolComponent extends ToolComponent {

	double processingTorque = 6;
	double processingSpeed = 1;
	
	public GrindstoneToolComponent() {
		this.setMaxRotation(2);
		this.setMinTorque(6);
	}
	
	@Override
	public void onActivated(TileEntityGearFrame tileentity, EntityPlayer player) {
		if (!tileentity.getWorldObj().isRemote) {
			if (Math.abs(tileentity.getRotationSpeed()) >= processingSpeed && tileentity.getTorque() >= processingTorque) {
				if (player.getCurrentEquippedItem() != null) {
					ItemStack itemstack = player.getCurrentEquippedItem();
					if (itemstack.getItem() instanceof ISharpenable) {
						SharpnessHelper.sharpen(itemstack, 1);
						itemstack.damageItem(1, player);
					}
				}
			}
		}
	}
	
	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		RenderHelper.renderPolygon(tileentity, tessellator, rotation, 16, 0.5, 0.2, false, 0, 0, 0, 0);
	}
	
	@Override
	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		return tileentity.getHasAxle();
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.ROCK_COMPONENET_TEXTURE;// Edit
	}

	@Override
	public String getName() {
		return ReferenceName.ITEM_GRINDSTONE_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.grindstone,1);
	}
	
}
