package tfcs.containerslots;

import tfcs.util.ItemStackHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class WeaponSlot extends Slot {

	public WeaponSlot(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() != null && ItemStackHelper.isWeaponOrTool(itemstack);
	}
	
}
