package tfcs.entity;

import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import net.minecraft.world.World;

public class EntityWarHorse extends EntityHorseTFC{

	public EntityWarHorse(World par1World) {
		super(par1World);
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
	}
	
	@Override
	protected void updateWanderPath() {
		
		super.updateWanderPath();
	}
	
	public double getMountedYOffset() {
		return (double) this.height/2;
	}
	
}
