package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.reference.ReferenceResource;

public class BlackSteelCannonBarrelCompenent extends CannonBarrelComponent {
	public BlackSteelCannonBarrelCompenent() {
		setMaxGunPowder(18);
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.blackSteelCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BLACK_STEEL_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return super.getName()+".blackSteel";
	}
}
