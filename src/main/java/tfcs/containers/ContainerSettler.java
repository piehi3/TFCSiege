package tfcs.containers;

import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Items.ItemTFCArmor;
import com.bioxx.tfc.Items.Tools.ItemHammer;
import com.bioxx.tfc.api.TFCItems;

import tfcs.containerslots.ArmorBootsSlot;
import tfcs.containerslots.ArmorChestSlot;
import tfcs.containerslots.ArmorHeadSlot;
import tfcs.containerslots.ArmorLeggingsSlot;
import tfcs.containerslots.InventorySlot;
import tfcs.containerslots.ShieldSlot;
import tfcs.containerslots.WeaponSlot;
import tfcs.entity.EntitySettler;
import tfcs.items.tool.ItemShield;
import tfcs.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSettler extends Container {

	private EntitySettler es;
	private EntityPlayer player;

	public ContainerSettler(EntityPlayer player, EntitySettler es) {
		super();
		this.es = es;
		this.player = player;

		// inventory
		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 3; k++)
				addSlotToContainer(new InventorySlot(es, 0 + (i * 3) + k, 116 + (i * 18), 7 + (k * 18)));
		// Equipment
		addSlotToContainer(new WeaponSlot(es, 9, 30, 7));

		addSlotToContainer(new ArmorHeadSlot(es, 13, 8, 7));
		addSlotToContainer(new ArmorChestSlot(es, 12, 8, 25));
		addSlotToContainer(new ArmorLeggingsSlot(es, 11, 8, 43));
		addSlotToContainer(new ArmorBootsSlot(es, 10, 8, 61));

		addSlotToContainer(new ShieldSlot(es, 14, 30, 25));

		PlayerInventory.buildInventoryLayout(this, player.inventory, 8, 84, false, true);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {

		return true;
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		ItemStack origStack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		Slot[] slotinput = { (Slot) inventorySlots.get(1), (Slot) inventorySlots.get(2), (Slot) inventorySlots.get(3), (Slot) inventorySlots.get(5) };

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			origStack = slotStack.copy();

			if (i < 15) {
				if (!this.mergeItemStack(slotStack, 7, inventorySlots.size(), true))
					return null;
			} else if (slotStack.getItem() instanceof ItemTFCArmor && ((ItemTFCArmor) slotStack.getItem()).getBodyPart() == 3) {
				if (((Slot) this.inventorySlots.get(10)).getHasStack())
					return null;
				ItemStack stack = slotStack.copy();
				stack.stackSize = 1;
				((Slot) this.inventorySlots.get(10)).putStack(stack);
				slotStack.stackSize--;
			} else if (slotStack.getItem() instanceof ItemTFCArmor && ((ItemTFCArmor) slotStack.getItem()).getBodyPart() == 2) {
				if (((Slot) this.inventorySlots.get(11)).getHasStack())
					return null;
				ItemStack stack = slotStack.copy();
				stack.stackSize = 1;
				((Slot) this.inventorySlots.get(11)).putStack(stack);
				slotStack.stackSize--;
			} else if (slotStack.getItem() instanceof ItemTFCArmor && ((ItemTFCArmor) slotStack.getItem()).getBodyPart() == 1) {
				if (((Slot) this.inventorySlots.get(12)).getHasStack())
					return null;
				ItemStack stack = slotStack.copy();
				stack.stackSize = 1;
				((Slot) this.inventorySlots.get(12)).putStack(stack);
				slotStack.stackSize--;
			} else if (slotStack.getItem() instanceof ItemTFCArmor && ((ItemTFCArmor) slotStack.getItem()).getBodyPart() == 0) {
				if (((Slot) this.inventorySlots.get(13)).getHasStack())
					return null;
				ItemStack stack = slotStack.copy();
				stack.stackSize = 1;
				((Slot) this.inventorySlots.get(13)).putStack(stack);
				slotStack.stackSize--;
			} else if (slotStack.getItem() instanceof ItemShield) {
				if (((Slot) this.inventorySlots.get(14)).getHasStack())
					return null;
				ItemStack stack = slotStack.copy();
				stack.stackSize = 1;
				((Slot) this.inventorySlots.get(14)).putStack(stack);
				slotStack.stackSize--;
			} else if (ItemStackHelper.isWeaponOrTool(slotStack)) {
				if (((Slot) this.inventorySlots.get(9)).getHasStack())
					return null;
				ItemStack stack = slotStack.copy();
				stack.stackSize = 1;
				((Slot) this.inventorySlots.get(9)).putStack(stack);
				slotStack.stackSize--;
			}

			if (slotStack.stackSize <= 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();

			if (slotStack.stackSize == origStack.stackSize)
				return null;

			slot.onPickupFromSlot(player, slotStack);
		}

		return origStack;
	}

}
