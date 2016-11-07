package tfcs.jobs;

import java.util.Iterator;
import java.util.List;

import tfcs.entity.EntitySettler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.AxisAlignedBB;

public class SoldierAIFindTarget extends EntityAITarget {

	public SoldierAIFindTarget(EntitySettler entity, boolean bool) {
		super(entity, bool);
	}

	@Override
	public boolean shouldExecute() {
		return taskOwner.getAttackTarget() == null;
	}

	@Override
	public void startExecuting() {
		if (shouldExecute()) {
			double d0 = this.getTargetDistance();
			List list = this.taskOwner.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
					AxisAlignedBB.getBoundingBox(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)
							.expand(d0, 10.0D, d0));
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityLivingBase entitycreature = (EntityLivingBase) iterator.next();
				if (entitycreature != null && this.taskOwner != entitycreature && isSuitableTarget(entitycreature, true)) {
					this.taskOwner.setAttackTarget(entitycreature);
				}
			}
		}

		super.startExecuting();
	}

	@Override
	protected boolean isSuitableTarget(EntityLivingBase entity, boolean bool) {
		float f = 1000;
		if (taskOwner.getAttackTarget() != null)
			f = taskOwner.getDistanceToEntity(taskOwner.getAttackTarget());
		return this.taskOwner.canAttackClass(entity.getClass()) && ((EntitySettler) taskOwner).canAttackEntity(entity) && taskOwner.getDistanceToEntity(entity) < f;
	}

}
