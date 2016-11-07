package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.reference.ReferenceResource;

public class CopperCannonBarrelCompenent extends CannonBarrelComponent {
	public CopperCannonBarrelCompenent() {
		setMaxGunPowder(10);
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.copperCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.COPPER_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return super.getName()+".copper";
	}
	
}
