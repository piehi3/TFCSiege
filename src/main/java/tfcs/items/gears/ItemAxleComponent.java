package tfcs.items.gears;

import tfcs.gears.FrameComponent;
import tfcs.gears.axle.BismuthBronzeAxleComponent;
import tfcs.gears.axle.BlackBronzeAxleComponent;
import tfcs.gears.axle.BlackSteelAxleComponent;
import tfcs.gears.axle.BlueSteelAxleComponent;
import tfcs.gears.axle.BronzeAxleComponent;
import tfcs.gears.axle.CopperAxleComponent;
import tfcs.gears.axle.IronAxleComponent;
import tfcs.gears.axle.RedSteelAxleComponent;
import tfcs.gears.axle.SteelAxleComponent;
import tfcs.gears.axle.StoneAxleComponent;
import tfcs.gears.axle.WoodenAxleComponent;
import tfcs.reference.ReferenceName;

public class ItemAxleComponent extends ItemFrameComponent{
	int type = 0;
	public ItemAxleComponent(int type) {
		this.type = type;
		switch (type) {
		case 0:
			this.setUnlocalizedName(ReferenceName.ITEM_WOODEN_AXLE_NAME);
			break;
		case 1:
			this.setUnlocalizedName(ReferenceName.ITEM_STONE_AXLE_NAME);
			break;
		case 2:
			this.setUnlocalizedName(ReferenceName.ITEM_COPPER_AXLE_NAME);
			break;
		case 3:
			this.setUnlocalizedName(ReferenceName.ITEM_BRONZE_AXLE_NAME);
			break;
		case 4:
			this.setUnlocalizedName(ReferenceName.ITEM_BLACK_BRONZE_AXLE_NAME);
			break;
		case 5:
			this.setUnlocalizedName(ReferenceName.ITEM_BISMUTH_BRONZE_AXLE_NAME);
			break;
		case 6:
			this.setUnlocalizedName(ReferenceName.ITEM_IRON_AXLE_NAME);
			break;
		case 7:
			this.setUnlocalizedName(ReferenceName.ITEM_STEEL_AXLE_NAME);
			break;
		case 8:
			this.setUnlocalizedName(ReferenceName.ITEM_BLACK_STEEL_AXLE_NAME);
			break;
		case 9:
			this.setUnlocalizedName(ReferenceName.ITEM_RED_STEEL_AXLE_NAME);
			break;
		case 10:
			this.setUnlocalizedName(ReferenceName.ITEM_BLUE_STEEL_AXLE_NAME);
			break;
		}
	}
	@Override
	public String getFrameComponent() {
		switch (type) {
		case 0:
			return new WoodenAxleComponent().getName();
		case 1:
			return new StoneAxleComponent().getName();
		case 2:
			return new CopperAxleComponent().getName();
		case 3:
			return new BronzeAxleComponent().getName();
		case 4:
			return new BlackBronzeAxleComponent().getName();
		case 5:
			return new BismuthBronzeAxleComponent().getName();
		case 6:
			return new IronAxleComponent().getName();
		case 7:
			return new SteelAxleComponent().getName();
		case 8:
			return new BlackSteelAxleComponent().getName();
		case 9:
			return new RedSteelAxleComponent().getName();
		case 10:
			return new BlueSteelAxleComponent().getName();
		default:
			return null;
		}
	}
}
