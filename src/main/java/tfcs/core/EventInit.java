package tfcs.core;

import com.bioxx.tfc.api.Armor;

import tfcs.items.tool.ItemShield;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;

public class EventInit {
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new ItemShield(ToolMaterial.WOOD,Armor.leather));
	}
}
