package tfcs.jobs;

import net.minecraft.entity.SharedMonsterAttributes;
import tfcs.entity.EntitySettler;

public class SoldierFollowRoute extends JobAIBase {

	public SoldierFollowRoute(EntitySettler es) {
		super(es);
	}

	@Override
	public boolean shouldExecute() {
		return this.taskOwner.getAttackTarget() == null;
	}

	@Override
	public boolean continueExecuting() {
		return this.taskOwner.getAttackTarget() == null;
	}

	@Override
	public void startExecuting() {
		this.taskOwner.getNavigator().setSpeed(this.taskOwner.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
		int[] array = this.taskOwner.getPoint(this.taskOwner.getPosition());
		if (array != null) {
			this.taskOwner.setPathToEntity(this.taskOwner.getNavigator().getPathToXYZ(array[0], array[1], array[2]));
			if (this.taskOwner.getDistanceSq(array[0], array[1], array[2]) <= 1.5)
				this.taskOwner.nextPostion();
		}else{
			this.taskOwner.nextPostion();
		}
	}

	@Override
	public void resetTask() {

	}

	@Override
	public void updateTask() {
		startExecuting();
	}

}