package part2;

import part1.LineFollower;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

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
		DifferentialPilot pilot = new DifferentialPilot(81.6, 160, Motor.C, Motor.B);
		LightSensor sensorL = new LightSensor(SensorPort.S2, true);
		LightSensor sensorR = new LightSensor(SensorPort.S3, true);
		Delay.msDelay(500);
		
		LineFollower.calibrate(sensorL, sensorR);
		
		//sensorL.setHigh(440);
		//sensorL.setLow(350);
		//sensorR.setHigh(415);
		//sensorR.setLow(350);
		
		int speed = 80;
		pilot.setTravelSpeed(speed);
		pilot.setRotateSpeed(speed);
		
		Arbitrator arby = new Arbitrator(new Behavior[] {new GridLineFollower(pilot, sensorL, sensorR), 
														new JunctionBehavior(pilot, sensorL, sensorR)});
		arby.start();
	}
	
	public static void main(String[] args) {
		System.out.println("Grid navigator ready. Press any button to begin.");
		Button.waitForAnyPress();
		new GridNavigator();
	}
}
