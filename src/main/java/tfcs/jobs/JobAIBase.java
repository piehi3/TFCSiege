package tfcs.jobs;

import tfcs.entity.EntitySettler;
import net.minecraft.entity.ai.EntityAIBase;

public abstract class JobAIBase extends EntityAIBase {

	EntitySettler taskOwner;
	public JobAIBase(EntitySettler es) {
		taskOwner = es;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public abstract boolean shouldExecute();

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public abstract boolean continueExecuting();

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public abstract void startExecuting();

	/**
	 * Resets the task
	 */
	public abstract void resetTask();

	/**
	 * Updates the task
	 */
	public abstract void updateTask();
}
