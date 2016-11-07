

package tfcs.items.tool;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Blocks.Terrain.BlockStone;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.google.common.collect.ImmutableSet;

import tfcs.items.ItemTFCSTool;
import tfcs.reference.ReferenceName;
import tfcs.util.NBTHelper;
import tfcs.util.SharpnessHelper;

public class ItemTemperedPickaxe extends ItemTFCSTool{

	public ItemTemperedPickaxe(ToolMaterial toolMaterial, float damage) {
		super(toolMaterial, ReferenceName.ITEM_TEMPERED_PICKAXE_NAME, damage);
		this.setCreativeTab(TFCTabs.TFC_TOOLS);
	}
	
	
	
	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta) {
		if (ForgeHooks.isToolEffective(new ItemStack(Items.diamond_pickaxe), block, meta)){
		return efficiencyOnProperMaterial + (NBTHelper.getInt(itemstack, "SHARPNESS")/5);
		}
		return super.getDigSpeed(itemstack, block, meta);
	}
	
	@Override
	public EnumDamageType getDamageType() {
		return EnumDamageType.PIERCING;
	}
	@Override
	public boolean canHarvestBlock(Block block, ItemStack itemstack) {
		return true;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
		SharpnessHelper.dull(itemstack, 1);
		if(block instanceof BlockOre ||block instanceof BlockStone)
			block.dropBlockAsItem(world, x, y, z, block.getDamageValue(world, x, y, z), 1);
		return super.onBlockDestroyed(itemstack, world, block, x, y, z, entity);
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe");
	}
	
}
