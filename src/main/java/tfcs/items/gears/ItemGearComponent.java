package tfcs.items.gears;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tfcs.gears.FrameComponent;
import tfcs.gears.gear.BismuthBronzeGearComponent;
import tfcs.gears.gear.BlackBronzeGearComponent;
import tfcs.gears.gear.BlackSteelGearComponent;
import tfcs.gears.gear.BlueSteelGearComponent;
import tfcs.gears.gear.BronzeGearComponent;
import tfcs.gears.gear.CopperGearComponent;
import tfcs.gears.gear.IronGearComponent;
import tfcs.gears.gear.RedSteelGearComponent;
import tfcs.gears.gear.SteelGearComponent;
import tfcs.gears.gear.StoneGearComponent;
import tfcs.gears.gear.WoodenGearComponent;
import tfcs.reference.ReferenceName;

public class ItemGearComponent extends ItemFrameComponent{
	int type = 0;
	public ItemGearComponent(int type) {
		this.type = type;
		switch (type) {
		case 0:
			this.setUnlocalizedName(ReferenceName.ITEM_WOODEN_GEAR_NAME);
			break;
		case 1:
			this.setUnlocalizedName(ReferenceName.ITEM_STONE_GEAR_NAME);
			break;
		case 2:
			this.setUnlocalizedName(ReferenceName.ITEM_COPPER_GEAR_NAME);
			break;
		case 3:
			this.setUnlocalizedName(ReferenceName.ITEM_BRONZE_GEAR_NAME);
			break;
		case 4:
			this.setUnlocalizedName(ReferenceName.ITEM_BLACK_BRONZE_GEAR_NAME);
			break;
		case 5:
			this.setUnlocalizedName(ReferenceName.ITEM_BISMUTH_BRONZE_GEAR_NAME);
			break;
		case 6:
			this.setUnlocalizedName(ReferenceName.ITEM_IRON_GEAR_NAME);
			break;
		case 7:
			this.setUnlocalizedName(ReferenceName.ITEM_STEEL_GEAR_NAME);
			break;
		case 8:
			this.setUnlocalizedName(ReferenceName.ITEM_BLACK_STEEL_GEAR_NAME);
			break;
		case 9:
			this.setUnlocalizedName(ReferenceName.ITEM_BLUE_STEEL_GEAR_NAME);
			break;
		case 10:
			this.setUnlocalizedName(ReferenceName.ITEM_RED_STEEL_GEAR_NAME);
			break;
		}
	}
	@Override
	public String getFrameComponent() {
		switch (type) {
		case 0:
			return new WoodenGearComponent().getName();
		case 1:
			return new StoneGearComponent().getName();
		case 2:
			return new CopperGearComponent().getName();
		case 3:
			return new BronzeGearComponent().getName();
		case 4:
			return new BlackBronzeGearComponent().getName();
		case 5:
			return new BismuthBronzeGearComponent().getName();
		case 6:
			return new IronGearComponent().getName();
		case 7:
			return new SteelGearComponent().getName();
		case 8:
			return new BlackSteelGearComponent().getName();
		case 9:
			return new BlueSteelGearComponent().getName();
		case 10:
			return new RedSteelGearComponent().getName();
		default:
			return null;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {
		list.add("Size: "+(itemstack.getItemDamage()+1));
		super.addInformation(itemstack, player, list, bool);
	}
}
