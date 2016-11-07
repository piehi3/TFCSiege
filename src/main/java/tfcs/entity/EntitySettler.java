package tfcs.entity;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.bioxx.tfc.Core.TFC_Sounds;
import com.bioxx.tfc.Entities.Mobs.EntitySquidTFC;
import com.bioxx.tfc.Items.Tools.ItemWeapon;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.IInnateArmor;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import tfcs.TFCSiege;
import tfcs.handlers.network.MessageSyncEntityToClient;
import tfcs.handlers.network.PacketHandler;
import tfcs.items.ItemBattleHorn;
import tfcs.items.ItemTFCSTool;
import tfcs.jobs.JobAIBase;
import tfcs.jobs.QuarryManagerAI;
import tfcs.jobs.SoldierAIFindTarget;
import tfcs.jobs.SoldierAIFollowOwner;
import tfcs.jobs.SoldierAIHuntTarget;
import tfcs.jobs.SoldierAIRetreat;
import tfcs.jobs.SoldierAITarget;
import tfcs.jobs.SoldierFollowRoute;
import tfcs.util.ItemStackHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySettler extends EntityCreature implements ICausesDamage, IInnateArmor, IEntityOwnable, IInventory {

	JobAIBase job;
	int[][] points = new int[7][3];
	private ItemStack[] inventory = new ItemStack[9];
	private ItemStack shieldItemStack;
	int fullWorkCycle = 100;
	int workCycle = 0;
	byte type = 1;
	boolean inventoryOpen;
	private SoldierAIRetreat soldierAIRetreat;
	private SoldierAIFollowOwner soldierAIFollowOwner;

	public EntitySettler(EntityLivingBase entity) {
		this(entity.worldObj);
		setOwner(entity.getUniqueID().toString());
	}

	public EntitySettler(World world) {
		super(world);
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		if (type == 0) {
			job = new QuarryManagerAI(this);
			this.fullWorkCycle = 100;
		}
		if (type == 1) {
			soldierAIRetreat = new SoldierAIRetreat(this);
			soldierAIFollowOwner = new SoldierAIFollowOwner(this);
			getNavigator().setCanSwim(true);
			getNavigator().setEnterDoors(true);
			targetTasks.addTask(0, new SoldierAIFindTarget(this, true));
			targetTasks.addTask(1, new SoldierAITarget(this, true));
			tasks.addTask(0, soldierAIRetreat);
			tasks.addTask(1, new EntityAIAttackOnCollide(this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() * 1.5F, true));
			tasks.addTask(2, new EntityAILeapAtTarget(this, (float) (this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() * 1.5F)));
			tasks.addTask(3, new SoldierAIHuntTarget(this));
			tasks.addTask(4, soldierAIFollowOwner);
			tasks.addTask(5, new SoldierFollowRoute(this));
		}
		if (job != null)
			tasks.addTask(1, job);
	}

	protected void entityInit() {
		super.entityInit();
		this.settlerInit();
	}

	protected void settlerInit() {
		this.dataWatcher.addObject(17, "");
		this.dataWatcher.addObject(18, getStringToBoolean(false));// attack
																	// Animals
		this.dataWatcher.addObject(19, getStringToBoolean(false));// attack
																	// Players
		this.dataWatcher.addObject(20, getStringToBoolean(false));// attack
																	// Drawn
																	// Players
		this.dataWatcher.addObject(21, getStringToBoolean(false));// attack
																	// Owner
		this.dataWatcher.addObject(22, getStringToBoolean(false));// attack

		this.dataWatcher.addObject(23, getStringToBoolean(false));// does wander

		this.dataWatcher.addObject(26, getStringToBoolean(false));// follow
																	// owner

		this.dataWatcher.addObject(24, getStringToBoolean(false));// retreat

		this.dataWatcher.addObject(25, 0);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(10.0F);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);

		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(30.0D);
	}

	@Override
	public void updateRidden() {
		if (this.ridingEntity.riddenByEntity == null)
			this.ridingEntity.riddenByEntity = this;
		super.updateRidden();
		if (this.ridingEntity != null) {
			if (this.ridingEntity instanceof EntityWarHorse) {
				((EntityWarHorse) this.ridingEntity).setPathToEntity(this.getNavigator().getPath());
			}
		}
	}

	@Override
	public void onUpdate() {
		if (workCycle == fullWorkCycle) {
			workCycle = 0;
			onWorkUpdate();
		} else {
			workCycle++;
		}
		if (!inventoryOpen) {
			tasks.onUpdateTasks();
			targetTasks.onUpdateTasks();
			super.onUpdate();
			if (this.getAttackTarget() != null && !canAttackEntity(this.getAttackTarget()))
				this.setAttackTarget(null);
		}
	}

	private void onWorkUpdate() {
		this.heal(1.0F);
	}

	@Override
	protected boolean interact(EntityPlayer player) {
		this.setAttackTarget(null);
		PacketHandler.network.sendToAllAround(new MessageSyncEntityToClient(this), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 20));
		if (!this.inventoryOpen && player.equals(this.getOwner()) && (player.getCurrentEquippedItem() == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemBattleHorn))) {
			openGui(player);
		}
		return super.interact(player);
	}

	public void openGui(EntityPlayer player) {
		player.openGui(TFCSiege.instance, getGuiID(), this.worldObj, this.getEntityId(), (int) posY, (int) posZ);
	}

	public int getGuiID() {
		return 0;
	}

	public void mountHorse() {
		double d0 = 2.0;
		List list = this.worldObj.getEntitiesWithinAABB(EntityWarHorse.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D)
				.expand(d0, 2.0D, d0));
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			EntityWarHorse entityWarHorse = (EntityWarHorse) iterator.next();
			System.out.println(entityWarHorse);
			if (entityWarHorse.riddenByEntity == null) {
				entityWarHorse.riddenByEntity = this;
				this.ridingEntity = entityWarHorse;
			}
		}
	}

	public void setInventoryOpen(boolean inventoryOpen) {
		this.inventoryOpen = inventoryOpen;
	}

	@Override
	public boolean canAttackClass(Class entityClass) {
		return super.canAttackClass(entityClass) && entityClass != EntitySquidTFC.class;
	}

	public int getBaseAttackDamage() {
		return 50;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		double dam = getBaseAttackDamage();
		if (this.getEquipmentInSlot(0) != null) {
			if (this.getEquipmentInSlot(0).getItem() instanceof ItemTFCSTool)
				dam += ((ItemTFCSTool) this.getEquipmentInSlot(0).getItem()).getWeaponDamage(this.getEquipmentInSlot(0));
			else if (this.getEquipmentInSlot(0).getItem() instanceof ItemWeapon)
				dam += ((ItemWeapon) this.getEquipmentInSlot(0).getItem()).getWeaponDamage(this.getEquipmentInSlot(0));
			this.getEquipmentInSlot(0).attemptDamageItem(1, this.worldObj.rand);
		}
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) dam);
	}

	protected boolean canDespawn() {
		return false;
	}

	public boolean getFightToDeath() {
		return false;
	}

	public boolean entityCallsForHelp() {
		return false;
	}

	public int getBaseCurshArmor() {
		return 0;
	}

	@Override
	public int getCrushArmor() {
		int i = getBaseCurshArmor();
		if (this.getEquipmentInSlot(1) != null)
			if (this.getEquipmentInSlot(1).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getCrushArmor();
		if (this.getEquipmentInSlot(2) != null)
			if (this.getEquipmentInSlot(2).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getCrushArmor();
		if (this.getEquipmentInSlot(3) != null)
			if (this.getEquipmentInSlot(3).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getCrushArmor();
		if (this.shieldItemStack != null)
			if (this.shieldItemStack.getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.shieldItemStack.getItem()).getCrushArmor();
		return -20 + i;
	}

	public int getBaseSlashArmor() {
		return 0;
	}

	@Override
	public int getSlashArmor() {
		int i = getBaseSlashArmor();
		if (this.getEquipmentInSlot(1) != null)
			if (this.getEquipmentInSlot(1).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getSlashArmor();
		if (this.getEquipmentInSlot(2) != null)
			if (this.getEquipmentInSlot(2).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getSlashArmor();
		if (this.getEquipmentInSlot(3) != null)
			if (this.getEquipmentInSlot(3).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getSlashArmor();
		if (this.shieldItemStack != null)
			if (this.shieldItemStack.getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.shieldItemStack.getItem()).getSlashArmor();
		return -100 + i;
	}

	public int getBasePierceArmor() {
		return 0;
	}

	@Override
	public int getPierceArmor() {
		int i = getBasePierceArmor();
		if (this.getEquipmentInSlot(1) != null)
			if (this.getEquipmentInSlot(1).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getPierceArmor();
		if (this.getEquipmentInSlot(2) != null)
			if (this.getEquipmentInSlot(2).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getPierceArmor();
		if (this.getEquipmentInSlot(3) != null)
			if (this.getEquipmentInSlot(3).getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.getEquipmentInSlot(1).getItem()).getPierceArmor();
		if (this.shieldItemStack != null)
			if (this.shieldItemStack.getItem() instanceof IInnateArmor)
				i += ((IInnateArmor) this.shieldItemStack.getItem()).getPierceArmor();
		return 50 + i;
	}

	@Override
	public EnumDamageType getDamageType() {
		if (this.getEquipmentInSlot(0) != null) {
			if (this.getEquipmentInSlot(0).getItem() instanceof ICausesDamage) {
				ICausesDamage item = (ICausesDamage) this.getEquipmentInSlot(0).getItem();
				return item.getDamageType();
			}
		}
		return EnumDamageType.CRUSHING;
	}

	public boolean canAttackEntity(EntityLivingBase entity) {
		if (getBooleanFromString(21) && entity.equals(this.getOwner())) {
			return true;
		}
		if (entity instanceof EntitySettler && (!((EntitySettler) entity).hasSameOwner(this) || getBooleanFromString(21))) {
			return true;
		}
		if (getBooleanFromString(18) && entity instanceof EntityAnimal) {
			return true;
		}
		if (getBooleanFromString(22) && entity instanceof EntityMob) {
			return true;
		}
		if (entity instanceof EntityPlayer && (getBooleanFromString(19) || (getBooleanFromString(20) && (ItemStackHelper.isWeapon(((EntityPlayer) entity).getCurrentEquippedItem()))))) {
			return true;
		}
		return false;
	}

	private boolean hasSameOwner(EntitySettler owner) {
		if (owner == null)
			return false;
		if (this.getOwner() == null)
			return false;
		return this.func_152113_b().equals(owner.func_152113_b());
	}

	private boolean getBooleanFromString(int i) {
		return dataWatcher.getWatchableObjectString(i).equals("true");
	}

	private String getStringToBoolean(boolean b) {
		if (b)
			return "true";
		else
			return "false";
	}

	public String func_152113_b() {
		return this.dataWatcher.getWatchableObjectString(17);
	}

	public void setOwner(String name) {
		this.dataWatcher.updateObject(17, name);
	}

	public EntityLivingBase getOwner() {
		try {
			UUID uuid = UUID.fromString(this.func_152113_b());
			return uuid == null ? null : this.worldObj.func_152378_a(uuid);
		} catch (IllegalArgumentException illegalargumentexception) {
			return null;
		}
	}

	@Override
	public int getSizeInventory() {
		return inventory.length + 6;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i < inventory.length)
			return inventory[i];
		else if (i - inventory.length < 5)
			return getEquipmentInSlot(i - inventory.length);
		else
			return shieldItemStack;
	}

	@Override
	public ItemStack decrStackSize(int i, int a) {
		ItemStack stack = getStackInSlot(i);
		if (stack != null) {
			if (stack.stackSize <= a) {
				setInventorySlotContents(i, null);
			} else {
				stack = stack.splitStack(a);
				if (stack.stackSize == 0) {
					setInventorySlotContents(i, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack stack = getStackInSlot(i);
		if (stack != null) {
			setInventorySlotContents(i, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack is) {
		if (is != null && is.stackSize > getInventoryStackLimit())
			is.stackSize = getInventoryStackLimit();
		if (i < inventory.length)
			inventory[i] = is;
		else if (i - inventory.length < 5)
			setCurrentItemOrArmor(i - inventory.length, is);
		else
			shieldItemStack = is;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		// this.ridingEntity.writeMountToNBT(tag);
	}

	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean("Attack_Animals", getBooleanFromString(18));
		tag.setBoolean("Attack_Players", getBooleanFromString(19));
		tag.setBoolean("Attack_DrawnPlayers", getBooleanFromString(20));
		tag.setBoolean("Attack_Owner", getBooleanFromString(21));
		tag.setBoolean("Attack_Mobs", getBooleanFromString(22));
		tag.setBoolean("Does_Wander", getBooleanFromString(23));
		tag.setBoolean("Does_Retreat", getBooleanFromString(24));
		tag.setBoolean("Follow_Owner", getBooleanFromString(26));
		tag.setInteger("Position", dataWatcher.getWatchableObjectInt(25));

		for (int i = 0; i < points.length; i++)
			if (points[i] != null)
				tag.setIntArray("COORD" + i, points[i]);

		NBTTagList nbttaglist = new NBTTagList();
		NBTTagCompound nbttagcompound;

		for (int i = 0; i < this.inventory.length; ++i) {
			nbttagcompound = new NBTTagCompound();

			if (this.inventory[i] != null) {
				this.inventory[i].writeToNBT(nbttagcompound);
			}

			nbttaglist.appendTag(nbttagcompound);
		}

		tag.setTag("Inventory", nbttaglist);

		if (this.shieldItemStack != null) {
			NBTTagList nbttaglist1 = new NBTTagList();
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			this.shieldItemStack.writeToNBT(nbttagcompound1);
			nbttaglist1.appendTag(nbttagcompound1);
			tag.setTag("Shield", nbttaglist1);
		}

		if (this.func_152113_b() == null) {
			tag.setString("OwnerUUID", "");
		} else {
			tag.setString("OwnerUUID", this.func_152113_b());
		}
	}

	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		dataWatcher.updateObject(18, getStringToBoolean(tag.getBoolean("Attack_Animals")));
		dataWatcher.updateObject(19, getStringToBoolean(tag.getBoolean("Attack_Players")));
		dataWatcher.updateObject(20, getStringToBoolean(tag.getBoolean("Attack_DrawnPlayers")));
		dataWatcher.updateObject(21, getStringToBoolean(tag.getBoolean("Attack_Owner")));
		dataWatcher.updateObject(22, getStringToBoolean(tag.getBoolean("Attack_Mobs")));
		dataWatcher.updateObject(23, getStringToBoolean(tag.getBoolean("Does_Wander")));
		dataWatcher.updateObject(24, getStringToBoolean(tag.getBoolean("Does_Retreat")));
		dataWatcher.updateObject(26, getStringToBoolean(tag.getBoolean("Follow_Owner")));
		dataWatcher.updateObject(25, tag.getInteger("Position"));

		for (int i = 0; i < points.length; i++)
			if (tag.hasKey("COORD" + i))
				points[i] = tag.getIntArray("COORD" + i);

		NBTTagList nbttaglist;
		int i;
		if (tag.hasKey("Inventory", 9)) {
			nbttaglist = tag.getTagList("Inventory", 10);

			for (i = 0; i < this.inventory.length; ++i) {
				this.inventory[i] = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));
			}
		}
		if (tag.hasKey("Shield", 9)) {
			nbttaglist = tag.getTagList("Shield", 10);
			this.shieldItemStack = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(0));
		}

		String s = "";

		if (tag.hasKey("OwnerUUID", 8)) {
			s = tag.getString("OwnerUUID");
		} else {
			String s1 = tag.getString("Owner");
			s = PreYggdrasilConverter.func_152719_a(s1);
		}
		this.setOwner(s);
	}

	@Override
	protected void dropEquipment(boolean bool, int i) {
		// if (bool) {
		for (int j = 0; j < this.getSizeInventory(); j++)
			if (this.getStackInSlot(j) != null)
				this.entityDropItem(this.getStackInSlot(j), 0.1F);
		// }
	}

	@Override
	protected void updateWanderPath() {
		if (doesWander()) {
			super.updateWanderPath();
		}
	}

	@Override
	public String getInventoryName() {

		return "tfcs:entitySettler.gui";
	}

	@Override
	public boolean hasCustomInventoryName() {

		return true;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public void markDirty() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(posX, posY, posZ) < 10;
	}

	@Override
	public void openInventory() {
		this.inventoryOpen = true;
	}

	@Override
	public void closeInventory() {
		this.inventoryOpen = false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {

		return true;
	}

	private void toggle(int i) {
		dataWatcher.updateObject(i, getStringToBoolean(!getBooleanFromString(i)));
	}

	public void toggleAttacksAnimal() {
		toggle(18);
	}

	public void toggleAttacksPlayer() {
		toggle(19);
	}

	public void toggleAttacksDrawnPlayer() {
		toggle(20);
	}

	public void toggleAttacksOwner() {
		toggle(21);
	}

	public void toggleAttacksMobs() {
		toggle(22);
	}

	public void toggleWander() {
		toggle(23);
	}

	public void toggleFollowOwner() {
		if (this.getBooleanFromString(26)) {
			this.tasks.removeTask(soldierAIFollowOwner);
		} else {
			this.tasks.addTask(4, soldierAIFollowOwner);
		}
		toggle(26);
	}

	public void toggleRetreat() {
		if (this.getBooleanFromString(24)) {
			this.tasks.removeTask(soldierAIRetreat);
		} else {
			this.tasks.addTask(0, soldierAIRetreat);
		}
		toggle(24);
	}

	public void setRetreat() {
		dataWatcher.updateObject(24, "true");
	}

	public void nextPostion() {
		if (dataWatcher.getWatchableObjectInt(25) < points.length - 1)
			dataWatcher.updateObject(25, dataWatcher.getWatchableObjectInt(25) + 1);
		else
			dataWatcher.updateObject(25, 0);
	}

	public int getPosition() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	public boolean addPostion(int x, int y, int z) {
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null || (points[i][0] == 0 && points[i][1] == 0 && points[i][2] == 0)) {
				if (i == 0)
					points[i] = new int[] { (int) (this.posX + x), (int) (this.posY + y), (int) (this.posZ + z) };
				else
					points[i] = new int[] { (int) (this.points[i - 1][0] + x), (int) (this.points[i - 1][1] + y), (int) (this.points[i - 1][2] + z) };
				return true;
			}
		}
		return false;
	}

	public boolean removePotsion() {
		/*
		 * for (int i = 0; i <points.length; i++) { if (points[points.length - i
		 * - 1] != null || !(points[points.length - i - 1][0] == 0 &&
		 * points[points.length - i - 1][1] == 0 && points[points.length - i -
		 * 1][2] == 0)) { points[points.length - i - 1] = null; return true; } }
		 */
		this.points = new int[7][3];
		return false;
	}

	public int[][] getPoints() {
		return points;
	}

	public int[] getPoint(int i) {
		int[] array = points[i];
		if (array == null || array[0] == 0 && array[1] == 0 && array[2] == 0)
			return null;
		return array;
	}

	public boolean doesAttackAnimals() {
		return getBooleanFromString(18);
	}

	public boolean doesAttackPlayers() {
		return getBooleanFromString(19);
	}

	public boolean doesAttackOwner() {
		return getBooleanFromString(20);
	}

	public boolean doesAttackDrawnPlayers() {
		return getBooleanFromString(21);
	}

	public boolean doesAttackMobs() {
		return getBooleanFromString(22);
	}

	public boolean doesWander() {
		return getBooleanFromString(23);
	}

	public boolean doesRetreat() {
		return getBooleanFromString(24);
	}

	public boolean doesFollowOwner() {
		return getBooleanFromString(26);
	}

	public ItemStack getShield() {
		return this.shieldItemStack;
	}

	@Override
	protected String getLivingSound() {
		return TFC_Sounds.METALIMPACT;
	}

	@Override
	protected String getDeathSound() {
		return TFC_Sounds.METALIMPACT;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected float getSoundPitch() {
		return 0.2F;
	}

}
