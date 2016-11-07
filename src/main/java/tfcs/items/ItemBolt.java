package tfcs.items;

import net.minecraft.world.World;
import tfcs.entity.ProjectileBolt;
import tfcs.entity.ProjectileTFCS;
import tfcs.reference.ReferenceName;

public class ItemBolt extends ItemProjectile{
	ToolMaterial material = null;
	public ItemBolt(ToolMaterial material) {
		super(-1);
		this.material = material;
		this.setUnlocalizedName(ReferenceName.ITEM_BOLT_NAME+"."+material.name());
	}

	@Override
	public ProjectileTFCS getProjectile(World world) {
		return new ProjectileBolt(world,material);
	}
	
}
