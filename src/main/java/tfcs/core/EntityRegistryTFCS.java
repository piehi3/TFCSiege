package tfcs.core;

import cpw.mods.fml.common.registry.EntityRegistry;
import tfcs.TFCSiege;
import tfcs.entity.EntityRideableBear;
import tfcs.entity.EntitySettler;
import tfcs.entity.EntityTravelingSalesman;
import tfcs.entity.EntityWarHorse;
import tfcs.entity.ProjectileBolt;
import tfcs.entity.ProjectileCannonBall;
import tfcs.entity.ProjectileCeramicShell;
import tfcs.entity.ProjectileLongArrow;
import tfcs.reference.ReferenceName;

public class EntityRegistryTFCS {
	
	public static void RegisterEntities() {
		registerEntity(ProjectileCeramicShell.class, ReferenceName.ITEM_CERAMIC_SHELL_NAME);
		registerEntity(ProjectileLongArrow.class, ReferenceName.ITEM_LONG_ARROW_NAME);
		registerEntity(ProjectileBolt.class, ReferenceName.ITEM_BOLT_NAME);
		registerEntity(ProjectileCannonBall.class, ReferenceName.ITEM_CANNON_BALL_NAME);
		registerEntity(EntitySettler.class, ReferenceName.ITEM_SETTLER_NAME);
		registerEntity(EntityTravelingSalesman.class, ReferenceName.ITEM_TRAVELING_SALESMAN_NAME);
		
		registerEntity(EntityRideableBear.class, ReferenceName.ITEM_RIDE_BEAR_NAME);
		registerEntity(EntityWarHorse.class, ReferenceName.ITEM_WAR_HORSE_NAME);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void registerEntity(Class entityClass,String entityName){
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName + "entity", entityId);
		EntityRegistry.registerModEntity(entityClass, entityName + "entity", entityId, TFCSiege.instance, 64, 1, true);
	}
}
