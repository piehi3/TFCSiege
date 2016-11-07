package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.reference.ReferenceResource;

public class RedSteelCannonBarrelCompenent extends CannonBarrelComponent {
	public RedSteelCannonBarrelCompenent() {
		setMaxGunPowder(20);
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.redSteelCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.RED_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return super.getName()+".redSteel";
	}
}
