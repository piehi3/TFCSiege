package tfcs.util;

import java.util.Random;

import com.bioxx.tfc.api.TFCItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import tfcs.core.TFCSItems;
import tfcs.core.TradeRegistry;
import tfcs.core.TradeValue;
import tfcs.reference.ReferenceName;

public class Trade {

	TradeValue[] trades;

	public Trade(String type, Random rand) {
		trades = new TradeValue[] { null, null, null, null, null, null, null, null };
		getRandomTrade(type, rand);
	}

	public void getRandomTrade(String type, Random rand) {
		TradeValue[] trades2 = TradeRegistry.instance.getTradeArrayType(type);
		for (int i = 0; i < trades.length; i++)
			trades[i] = trades2[rand.nextInt(trades2.length)];
	}

	public TradeValue[] getTrades() {
		return trades;
	}

	public void setTrade(TradeValue trade, int i) {
		this.trades[i] = trade;
	}

	public void readFromNBT(String id, NBTTagCompound tag) {
		NBTTagList nbttaglist;
		if (tag.hasKey(id, 9)) {
			nbttaglist = tag.getTagList(id, 10);

			for (int i = 0; i < trades.length; i++) {
				this.trades[i] = new TradeValue(tag.getString(id + "_TYPE_" + i), tag.getInteger(id + "_COST_" + i), ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i)));
			}
		}
	}

	public void writeToNBT(String id, NBTTagCompound tag) {
		NBTTagList nbttaglist = new NBTTagList();
		NBTTagCompound nbttagcompound;

		for (int i = 0; i < this.trades.length; ++i) {
			nbttagcompound = new NBTTagCompound();

			this.trades[i].getItemstack().writeToNBT(nbttagcompound);
			tag.setInteger(id + "_COST_" + i, this.trades[i].getCost());
			tag.setString(id + "_TYPE_" + i, this.trades[i].getType());

			nbttaglist.appendTag(nbttagcompound);
		}

		tag.setTag(id, nbttaglist);
	}

	public static void registerTrades() {
		TradeRegistry.instance.registerTrade(ReferenceName.TRADE_MILITARY_NAME, new ItemStack(Items.gunpowder));
		TradeRegistry.instance.registerTrade(ReferenceName.TRADE_MILITARY_NAME, new ItemStack(TFCSItems.cannonBall));
		TradeRegistry.instance.registerTrade(ReferenceName.TRADE_MILITARY_NAME, new ItemStack(TFCSItems.bullet));
		TradeRegistry.instance.registerTrade(ReferenceName.TRADE_MILITARY_NAME, new ItemStack(TFCSItems.steelTemperedBroadsword));
		TradeRegistry.instance.registerTrade(ReferenceName.TRADE_MILITARY_NAME, new ItemStack(TFCSItems.copperCannonBarrel));
		TradeRegistry.instance.registerTrade(ReferenceName.TRADE_MILITARY_NAME, new ItemStack(TFCItems.powder,1,2));
	}

}
