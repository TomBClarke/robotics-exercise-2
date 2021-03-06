package part2;

import java.util.LinkedList;

import lejos.nxt.LightSensor;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

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
	private LinkedList<Integer> pathToTake;
	
	/**
	 * Allows the class access to the sensors and pilot.
	 * 
	 * @param pilot The pilot controlling the robot.
	 * @param sensorL The left light sensor.
	 * @param sensorR The right light sensor.
	 */
	public JunctionBehavior(DifferentialPilot pilot, LightSensor sensorL, LightSensor sensorR, LinkedList<Integer> pathToTake) {
		this.pilot= pilot;
		this.sensorL = sensorL;
		this.sensorR = sensorR;
		this.pathToTake = pathToTake;
	}

	@Override
	public boolean takeControl() {
		 return (sensorL.getLightValue() < 50 && sensorR.getLightValue() < 50 && !pathToTake.isEmpty());
	}

	@Override
	public void action() {
		pilot.travel(100);
		Integer direction = pathToTake.get(0);
		pathToTake.remove(0);
		if(direction == 0){
			pilot.rotate(90);
			System.out.println("Left");
		}else if(direction == 2){
			pilot.rotate(-90);
			System.out.println("Right");
		} else {
			System.out.println("Forward");
		}
		
		if(pathToTake.isEmpty()){
			Sound.beep();
			Delay.msDelay(500);
		}
	}

	@Override
	public void suppress() {
		//Nothing to do here.
	}
}
