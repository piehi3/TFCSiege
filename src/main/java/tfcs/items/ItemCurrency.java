package tfcs.items;

import java.util.List;

import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.Reference;
import tfcs.reference.ReferenceName;

import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemCurrency extends ItemTFCS implements ISize {
	IIcon[] icon;

	public ItemCurrency() {
		super();
		this.setUnlocalizedName(ReferenceName.ITEM_COIN_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setHasSubtypes(true);
		this.setMaxStackSize(16);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		icon = new IIcon[] { iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".copper"),
				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".brass"),
				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".nickel"),
				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".stirlingSilver"),
				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".silver"),
				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".roseGold"),
				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".gold"),

				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".platinum") };

	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return icon[stack.getItemDamage()];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
		return super.getIconFromDamageForRenderPass(meta, pass);
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		return icon[damage];
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List list) {
		for (int i = 0; i < icon.length; i++)
			list.add(new ItemStack(this, 1, i));
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		String s;
		switch (itemstack.getItemDamage()) {
		case 0:
			s = ".copper";
			break;
		case 1:
			s = ".brass";
			break;
		case 3:
			s = ".stirlingSilver";
			break;
		case 4:
			s = ".silver";
			break;
		case 5:
			s = ".roseGold";
			break;
		case 2:
			s = ".nickel";
			break;
		case 6:
			s = ".gold";
			break;
		case 7:
			s = ".platinum";
			break;
		default:
			s = ".copper";
			break;
		}
		return super.getUnlocalizedName(itemstack) + s;
	}

	@Override
	public EnumSize getSize(ItemStack is) {
		return EnumSize.TINY;
	}

	@Override
	public EnumWeight getWeight(ItemStack is) {
		return EnumWeight.LIGHT;
	}

	@Override
	public EnumItemReach getReach(ItemStack is) {
		return EnumItemReach.SHORT;
	}

	@Override
	public boolean canStack() {
		return true;
	}
}