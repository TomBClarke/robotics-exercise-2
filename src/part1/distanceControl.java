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
public class distanceControl {
	
	/**
	 * Allows the robot to keep its distances from robotics.
	 */
	public distanceControl(){
		
		//Creates a ultrasonic sensor to find distance.
		UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
		
		//Creates a pilot to control the robot with.
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.A);
		
		pilot.forward();
		double stopDistance = 10;
		
		while(true){
			double measuredDistance = sonar.getDistance();
			double speed = measuredDistance - stopDistance;				
			pilot.setTravelSpeed(speed);
			}
	}

	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new distanceControl();
	}

}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/