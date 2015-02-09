package part2;

import java.util.Random;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

/**
 * Control the robot when it reaches a junction.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class JunctionBehavior implements Behavior {
	
	private final DifferentialPilot pilot;
	private final LightSensor sensorL;
	private final LightSensor sensorR;
	
	/**
	 * Allows the class access to the sensors and pilot.
	 * 
	 * @param pilot The pilot controlling the robot.
	 * @param sensorL The left light sensor.
	 * @param sensorR The right light sensor.
	 */
	public JunctionBehavior(DifferentialPilot pilot, LightSensor sensorL, LightSensor sensorR) {
		this.pilot= pilot;
		this.sensorL = sensorL;
		this.sensorR = sensorR;
	}

	@Override
	public boolean takeControl() {
		 return (sensorL.getLightValue() < 50 && sensorR.getLightValue() < 50);
	}

	@Override
	public void action() {
		Random rand = new Random();
		double randNum = rand.nextDouble();
		pilot.travel(90);
		if(randNum < 0.25){
			pilot.rotate(90);
		}else if(randNum < 0.5){
			pilot.rotate(-90);
		}else if(randNum < 0.75){
			pilot.rotate(180);
		}
	}

	@Override
	public void suppress() {
		//Nothing to do here.
	}
}
