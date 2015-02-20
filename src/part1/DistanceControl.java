package part1;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * 
 * Keeps the robot a safe distance/speed from an obstacle in front of it.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class DistanceControl {
	
	/**
	 * Allows the robot to keep its distances from robotics.
	 */
	public DistanceControl(){
		
		UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S4);
		
		DifferentialPilot pilot = new DifferentialPilot(88.0, 162, Motor.C, Motor.B);
		
		pilot.forward();
		double setDistance = 20;
		double bandWidth = 5;
		double targetDistance;
		double currentSpeed = 0;
		double k = 0.1;
		double measuredDistance;
		double error;
		
		while(true){
			measuredDistance = sonar.getDistance();
			if(currentSpeed>0){
				targetDistance = setDistance + bandWidth;
			} else {
				targetDistance = setDistance - bandWidth;
			}
			error = measuredDistance-targetDistance;
			
			currentSpeed += k * error;
			
			if(currentSpeed > 300){
				currentSpeed = 300;
			}
			else if(currentSpeed < -300){
				currentSpeed = -300;
			}
			
			if(currentSpeed < 0){
				pilot.backward();
			}
			else {
				pilot.forward();
			}
			pilot.setTravelSpeed(currentSpeed);
		}
	}

	public static void main(String[] args) {
		System.out.println("Distance controller ready. Press any button to begin.");
		Button.waitForAnyPress();
		new DistanceControl();
	}
}
