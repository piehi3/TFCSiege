package tfcs.items.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tfcs.core.TFCSItems;
import tfcs.items.ItemProjectile;
import tfcs.reference.ReferenceName;

public class ItemMusket extends ItemCrossbow {

	public ItemMusket(ToolMaterial material, float bowPower) {
		super(material, bowPower);
		this.setUnlocalizedName(ReferenceName.ITEM_MUSKET_NAME + "." + toolMaterial.name());
		this.setPlaySound("random.explode");
		this.setStats(5000, false, 0);
	}

	@Override
	public ItemProjectile[] getAmmo() {
		return new ItemProjectile[]{(ItemProjectile) TFCSItems.bullet};
	}
	
	@Override
	protected void launchEntity(ItemStack itemstack, EntityPlayer player, World world, double x, double y, double z) {
		if(player.capabilities.isCreativeMode||player.inventory.consumeInventoryItem(Items.gunpowder))
			super.launchEntity(itemstack, player, world, x, y, z);
	}
	
}
