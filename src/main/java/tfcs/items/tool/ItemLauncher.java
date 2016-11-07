package tfcs.items.tool;

import java.util.List;

import com.bioxx.tfc.Core.TFCTabs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tfcs.core.TFCSItems;
import tfcs.entity.ProjectileTFCS;
import tfcs.items.ItemProjectile;
import tfcs.items.ItemTFCSTool;
import tfcs.reference.ReferenceName;
import tfcs.util.NBTHelper;
import tfcs.util.Vector;

public class ItemLauncher extends ItemTFCSTool {
	int preLoaded = 10;
	boolean isLoaded = false;
	int postLoaded = 100;
	String sound = "random.bow";

	public ItemLauncher(ToolMaterial material) {
		super(material, ReferenceName.ITEM_LONG_BOW_NAME, 1);
		this.setCreativeTab(TFCTabs.TFC_WEAPONS);
	}

	public ItemProjectile[] getAmmo() {
		return new ItemProjectile[] { (ItemProjectile) TFCSItems.longArrow, (ItemProjectile) TFCSItems.fireLongArrow };
	}

	public ItemProjectile isAmmoType(Item item) {
		if (item instanceof ItemProjectile)
			for (int i = 0; i < getAmmo().length; i++) {
				if (getAmmo()[i].equals(item))
					return getAmmo()[i];
			}
		return null;
	}

	public int getPostLoaded() {
		return postLoaded;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {
		if (NBTHelper.hasKey(itemstack, "PRE_LOADED"))
			list.add("Pre loaded " + NBTHelper.getInt(itemstack, "PRE_LOADED"));
		if (NBTHelper.hasKey(itemstack, "IS_LOADED"))
			list.add("Is loaded " + NBTHelper.getBoolean(itemstack, "IS_LOADED"));
		if (NBTHelper.hasKey(itemstack, "POST_LOADED"))
			list.add("Post loaded " + NBTHelper.getInt(itemstack, "POST_LOADED"));
		super.addInformation(itemstack, player, list, bool);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 76000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float posX, float posY, float posZ) {
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (getAmmoItem(player) != null) {
			initNBT(itemstack);
			NBTHelper.setInt(itemstack, "PRE_LOADED", NBTHelper.getInt(itemstack, "PRE_LOADED") - 1);
		}
		return itemstack;
	}

	private ItemProjectile getAmmoItem(EntityPlayer player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); i++)
			if (player.inventory.getStackInSlot(i) != null)
				if (isAmmoType(player.inventory.getStackInSlot(i).getItem()) != null)
					return (ItemProjectile) player.inventory.getStackInSlot(i).getItem();
		return null;
	}

	@Override
	public void onUsingTick(ItemStack itemstack, EntityPlayer player, int count) {
		if (NBTHelper.getInt(itemstack, "PRE_LOADED") <= 0 || NBTHelper.getBoolean(itemstack, "IS_LOADED")) {
			NBTHelper.setBoolean(itemstack, "IS_LOADED", true);
			if (NBTHelper.getInt(itemstack, "POST_LOADED") < postLoaded)
				NBTHelper.setInt(itemstack, "POST_LOADED", NBTHelper.getInt(itemstack, "POST_LOADED") + 1);
		} else {
			NBTHelper.setInt(itemstack, "PRE_LOADED", NBTHelper.getInt(itemstack, "PRE_LOADED") - 1);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int count) {
		if (NBTHelper.getInt(itemstack, "POST_LOADED") > 0 && postLoaded != 0) {
			launchEntity(itemstack, player, world, player.posX, player.posY, player.posZ);
			resetNBT(itemstack);
		} else {
			NBTHelper.setInt(itemstack, "POST_LOADED", 0);
			NBTHelper.setInt(itemstack, "PRE_LOADED", preLoaded);
		}
	}

	protected void launchEntity(ItemStack itemstack, EntityPlayer player, World world, double x, double y, double z) {
		ItemProjectile item = getAmmoItem(player);
		if (item != null) {
			ProjectileTFCS entity = item.getProjectile(world);
			Vector v = new Vector(player.rotationPitch, player.rotationYaw, NBTHelper.getInt(itemstack, "POST_LOADED") / 16);
			entity.shootingEntity = player;
			entity.setPosition(player.posX, player.posY + 1, player.posZ);
			entity.setVelocity(v.getMotionX(), -v.getMotionY(), -v.getMotionZ());
			entity.setPickupItem(getAmmoItem(player));
			if (!world.isRemote)
				world.spawnEntityInWorld(entity);
			world.playSoundAtEntity(player, sound, 1.0F, 1.5F + (1.0F / itemRand.nextFloat()));
			if (!player.capabilities.isCreativeMode)
				player.inventory.consumeInventoryItem(getAmmoItem(player));
			itemstack.damageItem(1, player);
		}
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int meta, boolean bool) {
		if (entity instanceof EntityPlayer) {
			if (((EntityPlayer) entity).getCurrentEquippedItem() != null) {
				if (!((EntityPlayer) entity).getCurrentEquippedItem().equals(itemstack)) {
					if (this.postLoaded != 0)
						resetNBT(itemstack);
				} else {
					if (NBTHelper.getInt(itemstack, "PRE_LOADED") < preLoaded) {
						((EntityPlayer) entity).setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
					}
				}
			} else {
				if (this.postLoaded != 0)
					resetNBT(itemstack);
			}
		}
	}

	protected void resetNBT(ItemStack itemstack) {
		NBTHelper.setBoolean(itemstack, "IS_LOADED", isLoaded);
		NBTHelper.setInt(itemstack, "POST_LOADED", 0);
		NBTHelper.setInt(itemstack, "PRE_LOADED", preLoaded);
	}

	private void initNBT(ItemStack itemstack) {
		if (!NBTHelper.hasKey(itemstack, "PRE_LOADED"))
			NBTHelper.setInt(itemstack, "PRE_LOADED", this.preLoaded);
		if (!NBTHelper.hasKey(itemstack, "IS_LOADED"))
			NBTHelper.setBoolean(itemstack, "IS_LOADED", this.isLoaded);
		if (!NBTHelper.hasKey(itemstack, "POST_LOADED"))
			NBTHelper.setInt(itemstack, "POST_LOADED", 0);
	}

	public void setStats(int preLoaded, boolean isLoaded, int postLoaded) {
		this.postLoaded = preLoaded;
		this.isLoaded = isLoaded;
		this.postLoaded = postLoaded;
	}

	public void setPlaySound(String sound) {
		this.sound = sound;
	}

}
