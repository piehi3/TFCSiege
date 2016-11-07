package tfcs.gears;

import tfcs.reference.ReferenceResource;
import tfcs.tileentities.TileEntityGearFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class FrameComponent {

	private double maxRotation = 1;
	private double minTorque = 1;
	private boolean shouldRender = true;
	private int forcePlaceCounter;
	private boolean isActive = true;
	private int size = 1;

	public void update(TileEntityGearFrame tileentity) {
		if(forcePlaceCounter>=1){
			if(forcePlaceCounter == 1)
				isActive = onFrameComponentPlace(tileentity);
		}else if (forcePlaceCounter!=0) {
			forcePlaceCounter--;
		}
	}

	public boolean addFrameComponentToTileEntity(TileEntityGearFrame tileentity) {
		if (onFrameComponentPlace(tileentity)) {
			if (addFrameComponentToTileEntitySub(tileentity)) {
				applyAugmentation(tileentity);
				return true;
			}
		}
		return false;
	}

	private boolean addFrameComponentToTileEntitySub(TileEntityGearFrame tileentity) {
		if (this instanceof GearComponent) {
			if (tileentity.getGearComponent() != null) {
				tileentity.setFrameComponent((GearComponent) this,true);
				return true;
			}
		}
		if (this instanceof AxleComponent) {
			if (tileentity.getAxleComponent() != null) {
				tileentity.setFrameComponent((AxleComponent) this,true);
				return true;
			}
		}
		if (this instanceof ToolComponent) {
			if (tileentity.getToolComponent() != null) {
				tileentity.setToolComponent((ToolComponent) this);
				return true;
			}
		}
		return false;
	}

	public void applyAugmentation(TileEntityGearFrame tileentity) {
		tileentity.maxRotation = tileentity.maxRotation * this.maxRotation;
		tileentity.minTorque = tileentity.minTorque * this.minTorque;
	}

	public void removeAugmentation(TileEntityGearFrame tileentity) {
		tileentity.maxRotation /= maxRotation;
		tileentity.minTorque /= minTorque;
	}

	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		return true;
	}

	public ResourceLocation getResourceLocation() {
		return ReferenceResource.WOODEN_COMPONENET_TEXTURE;
	}

	public ResourceLocation getResourceLocationFromTileEntity(TileEntityGearFrame tileentity) {
		return getResourceLocation();
	}

	public void setMaxRotation(double maxRotation) {
		this.maxRotation = maxRotation;
	}

	public void setMinTorque(double minTorque) {
		this.minTorque = minTorque;
	}

	public double getMaxRotation() {
		return maxRotation;
	}

	public double getMinTorque() {
		return minTorque;
	}

	public String getName() {
		return null;
	}

	public String getSaveName() {
		return getName() + "_" + getSize();
	}
	
	public boolean isShouldRender() {
		return shouldRender;
	}

	public void setShouldRender(boolean shouldRender) {
		this.shouldRender = shouldRender;
	}

	public ItemStack getDropItem() {
		return null;
	}

	public void drop(EntityPlayer player, TileEntityGearFrame tileentity) {
		if (getDropItem() != null) {
			if (!player.worldObj.isRemote) {
				player.entityDropItem(getDropItem(), 1);
			}
		}
	}
	
	public void markForUpdate() {
		this.forcePlaceCounter = 10;
	}

	public int getSize() {
		return size;
	}

	public FrameComponent setSize(int size) {
		this.size = size;
		return this;
	}

	public void readFromNBT(NBTTagCompound tag) {
		this.setSize(tag.getInteger(this.getName().toUpperCase()+"_SIZE"));
	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger(this.getName().toUpperCase()+"_SIZE", (int) this.size);
	}
	
}
