package tfcs.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ValueRegistry {

	public static ValueRegistry instance = new ValueRegistry();

	private Map<String, String> hash;

	public ValueRegistry() {
		hash = new HashMap<String, String>();
	}

	public boolean registerItemValue(Item item, int meta, int value) {
		if (hash.containsKey(item.getUnlocalizedName() + "_" + meta))
			return false;

		hash.put(item.getUnlocalizedName() + "_" + meta, value + "");
		return true;
	}

	public int getValueFromItem(Item item, int meta) {
		if (hash.containsKey(item.getUnlocalizedName() + "_" + meta))
				return Integer.parseInt(hash.get(item.getUnlocalizedName() + "_" + meta));
		return 0;
	}

	public static int lookupItemStackValue(ItemStack is) {
		return ValueRegistry.instance.getValueFromItem(is.getItem(), is.getItemDamage());
	}
}
