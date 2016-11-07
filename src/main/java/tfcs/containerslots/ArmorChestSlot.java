package tfcs.containerslots;

import com.bioxx.tfc.Items.ItemTFCArmor;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ArmorChestSlot extends Slot {

	public ArmorChestSlot(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() != null && itemstack.getItem() instanceof ItemTFCArmor && ((ItemTFCArmor)itemstack.getItem()).getBodyPart() == 2;
	}

}
