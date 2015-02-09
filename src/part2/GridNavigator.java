package part2;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * 
 * Allows the robot to turn at junctions of lines.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class GridNavigator {

	/**
	 * Allows the robot to turn  when it hits a junction between lines.
	 */
	public GridNavigator(){
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.B);
		LightSensor sensorL = new LightSensor(SensorPort.S2, true);
		LightSensor sensorR = new LightSensor(SensorPort.S3, true);
		pilot.setTravelSpeed(50);
		pilot.setRotateSpeed(50);
		Arbitrator arby = new Arbitrator(new Behavior[] {new GridLineFollower(pilot, sensorL, sensorR), 
														new JunctionBehavior(pilot, sensorL, sensorR)});
		arby.start();
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new GridNavigator();
	}
}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/