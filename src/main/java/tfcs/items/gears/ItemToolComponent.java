package tfcs.items.gears;

import java.util.List;




import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tfcs.gears.tool.BenSmells;
import tfcs.gears.tool.GateMoverCompenent;
import tfcs.gears.tool.GrindstoneToolComponent;
import tfcs.gears.tool.HandCrankToolComponent;
import tfcs.gears.tool.SawToolComponent;
import tfcs.gears.tool.WaterWheelToolComponent;
import tfcs.gears.tool.WindmillToolComponent;
import tfcs.reference.ReferenceName;


public class ItemToolComponent extends ItemFrameComponent {
	int type = 0;
	
	public ItemToolComponent(int type) {
		this.type = type;
		switch (type) {
		case 0:
			this.setUnlocalizedName(ReferenceName.ITEM_WINDMILL_NAME);
			break;
		case 1:
			this.setUnlocalizedName(ReferenceName.ITEM_GATE_MOVER_NAME);
			break;
		case 2:
			this.setUnlocalizedName(ReferenceName.ITEM_BEN_SMELLS_NAME);
			break;
		case 3:
			this.setUnlocalizedName(ReferenceName.ITEM_WATER_WHEEL_NAME);
			break;
		case 4:
			this.setUnlocalizedName(ReferenceName.ITEM_GRINDSTONE_NAME);
			break;
		case 5:
			this.setUnlocalizedName(ReferenceName.ITEM_HAND_CRANK_NAME);
			break;
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {
		switch (type) {
		case 0:
		case 6:
			list.add("Size: "+(itemstack.getItemDamage()+1));
			break;
		default:
			break;
		}
		super.addInformation(itemstack, player, list, bool);
	}
	
	@Override
	public int getMetadata(int meta) {
		return super.getMetadata(meta);
	}
	
	@Override
	public String getFrameComponent() {
		switch (type) {
		case 0:
			return new WindmillToolComponent().getName();
		case 1:
			return new GateMoverCompenent().getName();
		case 2:
			return new BenSmells().getName();
		case 3:
			return new WaterWheelToolComponent().getName();
		case 4:
			return new GrindstoneToolComponent().getName();
		case 5:
			return new HandCrankToolComponent().getName();
		default:
			return null;
		}
	}
}
