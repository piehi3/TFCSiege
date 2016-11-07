package tfcs.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.bioxx.tfc.Items.ItemCoal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.Reference;
import tfcs.reference.ReferenceName;

public class ItemFuel extends ItemCoal {
	public ItemFuel() {
		this.setUnlocalizedName(ReferenceName.ITEM_FUEL_NAME+"."+"coke");
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setMaxStackSize(32);
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

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return itemIcon;
	}
	
	@Override
	public IIcon getIconFromDamage(int i) {
		return itemIcon;
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
}
