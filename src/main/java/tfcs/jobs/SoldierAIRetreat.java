package tfcs.jobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import tfcs.entity.EntitySettler;

public class SoldierAIRetreat extends JobAIBase {

	public SoldierAIRetreat(EntitySettler es) {
		super(es);
	}

	@Override
	public boolean shouldExecute() {
		return this.taskOwner.doesRetreat() && this.taskOwner.getOwner().getDistanceSqToEntity(this.taskOwner) > 16;
	}

	@Override
	public boolean continueExecuting() {
		return this.taskOwner.doesRetreat() && this.taskOwner.getOwner().getDistanceSqToEntity(this.taskOwner) > 16;
	}

	@Override
	public void startExecuting() {
		this.taskOwner.getNavigator().setSpeed(this.taskOwner.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()*2);
		this.taskOwner.setPathToEntity(this.taskOwner.getNavigator().getPathToEntityLiving(this.taskOwner.getOwner()));
	}

	@Override
	public void resetTask() {

	}

	@Override
	public void updateTask() {
		startExecuting();
	}

}