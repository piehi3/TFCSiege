package tfcs.containerslots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class OutputSlot extends Slot {
	
	public OutputSlot(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return false;//itemstack.getItem() != null && itemstack.getItem() instanceof ItemTFCArmor && ((ItemTFCArmor)itemstack.getItem()).getBodyPart() == 2;
	}
}
