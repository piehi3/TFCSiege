package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.reference.ReferenceResource;

public class IronCannonBarrelCompenent extends CannonBarrelComponent {
	public IronCannonBarrelCompenent() {
		setMaxGunPowder(14);
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.ironCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.IRON_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return super.getName()+".iron";
	}
}
