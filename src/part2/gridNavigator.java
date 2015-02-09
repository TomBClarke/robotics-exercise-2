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
public class gridNavigator {

	/**
	 * Allows the robot to turn  when it hits a junction between lines.
	 */
	public gridNavigator(){
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.B);
		LightSensor sensorL = new LightSensor(SensorPort.S2, true);
		LightSensor sensorR = new LightSensor(SensorPort.S3, true);
		Arbitrator arby = new Arbitrator(new Behavior[] {new lineFollower(pilot, sensorL,sensorR)});
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new gridNavigator();
	}
}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/