package part2;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class GridLineFollower implements Behavior {

	private final DifferentialPilot pilot;
	private final LightSensor sensorL;
	private final LightSensor sensorR;
	private boolean suppressed;

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
		suppressed = false;
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
