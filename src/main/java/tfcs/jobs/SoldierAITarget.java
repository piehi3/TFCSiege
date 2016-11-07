package tfcs.jobs;

import java.util.Iterator;
import java.util.List;

import tfcs.entity.EntitySettler;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.AxisAlignedBB;

public class SoldierAITarget extends EntityAITarget {

	public SoldierAITarget(EntitySettler entity, boolean bool) {
		super(entity, bool);
	}

	@Override
	public boolean shouldExecute() {
		return (this.taskOwner.getHealth() > 100 || ((EntitySettler) this.taskOwner).getFightToDeath())&&this.taskOwner.getAITarget()!=null;
	}

	@Override
	public void startExecuting() {
		this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());

		if (((EntitySettler) this.taskOwner).entityCallsForHelp()) {
			double d0 = this.getTargetDistance();
			List list = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(),
					AxisAlignedBB.getBoundingBox(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)
							.expand(d0, 10.0D, d0));
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityCreature entitycreature = (EntityCreature) iterator.next();

				if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && !entitycreature.isOnSameTeam(this.taskOwner.getAITarget())) {
					entitycreature.setAttackTarget(this.taskOwner.getAITarget());
				}
			}
		}
		super.startExecuting();
	}
	@Override
	protected boolean isSuitableTarget(EntityLivingBase entity, boolean b) {
		return entity!=null && taskOwner.canAttackClass(entity.getClass()) && ((EntitySettler)taskOwner).canAttackEntity(entity);
	}
	
}
