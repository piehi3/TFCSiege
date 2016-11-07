package tfcs.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tfcs.util.IndustrialFuel;

public class IndustrialFuelManager {

	public static IndustrialFuelManager instance = new IndustrialFuelManager();

	private Map<String, IndustrialFuel> hash;

	public IndustrialFuelManager() {
		hash = new HashMap<String, IndustrialFuel>();
	}

	public boolean addFuelToRegistry(Item item, int meta, float burnTime, float burnTemperature) {
		ItemStack itemstack = new ItemStack(item, 1, meta);
		return addIndustrialFuelToRegistry(item.getUnlocalizedName(itemstack), new IndustrialFuel(burnTime, burnTemperature));
	}

	public boolean addIndustrialFuelToRegistry(String name, IndustrialFuel fuel) {
		if (hash.containsKey(name))
			return false;
		hash.put(name, fuel);
		return true;
	}

	public IndustrialFuel getFrameComponentFromItemStack(String name) {
		if (hash.containsKey(name))
			return hash.get(name);
		else
			return null;
	}

	public IndustrialFuel getFrameComponentFromItem(Item item, int meta) {
		ItemStack itemstack = new ItemStack(item, 1, meta);
		if (hash.containsKey(item.getUnlocalizedName(itemstack)))
			return hash.get(item.getUnlocalizedName(itemstack));
		else
			return null;
	}
	
	public boolean hasItemKey(Item item,int meta) {
		ItemStack itemstack = new ItemStack(item, 1, meta);
		return hash.containsKey(item.getUnlocalizedName(itemstack));
	}
}
