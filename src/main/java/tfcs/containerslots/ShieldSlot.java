package tfcs.containerslots;

import tfcs.items.tool.ItemShield;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ShieldSlot extends Slot{

	public ShieldSlot(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() != null && itemstack.getItem() instanceof ItemShield;
	}
	
}
