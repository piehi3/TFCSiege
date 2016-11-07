package tfcs.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.entity.EntityWarHorse;
import tfcs.reference.ReferenceName;

public class ItemWarSaddle extends ItemTFCS {

	public ItemWarSaddle() {
		super();
		this.setUnlocalizedName(ReferenceName.ITEM_WAR_SADDLE_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		NBTTagCompound tag = new NBTTagCompound();
		entity.writeToNBT(tag);
		EntityWarHorse horse = new EntityWarHorse(entity.worldObj);
		horse.readFromNBT(tag);
		horse.setHorseSaddled(true);
		entity.setDead();
		if (!entity.worldObj.isRemote)
		entity.worldObj.spawnEntityInWorld(horse);
		return super.onLeftClickEntity(stack, player, entity);
	}
	
}
