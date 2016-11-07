package tfcs.items;

import java.util.List;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.Items.Tools.ItemTerraTool;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import tfcs.reference.Reference;
import tfcs.util.ISharpenable;
import tfcs.util.NBTHelper;
import tfcs.util.SharpnessHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemTFCSTool extends ItemTool implements ISize, ICausesDamage, ISharpenable {

	float damage;

	public ItemTFCSTool(Item.ToolMaterial toolMaterial, String name, float damage) {
		super(damage, toolMaterial, Sets.newHashSet(new Block[] { Blocks.air }));
		this.setUnlocalizedName(name + "." + toolMaterial.name());
		this.setMaxStackSize(1);
		this.setCreativeTab(TFCTabs.TFC_TOOLS);
		this.damage = damage;
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.ModID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Reference.ModID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int meta, boolean bool) {
		super.onUpdate(itemstack, world, entity, meta, bool);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 7000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		player.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
		return itemstack;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {

	}

	public double getWeaponDamage(ItemStack itemstack) {
		return Math.floor(damage + (damage * AnvilManager.getDamageBuff(itemstack)));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Multimap getAttributeModifiers(ItemStack itemstack) {
		if (damage > 1) {
			Multimap multimap = HashMultimap.create();
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", getWeaponDamage(itemstack), 0));
			return multimap;
		} else {
			return super.getAttributeModifiers(itemstack);
		}
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
		arraylist.add("+"+NBTHelper.getInt(is, "SHARPNESS")+" Bonus Attack Damage");
	}
	
	@Override
	public EnumDamageType getDamageType() {
		return EnumDamageType.PIERCING;
	}

	@Override
	public EnumSize getSize(ItemStack is) {
		return EnumSize.LARGE;
	}

	@Override
	public EnumWeight getWeight(ItemStack is) {
		return EnumWeight.HEAVY;
	}

	@Override
	public EnumItemReach getReach(ItemStack is) {
		return EnumItemReach.FAR;
	}

	@Override
	public boolean canStack() {
		return false;
	}
	
	public void damageItem(ItemStack itemstack,EntityPlayer player) {
		itemstack.damageItem(1, player);
		SharpnessHelper.dull(itemstack, 1);
	}

}
