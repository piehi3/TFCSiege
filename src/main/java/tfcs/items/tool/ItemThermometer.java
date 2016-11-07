package tfcs.items.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.items.ItemTFCS;
import tfcs.reference.ReferenceName;
import tfcs.util.IHeated;

public class ItemThermometer extends ItemTFCS {
	public ItemThermometer() {
		this.setUnlocalizedName(ReferenceName.ITEM_THERMOMETER_NAME);
		this.setMaxDamage(100);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_GEARS);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.getTileEntity(x, y, z) instanceof IHeated)
			player.addChatComponentMessage(new ChatComponentText(((IHeated) world.getTileEntity(x, y, z)).getHeat()-273 + "C"));
		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

}
