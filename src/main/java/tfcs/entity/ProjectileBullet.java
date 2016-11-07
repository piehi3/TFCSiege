package tfcs.entity;

import net.minecraft.world.World;

public class ProjectileBullet extends ProjectileTFCS {

	public ProjectileBullet(World world) {
		super(world);
		this.setDamage(250.0F);
	}

}
