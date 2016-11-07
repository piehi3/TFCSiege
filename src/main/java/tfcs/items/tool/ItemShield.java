package tfcs.items.tool;

import java.util.List;
import java.util.Random;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.Items.Tools.ItemTerraTool;
import com.bioxx.tfc.api.Armor;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.IInnateArmor;
import com.bioxx.tfc.api.Interfaces.ISize;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import tfcs.items.ItemTFCSTool;
import tfcs.reference.ReferenceName;
import tfcs.util.CalculationHelper;

public class ItemShield extends ItemTFCSTool implements ISize, ICausesDamage,IInnateArmor {
	Armor armor;

	public ItemShield(ToolMaterial toolMaterial, Armor armor) {
		super(toolMaterial, ReferenceName.ITEM_SHIELD_NAME, 1);
		this.setCreativeTab(TFCTabs.TFC_WEAPONS);
		this.armor = armor;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
		ItemTerra.addSizeInformation(is, arraylist);
		ItemTerra.addHeatInformation(is, arraylist);

		if (is.getItem() instanceof ICausesDamage)
			arraylist.add(EnumChatFormatting.AQUA + TFC_Core.translate(((ICausesDamage) this).getDamageType().toString()));
		ItemTerraTool.addSmithingBonusInformation(is, arraylist);
		addExtraInformation(is, player, arraylist);
	}

	public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
	}

	@SubscribeEvent
	public void entityAttacked(LivingAttackEvent event) {
		if (event.entityLiving instanceof EntityPlayer && !event.source.equals(DamageSource.inFire) && !event.source.equals(DamageSource.drown) && !event.source.equals(DamageSource.fall)
				&& !event.source.equals(DamageSource.fallingBlock) && !event.source.equals(DamageSource.inFire) && !event.source.equals(DamageSource.inWall) && !event.source.equals(DamageSource.lava)
				&& !event.source.equals(DamageSource.outOfWorld) && !event.source.equals(DamageSource.starve) && !event.source.equals(DamageSource.wither)) {
			Random rand = new Random();
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack[] inventory = player.inventory.mainInventory;
			ItemStack itemstack = null;
			for (int i = 0; i < 9; i++) {
				if (inventory[i] != null && inventory[i].getItem() instanceof ItemShield) {
					itemstack = inventory[i];
					break;
				}
			}
			if (itemstack != null) {
				float entityPoximityBuff = (float) getAngleDistanceMultiplyer(event.source.getSourceOfDamage(), player, curentlyEquipedItemEquals(player, itemstack) ? 1 : -1);
				int entityBlockingBuff = player.isBlocking() && curentlyEquipedItemEquals(player, itemstack) ? 1 : 2;
				float postBlockedDamage = (armor.getCrushingAR() * rand.nextFloat() * entityPoximityBuff) / (entityBlockingBuff * 10);
				subtractDamageFromEvent(event, postBlockedDamage);
				if (event.source.equals(DamageSource.onFire) && armor.equals(Armor.leather)) {
					player.attackEntityFrom(DamageSource.inFire, event.ammount + event.ammount);
				}
				itemstack.damageItem(1, player);
				event.setCanceled(true);
			}
		}
	}

	private boolean curentlyEquipedItemEquals(EntityPlayer player, ItemStack itemstack) {
		return player.getCurrentEquippedItem() != null ? player.getCurrentEquippedItem().equals(itemstack) ? true : false : false;
	}

	private double getAngleDistanceMultiplyer(Entity entity, EntityPlayer player, int i) {
		if (entity != null) {
			double h = CalculationHelper.pythagoreanTheorem2D(entity.posX - player.posX, entity.posZ - player.posZ);
			double o = entity.posX - player.posX;
			double c = i * CalculationHelper.arcsin(o / h) + 180;
			double p = player.rotationYaw;
			if (p < 0)
				p += 360;
			double f = c - p > 180 ? Math.abs(c - p - 360) : c - p;
			return 2 - (f / 180);
		} else {
			return 1;
		}
	}

	public void subtractDamageFromEvent(LivingAttackEvent event, float damage) {
		float d = (event.ammount - damage < 0 ? 1 : event.ammount - damage);
		event.entityLiving.attackEntityFrom(DamageSource.inFire, d);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.block;
	}

	@Override
	public EnumDamageType getDamageType() {
		return EnumDamageType.CRUSHING;
	}

	@Override
	public EnumSize getSize(ItemStack is) {
		return EnumSize.HUGE;
	}

	@Override
	public EnumWeight getWeight(ItemStack is) {
		return EnumWeight.HEAVY;
	}

	@Override
	public EnumItemReach getReach(ItemStack is) {
		return EnumItemReach.SHORT;
	}

	@Override
	public boolean canStack() {
		return false;
	}

	@Override
	public int getCrushArmor() {
		return armor.getCrushingAR();
	}

	@Override
	public int getSlashArmor() {
		return armor.getSlashingAR();
	}

	@Override
	public int getPierceArmor() {
		return armor.getPiercingAR();
	}

}
