package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.reference.ReferenceResource;

public class SteelCannonBarrelCompenent extends CannonBarrelComponent {
	public SteelCannonBarrelCompenent() {
		setMaxGunPowder(16);
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.steelCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return super.getName()+".steel";
	}
}
