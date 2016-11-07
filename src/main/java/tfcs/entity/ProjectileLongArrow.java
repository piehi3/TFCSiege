package tfcs.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ProjectileLongArrow extends ProjectileTFCS {

	boolean isFlamming = false;

	public ProjectileLongArrow(World world) {
		super(world);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	public ProjectileLongArrow setFlamming() {
		this.isFlamming = true;
		return this;
	}

	@Override
	public void onHitGround(World world, int x, int y, int z) {
		super.onHitGround(world, x, y, z);
		if(isFlamming){
		setFireToBlock(world, x + 1, y, z);
		setFireToBlock(world, x - 1, y, z);
		setFireToBlock(world, x, y + 1, z);
		setFireToBlock(world, x, y - 1, z);
		setFireToBlock(world, x, y, z + 1);
		setFireToBlock(world, x, y, z - 1);
		this.setDead();
	}
	}

	private void setFireToBlock(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).equals(Blocks.air))
			world.setBlock(x, y, z, Blocks.fire);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		if(!isFlamming)
			super.onCollideWithPlayer(player);
	}
	
}
