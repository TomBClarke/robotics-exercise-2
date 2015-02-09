package part2;

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

	private final DifferentialPilot pilot;
	private final LightSensor sensorL;
	private final LightSensor sensorR;
	private boolean suppressed;

	/**
	 * Allows the class access to the sensors and pilot.
	 * 
	 * @param pilot The pilot controlling the robot.
	 * @param sensorL The left light sensor.
	 * @param sensorR The right light sensor.
	 */
	public GridLineFollower(DifferentialPilot pilot, LightSensor sensorL, LightSensor sensorR) {
		this.pilot= pilot;
		this.sensorL = sensorL;
		this.sensorR = sensorR;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		/*
		int turnTime = 50;
		sensorL.setHigh(440);
		sensorL.setLow(350);
		sensorR.setHigh(415);
		sensorR.setLow(350);
		
		pilot.forward();
		while(!suppressed){
			if(sensorL.getLightValue() < 50){
				pilot.steer(200);
				Delay.msDelay(turnTime);
				pilot.forward();
			}
			if(sensorR.getLightValue() < 50){
				pilot.steer(-200);
				Delay.msDelay(turnTime);
				pilot.forward();
			}
			Thread.yield();
		}
		*/
		
		//Uses the same line following method from part 1.
		while(!suppressed){
			LineFollower.checkLine(pilot, sensorL, sensorR); //Happy?
		}
		suppressed = false;
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
