package tfcs.core;

import tfcs.util.Trade;
import net.minecraft.item.ItemStack;

public class TradeRegistry {

	public static TradeRegistry instance = new TradeRegistry();

	private TradeValue[] trades;

	public TradeRegistry() {
		trades = new TradeValue[]{};
	}

	public boolean registerTrade(String type, ItemStack itemstack) {
		return addToArray(new TradeValue(type, ValueRegistry.instance.getValueFromItem(itemstack.getItem(), itemstack.getItemDamage()), itemstack));
	}

	private boolean addToArray(TradeValue tradeValue) {
		TradeValue[] trades2 = new TradeValue[trades.length + 1];
		trades2[0] = tradeValue;
		for (int i = 0; i < trades.length; i++)
			trades2[i + 1] = trades[i];
		trades = trades2;
		return true;
	}

	public TradeValue[] getTradeArrayType(String type) {
		int c = 0;
		for (int i = 0; i < trades.length; i++)
			if(trades[i].getType().equals(type))
				c++;
		TradeValue[] trades2 = new TradeValue[c];
		for (int i = 0; i < trades.length; i++)
			if(trades[i].getType().equals(type)){
				c--;
				trades2[c] = trades[i];
			}
		return trades2;
	}
	
	public TradeValue getTradeFromPosition(int i) {
		if (i < trades.length)
			return trades[i];
		else
			return null;
	}

}
