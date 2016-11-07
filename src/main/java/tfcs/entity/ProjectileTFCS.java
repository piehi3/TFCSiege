package tfcs.entity;

import tfcs.util.BlockHelper;

import com.bioxx.tfc.Entities.EntityProjectileTFC;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ProjectileTFCS extends EntityProjectileTFC {
	
	boolean inground = false;
	
	public ProjectileTFCS(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		boolean inground = nbt.hasKey("inGround") && nbt.getByte("inGround") == 1;
		this.inground = inground;
		
		if (inground)
			onHitGround(this.worldObj, BlockHelper.shiftCoord(this.posX), BlockHelper.shiftCoord(this.posY), BlockHelper.shiftCoord(this.posZ));
	}

	public void onHitGround(World world, int x, int y, int z) {
		
	}

	public void onCollideWithEntity(Entity entity) {

	}

	public void setVelocity(double x, double y, double z) {
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		if(this.inground)
			this.canBePickedUp = 1;
		super.onCollideWithPlayer(player);
		onCollideWithEntity(player);
	}

	public String getName() {
		return null;
	}

}
