package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.reference.ReferenceResource;

public class BlueSteelCannonBarrelCompenent extends CannonBarrelComponent {
	public BlueSteelCannonBarrelCompenent() {
		setMaxGunPowder(20);
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blueSteelCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLUE_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return super.getName()+".blueSteel";
	}
}
