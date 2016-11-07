package tfcs.gears.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfcs.core.TFCSItems;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.reference.ReferenceResource;

public class BismuthBronzeCannonBarrelCompenent extends CannonBarrelComponent {
	public BismuthBronzeCannonBarrelCompenent() {
		setMaxGunPowder(12);
	}
	@Override
	public ItemStack getDropItem() {
		return new ItemStack(TFCSItems.bismuthBronzeCannonBarrel);
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ReferenceResource.BISMUTH_BRONZE_COMPONENET_TEXTURE;
	}
	
	@Override
	public String getName() {
		return super.getName()+".bismuthBronze";
	}
}
