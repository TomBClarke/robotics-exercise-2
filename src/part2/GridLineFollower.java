package part2;

import java.util.LinkedList;

import part1.LineFollower;
import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

/**
 * Allows the robot to follow a line when ot at a junction.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class GridLineFollower implements Behavior {

	private boolean suppressed;
	private final LineFollower lf;
	private LinkedList<Integer> pathToTake;

	/**
	 * Allows the class access to the sensors and pilot.
	 * 
	 * @param pilot The pilot controlling the robot.
	 * @param sensorL The left light sensor.
	 * @param sensorR The right light sensor.
	 */
	public GridLineFollower(DifferentialPilot pilot, LightSensor sensorL, LightSensor sensorR, LinkedList<Integer> pathToTake) {
		this.lf = new LineFollower(pilot, sensorL, sensorR);
		suppressed = false;
		this.pathToTake = pathToTake;
	}

	@Override
	public boolean takeControl() {
		return !pathToTake.isEmpty();
	}

	@Override
	public void action() {		
		//Uses the same line following method from part 1.
		while(!suppressed){
			lf.checkLine();
		}
		suppressed = false;
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
