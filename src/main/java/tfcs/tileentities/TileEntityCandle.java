package tfcs.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import tfcs.core.TFCSBlocks;
import tfcs.reference.Reference;

public class TileEntityCandle extends TileEntityTFCS {

	private int burnTime = Reference.MAX_CANDLE_BRUN_TIME; 

	@Override
	public void updateEntity() {
		if(burnTime<=0){
			this.worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}else{
			if(isBlockON()){
				this.worldObj.spawnParticle("smoke", this.xCoord + 0.5, this.yCoord + getRenderHight(), this.zCoord + 0.5, 0, 0.1, 0);
				this.worldObj.spawnParticle("flame", this.xCoord + 0.5, this.yCoord + getRenderHight(), this.zCoord + 0.5, 0, 0.1, 0);
				burnTime--;
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.burnTime = tag.getInteger("BURN_TIME");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("BURN_TIME", this.burnTime);
	}
	
	private boolean isBlockON() {
		return this.worldObj.getBlock(xCoord, yCoord, zCoord).equals(TFCSBlocks.candleON);
	}
	
	public int getMaxBrunTime() {
		return Reference.MAX_CANDLE_BRUN_TIME;
	}
	
	public int getBurnTime() {
		return burnTime;
	}

	public void setBurnTime(int burnTime) {
		this.burnTime = burnTime;
	}
	
	public double getRenderHight() {
		return 1.0-((((double)burnTime)/((double)Reference.MAX_CANDLE_BRUN_TIME))*0.8);
	}

	public void lightCandle() {
		if(!isBlockON()){
			this.getWorldObj().setBlock(xCoord, yCoord, zCoord, TFCSBlocks.candleON);
			TileEntity te = this.worldObj.getTileEntity(xCoord, yCoord, zCoord);
			if(te != null && te instanceof TileEntityCandle)
				((TileEntityCandle)te).setBurnTime(this.getBurnTime());
		}
	}
	
	public void snuffCandle(){
		if (isBlockON()) {
			this.getWorldObj().setBlock(xCoord, yCoord, zCoord, TFCSBlocks.candleOFF);
			TileEntity te = this.worldObj.getTileEntity(xCoord, yCoord, zCoord);
			if(te != null && te instanceof TileEntityCandle)
				((TileEntityCandle)te).setBurnTime(this.getBurnTime());
		}
	}
	
}
