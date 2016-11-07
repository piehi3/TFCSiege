package tfcs.items;

import com.bioxx.tfc.Core.TFCTabs;

import tfcs.reference.Reference;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTFCS extends Item{
	public ItemTFCS() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(TFCTabs.TFC_MISC);
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.ModID.toLowerCase()
				+ ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Reference.ModID.toLowerCase()
				+ ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName()
				.substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world,Entity entity, int meta, boolean bool) {
		super.onUpdate(itemstack, world, entity, meta, bool);
	}
}
