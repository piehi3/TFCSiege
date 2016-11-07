package tfcs.gears.tool;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.gears.ToolComponent;
import tfcs.reference.ReferenceName;
import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;

public class BenSmells extends ToolComponent {
	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		RenderHelper.renderPolygon(tileentity, tessellator, rotation, 128, 5, 1, false, 0, 0, 0, 0);
	}
	@Override
	public void onActivated(TileEntityGearFrame tileentity, EntityPlayer player) {
		if(player.getCurrentEquippedItem()!=null)
			if(player.getCurrentEquippedItem().getItem().getUnlocalizedName().equals(Blocks.dirt.getUnlocalizedName()))
				player.dropItem(Items.diamond, 1);
	}
	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.COPPER_COMPONENET_TEXTURE;
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BEN_SMELLS_NAME;
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.benSmells,1);
	}
}
