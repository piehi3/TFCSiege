package tfcs.entity;

import tfcs.reference.ReferenceName;

import com.bioxx.tfc.api.TFCItems;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;

public class ProjectileBolt extends ProjectileTFCS {

	ToolMaterial material = TFCItems.copperToolMaterial;

	public ProjectileBolt(World world) {
		super(world);
	}

	public ProjectileBolt(World world, ToolMaterial material) {
		super(world);
		this.setDamage(material.getDamageVsEntity());
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_BOLT_NAME;
	}
}
