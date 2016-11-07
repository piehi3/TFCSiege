package tfcs.items.tool;

import tfcs.gears.FrameComponent;
import tfcs.gears.tool.BismuthBronzeCoilCompenent;
import tfcs.gears.tool.BlackBronzeCoilCompenent;
import tfcs.gears.tool.BlackSteelCoilCompenent;
import tfcs.gears.tool.BlueSteelCoilCompenent;
import tfcs.gears.tool.BronzeCoilCompenent;
import tfcs.gears.tool.CopperCoilCompenent;
import tfcs.gears.tool.IronCoilCompenent;
import tfcs.gears.tool.RedSteelCoilCompenent;
import tfcs.gears.tool.SteelCoilCompenent;
import tfcs.items.gears.ItemToolComponent;
import tfcs.reference.ReferenceName;

public class ItemCoilComponent extends ItemToolComponent {

	String type;
	public ItemCoilComponent(String type) {
		super(-1);
		this.type = type;
		this.setUnlocalizedName(ReferenceName.ITEM_COIL_NAME + "." + type);
	}

	@Override
	public String getFrameComponent() {
		if(type.equals("copper"))
			return new CopperCoilCompenent().getName();
		if(type.equals("bronze"))
			return new BronzeCoilCompenent().getName();
		if(type.equals("blackBronze"))
			return new BlackBronzeCoilCompenent().getName();
		if(type.equals("bismuthBronze"))
			return new BismuthBronzeCoilCompenent().getName();
		if(type.equals("iron"))
			return new IronCoilCompenent().getName();
		if(type.equals("steel"))
			return new SteelCoilCompenent().getName();
		if(type.equals("blackSteel"))
			return new BlackSteelCoilCompenent().getName();
		if(type.equals("blueSteel"))
			return new BlueSteelCoilCompenent().getName();
		if(type.equals("redSteel"))
			return new RedSteelCoilCompenent().getName();
		return super.getFrameComponent();
	}
	
}
