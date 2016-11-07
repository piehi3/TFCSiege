package tfcs.containers;

import com.bioxx.tfc.Core.Player.PlayerInventory;

import tfcs.containerslots.CoinSlot;
import tfcs.containerslots.OutputSlot;
import tfcs.entity.EntityTravelingSalesman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerTravelingSalesman extends Container {

	public ContainerTravelingSalesman(EntityPlayer player, EntityTravelingSalesman ts) {
		super();
		
		for (int i = 0; i < 8; i++)
			addSlotToContainer(new OutputSlot(ts, i, 28 + (i * 18), 43));
		
		addSlotToContainer(new CoinSlot(ts, 9, 8, 7));
		addSlotToContainer(new CoinSlot(ts, 10, 8, 25));
		addSlotToContainer(new CoinSlot(ts, 11, 8, 43));
		addSlotToContainer(new CoinSlot(ts, 12, 8, 61));
		
		PlayerInventory.buildInventoryLayout(this, player.inventory, 8, 84, false, true);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
		
	}
	
}
