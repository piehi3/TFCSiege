package tfcs.items.tool;

import java.util.List;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.ISize;

import tfcs.items.ItemTFCSTool;
import tfcs.reference.ReferenceName;
import tfcs.util.CalculationHelper;
import tfcs.util.ISharpenable;
import tfcs.util.NBTHelper;
import tfcs.util.SharpnessHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemTemperedBroadsword extends ItemTFCSTool implements ISize, ICausesDamage, ISharpenable {

	int chargeTime = 3;
	int slashDistance = 2;
	int slashRange = 45;

	public ItemTemperedBroadsword(ToolMaterial toolMaterial, float damage) {
		super(toolMaterial, ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME, damage);
		this.setCreativeTab(TFCTabs.TFC_WEAPONS);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int count) {
		if (!world.isRemote && count >= chargeTime) {
			double c = count / 100 > 1.5 ? 1.5 : count / 100;
			EntityLivingBase[] entity = getSlashableEntity(player, world);
			if (entity != null)
				for (int i = 0; i < entity.length; i++)
					if (entity[i] != null)
						entity[i].attackEntityFrom(DamageSource.causePlayerDamage(player), (float) (getWeaponDamage(itemstack) * c));
		}
		damageItem(itemstack, player);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity) {
		SharpnessHelper.dull(itemstack, 1);
		return super.onLeftClickEntity(itemstack, player, entity);
	}
	
	@SuppressWarnings("rawtypes")
	private EntityLivingBase[] getSlashableEntity(EntityPlayer player, World world) {
		EntityLivingBase[] entity = null;
		List list = world.getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(slashDistance, 1, slashDistance));
		if (list != null)
			for (int i = 0; i < list.size(); i++)
				if (!list.get(i).equals(player) && isValidEntityLivingBase((EntityLivingBase) list.get(i), player))
					entity = CalculationHelper.addEntityToEntityArray(entity, (EntityLivingBase) list.get(i));
		return entity;
	}

	private boolean isValidEntityLivingBase(EntityLivingBase entity, EntityPlayer player) {
		double h = CalculationHelper.pythagoreanTheorem2D(entity.posX - player.posX, entity.posZ - player.posZ);
		double o = entity.posX - player.posX;
		double c = CalculationHelper.arcsin(o / h) + 180;
		double p = player.rotationYaw;
		if (p < 0)
			p += 360;
		double r1 = p + slashRange;
		double r2 = p - slashRange;
		return r1 >= c && r2 <= c;
	}

	@Override
	public double getWeaponDamage(ItemStack itemstack) {
		return super.getWeaponDamage(itemstack)+NBTHelper.getInt(itemstack, "SHARPNESS");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public EnumDamageType getDamageType() {
		return EnumDamageType.SLASHING;
	}

	@Override
	public EnumItemReach getReach(ItemStack is) {
		return EnumItemReach.MEDIUM;
	}
	
}
