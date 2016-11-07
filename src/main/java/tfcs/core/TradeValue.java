package tfcs.core;

import net.minecraft.item.ItemStack;

public class TradeValue {

	private String type;
	private int cost;
	private ItemStack itemstack;

	public TradeValue(String type, int cost, ItemStack itemstack) {
		this.type = type;
		this.cost = cost;
		this.itemstack = itemstack;
	}

	public String getType() {
		return type;
	}
	
	public int getCost() {
		return cost;
	}
	
	public ItemStack getItemstack() {
		return itemstack;
	}
	
}
