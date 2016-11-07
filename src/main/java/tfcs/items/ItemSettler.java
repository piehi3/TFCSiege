package tfcs.items;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tfcs.entity.EntityRideableBear;
import tfcs.entity.EntitySettler;
import tfcs.entity.EntityTravelingSalesman;
import tfcs.entity.EntityWarHorse;
import tfcs.reference.ReferenceName;

public class ItemSettler extends ItemTFCS {
	int type;
	public ItemSettler(int type) {
		this.type = type;
		switch (type) {
		case 0:
			this.setUnlocalizedName(ReferenceName.ITEM_SETTLER_NAME);
			break;
		case 1:
			this.setUnlocalizedName(ReferenceName.ITEM_TRAVELING_SALESMAN_NAME);
			break;
		case 2:
			this.setUnlocalizedName(ReferenceName.ITEM_RIDE_BEAR_NAME);
			break;
		default:
			break;
		}
	}

	private EntityLiving getSettler(EntityPlayer player) {
		switch (type) {
		case 1:
			return new EntityTravelingSalesman(player);
		case 2:
			return new EntityRideableBear(player.worldObj);
		default:
			return new EntitySettler(player);
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int meta, float posX, float posY,float posZ) {
		EntityLiving es = getSettler(player);
		es.setPosition(x+0.5, y+1, z+0.5);
		if(!world.isRemote)
			world.spawnEntityInWorld(es);
		return true;
	}
}
