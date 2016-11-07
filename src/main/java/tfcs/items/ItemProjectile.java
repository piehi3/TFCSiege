package tfcs.items;

import net.minecraft.world.World;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.entity.ProjectileBullet;
import tfcs.entity.ProjectileCannonBall;
import tfcs.entity.ProjectileCeramicShell;
import tfcs.entity.ProjectileLongArrow;
import tfcs.entity.ProjectileTFCS;
import tfcs.reference.ReferenceName;

public class ItemProjectile extends ItemTFCS{
	int type = 0;
	public ItemProjectile(int type) {
		this.type = type;
		switch (type) {
		case 0:
			this.setUnlocalizedName(ReferenceName.ITEM_CERAMIC_SHELL_NAME);
			break;
		case 1:
			this.setUnlocalizedName(ReferenceName.ITEM_LONG_ARROW_NAME);
			break;
		case 2:
			this.setUnlocalizedName(ReferenceName.ITEM_BULLET_NAME);
			break;
		case 3:
			this.setUnlocalizedName(ReferenceName.ITEM_LONG_ARROW_NAME+"_fire");
			break;
		case 4:
			this.setUnlocalizedName(ReferenceName.ITEM_CANNON_BALL_NAME);
			break;
		default:
			break;
		}
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
		this.setMaxStackSize(16);
	}
	
	public ProjectileTFCS getProjectile(World world) {
		switch (type) {
		case 0:
			return new ProjectileCeramicShell(world);
		case 1:
			return new ProjectileLongArrow(world);
		case 2:
			return new ProjectileBullet(world);
		case 3:
			return new ProjectileLongArrow(world).setFlamming();
		case 4:
			return new ProjectileCannonBall(world);
		default:
			return null;
		}
	}
}
