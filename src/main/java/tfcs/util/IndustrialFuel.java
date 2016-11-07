package tfcs.util;

public class IndustrialFuel {

	private float burnTime;
	private float burnTemperature;

	public IndustrialFuel(float burnTime, float burnTemperature) {
		this.burnTime = burnTime;
		this.setBurnTemperature(burnTemperature);
	}

	public float getBurnTime() {
		return burnTime;
	}
	
	public void setBurnTime(float burnTime) {
		this.burnTime = burnTime;
	}

	public float getBurnTemperature() {
		return burnTemperature;
	}

	public void setBurnTemperature(float burnTemperature) {
		this.burnTemperature = burnTemperature;
	}
	
}
