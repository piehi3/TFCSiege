package tfcs.entity;

import tfcs.core.ValueRegistry;
import tfcs.handlers.network.MessageSyncEntityToClient;
import tfcs.handlers.network.PacketHandler;
import tfcs.items.ItemBattleHorn;
import tfcs.reference.ReferenceName;
import tfcs.util.Trade;

import com.bioxx.tfc.api.Enums.EnumDamageType;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTravelingSalesman extends EntitySettler {

	Trade trade;

	public EntityTravelingSalesman(EntityLivingBase entity) {
		super(entity);
	}

	public EntityTravelingSalesman(World world) {
		super(world);
	}

	@Override
	protected void settlerInit() {
		super.settlerInit();
		trade = new Trade(ReferenceName.TRADE_MILITARY_NAME, this.worldObj.rand);
	}
 
	@Override
	public boolean canAttackClass(Class entityClass) {
		return super.canAttackClass(entityClass)&&entityClass!=EntityTravelingSalesman.class;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10000.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(20.0F);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);

		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(30.0D);
	}

	public void increaseStack(int i, ItemStack itemstack) {
		if (getStackInSlot(i) == null) {
			setInventorySlotContents(i, itemstack);
		} else {
			setInventorySlotContents(i, new ItemStack(itemstack.getItem(), getStackInSlot(i).stackSize + itemstack.stackSize,itemstack.getItemDamage()));
		}
	}

	public void buyItem(int i, ItemStack itemstack, int cost) {
		if (this.getValueCount() >= cost) {
			increaseStack(i, itemstack);
			decreaseValueCount(cost);
		}
	}

	private void decreaseValueCount(int cost) {
		while (cost > 0) {
			if (getStackInSlot(9) != null) {
				cost -= ValueRegistry.lookupItemStackValue(getStackInSlot(9));
				decrStackSize(9, 1);
			} else if (getStackInSlot(10) != null) {
				cost -= ValueRegistry.lookupItemStackValue(getStackInSlot(10));
				decrStackSize(10, 1);
			} else if (getStackInSlot(11) != null) {
				cost -= ValueRegistry.lookupItemStackValue(getStackInSlot(11));
				decrStackSize(11, 1);
			} else if (getStackInSlot(12) != null) {
				cost -= ValueRegistry.lookupItemStackValue(getStackInSlot(12));
				decrStackSize(12, 1);
			} else {
				break;
			}
		}
	}

	public int getValueCount() {
		int c = 0;
		if (getStackInSlot(9) != null)
			c += (ValueRegistry.lookupItemStackValue(getStackInSlot(9)) * getStackInSlot(9).stackSize);
		if (getStackInSlot(10) != null)
			c += (ValueRegistry.lookupItemStackValue(getStackInSlot(10)) * getStackInSlot(10).stackSize);
		if (getStackInSlot(11) != null)
			c += (ValueRegistry.lookupItemStackValue(getStackInSlot(11)) * getStackInSlot(11).stackSize);
		if (getStackInSlot(12) != null)
			c += (ValueRegistry.lookupItemStackValue(getStackInSlot(12)) * getStackInSlot(12).stackSize);
		return c;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		this.trade.writeToNBT("TRADE",tag);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		this.trade.readFromNBT("TRADE", tag);
	}
	
	@Override
	protected boolean interact(EntityPlayer player) {
		this.setAttackTarget(null);
		PacketHandler.network.sendToAllAround(new MessageSyncEntityToClient(this), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 20));
		if (!this.inventoryOpen) {
			openGui(player);
		}
		return super.interact(player);
	}
	
	@Override
	public int getGuiID() {
		return 1;
	}

	@Override
	public int getBaseAttackDamage() {
		return 500;
	}

	@Override
	public int getBaseCurshArmor() {
		return 400;
	}

	@Override
	public int getBasePierceArmor() {
		return 1400;
	}

	@Override
	public int getBaseSlashArmor() {
		return 500;
	}

	@Override
	public EnumDamageType getDamageType() {
		return EnumDamageType.CRUSHING;
	}

	public Trade getTrade() {
		return trade;
	}

}
