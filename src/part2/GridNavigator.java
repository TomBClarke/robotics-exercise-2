package part2;

import java.util.LinkedList;
import java.util.Random;

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
	public GridNavigator(LinkedList<Integer> pathToTake){
		DifferentialPilot pilot = new DifferentialPilot(88.0, 162, Motor.C, Motor.B);
		LightSensor sensorL = new LightSensor(SensorPort.S2, true);
		LightSensor sensorR = new LightSensor(SensorPort.S3, true);
		Delay.msDelay(500);
		
		LineFollower.calibrate(sensorL, sensorR);
		
		int speed = 100;
		pilot.setTravelSpeed(speed);
		pilot.setRotateSpeed(speed);
		
		Arbitrator arby = new Arbitrator(new Behavior[] {new GridLineFollower(pilot, sensorL, sensorR, pathToTake), 
														new JunctionBehavior(pilot, sensorL, sensorR, pathToTake)});
		arby.start();
	}
	
	public static void main(String[] args) {
		System.out.println("Grid navigator ready. Press any button to begin.");
		Button.waitForAnyPress();
		
		Random rand = new Random();
		LinkedList<Integer> pathToTake = new LinkedList<Integer>();
		for(int i = 0; i < 50; i++){
			int randNum = rand.nextInt(3);
			pathToTake.add(randNum);
		}
		
		new GridNavigator(pathToTake);
	}
}
