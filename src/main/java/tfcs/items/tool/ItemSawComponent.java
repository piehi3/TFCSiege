package tfcs.items.tool;

import tfcs.gears.tool.BismuthBronzeSawCompenent;
import tfcs.gears.tool.BlackBronzeSawCompenent;
import tfcs.gears.tool.BlackSteelSawCompenent;
import tfcs.gears.tool.BlueSteelSawCompenent;
import tfcs.gears.tool.BronzeSawCompenent;
import tfcs.gears.tool.CopperSawCompenent;
import tfcs.gears.tool.IronSawCompenent;
import tfcs.gears.tool.RedSteelSawCompenent;
import tfcs.gears.tool.SteelSawCompenent;
import tfcs.items.gears.ItemToolComponent;
import tfcs.reference.ReferenceName;

public class ItemSawComponent extends ItemToolComponent {

	String type;

	public ItemSawComponent(String type) {
		super(-1);
		this.type = type;
		this.setUnlocalizedName(ReferenceName.ITEM_SAW_NAME + "." + type);
	}

	@Override
	public String getFrameComponent() {
		if (type.equals("copper"))
			return new CopperSawCompenent().getName();
		if (type.equals("bronze"))
			return new BronzeSawCompenent().getName();
		if (type.equals("blackBronze"))
			return new BlackBronzeSawCompenent().getName();
		if (type.equals("bismuthBronze"))
			return new BismuthBronzeSawCompenent().getName();
		if (type.equals("iron"))
			return new IronSawCompenent().getName();
		if (type.equals("steel"))
			return new SteelSawCompenent().getName();
		if (type.equals("blackSteel"))
			return new BlackSteelSawCompenent().getName();
		if (type.equals("blueSteel"))
			return new BlueSteelSawCompenent().getName();
		if (type.equals("redSteel"))
			return new RedSteelSawCompenent().getName();
		return super.getFrameComponent();
	}

}
