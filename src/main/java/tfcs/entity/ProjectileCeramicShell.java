package tfcs.entity;

import tfcs.reference.ReferenceName;
import tfcs.util.BlockHelper;
import tfcs.util.TFCSExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ProjectileCeramicShell extends ProjectileTFCS {

	double gunPowder = 4;
	
	public ProjectileCeramicShell(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
	@Override
	public void onHitGround(World world, int x, int y, int z) {
		detinate();
	}
	
	@Override
	public void onCollideWithEntity(Entity entity) {
		detinate();
	}
	
	private void detinate() {
		this.setDead();
		TFCSExplosion.explode(worldObj, null, BlockHelper.shiftCoord(posX), BlockHelper.shiftCoord(posY), BlockHelper.shiftCoord(posZ), 2.5F,1, true, true, true);
	}
	
	@Override
	public String getName() {
		return ReferenceName.ITEM_CERAMIC_SHELL_NAME;
	}
	
}
