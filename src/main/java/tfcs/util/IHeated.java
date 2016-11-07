package tfcs.util;

public interface IHeated {
	float getHeat();
	void addHeat(float temperature);
	float getThermalConductivity();
	float getHeatCapacity();
	float getNonAnbiantTemperature();
}
