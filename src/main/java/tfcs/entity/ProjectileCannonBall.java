package tfcs.entity;

import tfcs.reference.ReferenceName;
import tfcs.util.CalculationHelper;
import tfcs.util.TFCSExplosion;
import net.minecraft.world.World;

public class ProjectileCannonBall extends ProjectileTFCS {

	public ProjectileCannonBall(World world) {
		super(world);
		this.setDamage(500.0F);
	}
	
	@Override
	public void onHitGround(World world, int x, int y, int z) {
		this.setDead();
		TFCSExplosion.explode(worldObj, shootingEntity, posX, posY, posZ, 1.5F, (float) (3 * CalculationHelper.pythagoreanTheorem3D(this.motionX, this.motionY, this.motionZ)), true, false, false);
	}
	@Override
	public String getName() {
		return ReferenceName.ITEM_CANNON_BALL_NAME;
	}
}
