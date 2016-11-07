package tfcs.items;

import com.bioxx.tfc.Items.Pottery.ItemPotteryBase;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tfcs.core.TFCSBlocks;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.reference.Reference;
import tfcs.reference.ReferenceName;
import tfcs.util.ItemStackHelper;

public class ItemMudBrick extends ItemPotteryBase {
	IIcon[] icon;

	public ItemMudBrick() {
		super();
		this.setUnlocalizedName(ReferenceName.ITEM_MUD_BRICK);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_CHEMISTRY);
		this.setHasSubtypes(true);
		this.setWeight(EnumWeight.LIGHT);
		this.setSize(EnumSize.TINY);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icon = new IIcon[] { iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".wet"),
				iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + ".dry") };
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
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.ModID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		String s = itemStack.getItemDamage() == 0 ? ".wet" : ".dry";
		return String.format("item.%s%s", Reference.ModID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + s);
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (entityplayer.isSneaking())
			return super.onItemUse(itemstack, entityplayer, world, x, y, z, side, hitX, hitY, hitZ);
		if (itemstack.getItemDamage() == 0 && world.isAirBlock(x, y + 1, z)) {
			world.setBlock(x, y + 1, z, TFCSBlocks.dryingMudBrick);
			ItemStackHelper.decreaseItemStackFromPlayer(itemstack, entityplayer);
		}
		return true;
	}

}
