package tfcs.core;

public class RotationPacket {
	private double speed;
	private double torque;
	public RotationPacket(double speed,double torque) {
		this.speed = speed;
		this.torque = torque;
	}
	
	public double getSpeed() {
		
		return speed;
	}
	
	public double getUnpacketSpeed(boolean b){
		return speed;
	}
	
	public void reverseSpeed(){
		this.speed = -speed;
	}
	
	public double getTorque() {
		return torque;
	}
	
	public void addSpeed(int i){
		this.speed += i;
	}
	
	public void removeTorque(int i){
		this.torque -= i;
	}
	
	public void splitTorque(int i){
		this.torque /= i;
	}
	
	public void scaleRotation(double gear1, double gear2) {
		this.speed = (this.speed*gear2)/gear1;
		this.torque = (this.torque*gear1)/gear2;
	}
	
}
