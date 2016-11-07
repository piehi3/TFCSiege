package tfcs.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ibm.icu.impl.duration.impl.DataRecord.EPluralization;

import tfcs.blocks.devices.BlockWall;
import tfcs.tileentities.TileEntityWall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TFCSExplosion extends Explosion {

	private int rotations = 16;
	private Random explosionRNG = new Random();
	private World worldObj;
	private Map hashMap = new HashMap();
	private float explotionModdifier;

	public TFCSExplosion(World world, Entity entity, double x, double y, double z, float expotionSize,float explotionModdifier) {
		super(world, entity, x, y, z, expotionSize);
		this.worldObj = world;
		this.explotionModdifier = explotionModdifier;
	}

	public static void explode(World world, Entity entity, double x, double y, double z, float expotionSize,float expotionModdifier, boolean doParticles, boolean isFlaming, boolean isSmoking) {
		TFCSExplosion explotion = new TFCSExplosion(world, entity, x, y, z, expotionSize,expotionModdifier);
		explotion.doExplosionA();
		explotion.doExplosionB(doParticles);
		explotion.isFlaming = isFlaming;
		explotion.isSmoking = isSmoking;
	}

	public void doExplosionA() {
		float f = this.explosionSize;
		HashSet hashset = new HashSet();
		int i;
		int j;
		int k;
		double d5;
		double d6;
		double d7;

		for (i = 0; i < this.rotations; ++i) {
			for (j = 0; j < this.rotations; ++j) {
				for (k = 0; k < this.rotations; ++k) {
					if (i == 0 || i == this.rotations - 1 || j == 0 || j == this.rotations - 1 || k == 0 || k == this.rotations - 1) {
						double d0 = (double) ((float) i / ((float) this.rotations - 1.0F) * 2.0F - 1.0F);
						double d1 = (double) ((float) j / ((float) this.rotations - 1.0F) * 2.0F - 1.0F);
						double d2 = (double) ((float) k / ((float) this.rotations - 1.0F) * 2.0F - 1.0F);
						double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
						d0 /= d3;
						d1 /= d3;
						d2 /= d3;
						float f1 = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
						d5 = this.explosionX;
						d6 = this.explosionY;
						d7 = this.explosionZ;

						for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
							int j1 = MathHelper.floor_double(d5);
							int k1 = MathHelper.floor_double(d6);
							int l1 = MathHelper.floor_double(d7);
							Block block = this.worldObj.getBlock(j1, k1, l1);

							if (block.getMaterial() != Material.air && !(block instanceof BlockWall)) {
								float f3 = this.exploder != null ? this.exploder.func_145772_a(this, this.worldObj, j1, k1, l1, block) : block.getExplosionResistance(this.exploder, worldObj, j1, k1,
										l1, explosionX, explosionY, explosionZ);
								if(block.getMaterial() != Material.rock)
									f1 -= (f3 + 0.3F) * f2;
								else
									f1 -= ((f3/explotionModdifier) + 0.3F) * f2;
							} else if (worldObj.getTileEntity(j1, k1, l1) != null && worldObj.getTileEntity(j1, k1, l1) instanceof TileEntityWall) {
								TileEntityWall tileentity = (TileEntityWall) worldObj.getTileEntity(j1, k1, l1);
								float f3 = tileentity.getHitPoints();
								if(tileentity.damageWall((int) (f1 * explotionModdifier)))
									worldObj.setBlockToAir(j1, k1, l1);
								f1 -= f3;
							}

							if (f1 > 0.0F && (this.exploder == null || this.exploder.func_145774_a(this, this.worldObj, j1, k1, l1, block, f1))) {
								hashset.add(new ChunkPosition(j1, k1, l1));
							}

							d5 += d0 * (double) f2;
							d6 += d1 * (double) f2;
							d7 += d2 * (double) f2;
						}
					}
				}
			}
		}

		this.affectedBlockPositions.addAll(hashset);
		this.explosionSize *= 2.0F;
		i = MathHelper.floor_double(this.explosionX - (double) this.explosionSize - 1.0D);
		j = MathHelper.floor_double(this.explosionX + (double) this.explosionSize + 1.0D);
		k = MathHelper.floor_double(this.explosionY - (double) this.explosionSize - 1.0D);
		int i2 = MathHelper.floor_double(this.explosionY + (double) this.explosionSize + 1.0D);
		int l = MathHelper.floor_double(this.explosionZ - (double) this.explosionSize - 1.0D);
		int j2 = MathHelper.floor_double(this.explosionZ + (double) this.explosionSize + 1.0D);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getBoundingBox((double) i, (double) k, (double) l, (double) j, (double) i2, (double) j2));
		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, this.explosionSize);
		Vec3 vec3 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double) this.explosionSize;

			if (d4 <= 1.0D) {
				d5 = entity.posX - this.explosionX;
				d6 = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
				d7 = entity.posZ - this.explosionZ;
				double d9 = (double) MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);

				if (d9 != 0.0D) {
					d5 /= d9;
					d6 /= d9;
					d7 /= d9;
					double d10 = (double) this.worldObj.getBlockDensity(vec3, entity.boundingBox);
					double d11 = (1.0D - d4) * d10;
					entity.attackEntityFrom(DamageSource.setExplosionSource(this), (float) ((int) (((d11 * d11 + d11) / 2.0D * 8.0D * (double) this.explosionSize + 1.0D)*explotionModdifier) ));
					double d8 = EnchantmentProtection.func_92092_a(entity, d11);
					entity.motionX += d5 * d8;
					entity.motionY += d6 * d8;
					entity.motionZ += d7 * d8;

					if (entity instanceof EntityPlayer) {
						this.hashMap.put((EntityPlayer) entity, Vec3.createVectorHelper(d5 * d11, d6 * d11, d7 * d11));
					}
				}
			}
		}

		this.explosionSize = f;
	}

	public Map getHashMap() {
		return hashMap;
	}
	
}
