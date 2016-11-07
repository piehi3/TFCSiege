package tfcs.jobs;

import net.minecraft.entity.SharedMonsterAttributes;
import tfcs.entity.EntitySettler;

public class SoldierAIFollowOwner extends JobAIBase {

	public SoldierAIFollowOwner(EntitySettler es) {
		super(es);
	}

	@Override
	public boolean shouldExecute() {
		return this.taskOwner.getAttackTarget() == null && this.taskOwner.doesFollowOwner() && this.taskOwner.getOwner() != null
				&& this.taskOwner.getOwner().getDistanceSqToEntity(this.taskOwner) > 50;
	}

	@Override
	public boolean continueExecuting() {
		return this.taskOwner.getAttackTarget() == null && this.taskOwner.doesFollowOwner() && this.taskOwner.getOwner() != null
				&& this.taskOwner.getOwner().getDistanceSqToEntity(this.taskOwner) > 50;
	}

	@Override
	public void startExecuting() {
		this.taskOwner.getNavigator().setSpeed(this.taskOwner.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
		this.taskOwner.setPathToEntity(this.taskOwner.getNavigator().getPathToEntityLiving(this.taskOwner.getOwner()));
	}

	@Override
	public void resetTask() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTask() {
		startExecuting();
	}

}
