package tfcs.items.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tfcs.core.TFCSItems;
import tfcs.items.ItemProjectile;
import tfcs.reference.ReferenceName;
import tfcs.util.NBTHelper;

public class ItemCrossbow extends ItemLauncher{

	float bowPower = 20;
	
	public ItemCrossbow(ToolMaterial material, float bowPower) {
		super(material);
		this.setUnlocalizedName(ReferenceName.ITEM_CROSSBOW_NAME + "." + toolMaterial.name());
		this.setStats(1000, false, 0);
		this.bowPower*=bowPower;
	}
	
	@Override
	public ItemProjectile[] getAmmo() {
		return new ItemProjectile[]{(ItemProjectile) TFCSItems.copperBolt,(ItemProjectile) TFCSItems.bronzeBolt,(ItemProjectile) TFCSItems.blackBronzeBolt,(ItemProjectile) TFCSItems.bismuthBronzeBolt,(ItemProjectile) TFCSItems.wroughtIronBolt};
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if(NBTHelper.getBoolean(itemstack, "IS_LOADED")){
			launchEntity(itemstack, player, world, player.posX, player.posY, player.posZ);
			resetNBT(itemstack);
		}
		return super.onItemRightClick(itemstack, world, player);
	}
	
	@Override
	protected void launchEntity(ItemStack itemstack, EntityPlayer player, World world, double x, double y, double z) {
		NBTHelper.setInt(itemstack, "POST_LOADED", (int)bowPower);
		super.launchEntity(itemstack, player, world, x, y, z);
	}
	
}
