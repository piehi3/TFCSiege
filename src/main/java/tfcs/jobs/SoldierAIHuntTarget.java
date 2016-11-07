package tfcs.jobs;

import net.minecraft.entity.SharedMonsterAttributes;
import tfcs.entity.EntitySettler;

public class SoldierAIHuntTarget extends JobAIBase{

	
	public SoldierAIHuntTarget(EntitySettler es) {
		super(es);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldExecute() {
		return this.taskOwner.getAttackTarget()!=null;
	}

	@Override
	public boolean continueExecuting() {
		return this.taskOwner.getAttackTarget()!=null;
	}

	@Override
	public void startExecuting() {
		this.taskOwner.getNavigator().setSpeed(this.taskOwner.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()*2);
		this.taskOwner.setPathToEntity(this.taskOwner.getNavigator().getPathToEntityLiving(this.taskOwner.getAttackTarget()));
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
