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
		
		//Creates a ultrasonic sensor to find distance.
		UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S4);
		
		//Creates a pilot to control the robot with.
		DifferentialPilot pilot = new DifferentialPilot(81.6, 160, Motor.C, Motor.B);
		
		pilot.forward();
		double setDistance = 20;
		double currentSpeed = 0;
		double k = 5;
		double measuredDistance;
		double error;
		
		while(true){
			measuredDistance = sonar.getDistance();
			error = measuredDistance - setDistance;
			//currentSpeed+k* 
			currentSpeed = k * error;
			
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
