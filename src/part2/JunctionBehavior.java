package part2;

import java.util.Random;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class JunctionBehavior implements Behavior {
	
	private final DifferentialPilot pilot;
	private final LightSensor sensorL;
	private final LightSensor sensorR;
	
	public JunctionBehavior(DifferentialPilot pilot, LightSensor sensorL, LightSensor sensorR) {
		this.pilot= pilot;
		this.sensorL = sensorL;
		this.sensorR = sensorR;
		
		sensorL.setHigh(440);
		sensorL.setLow(350);
		sensorR.setHigh(415);
		sensorR.setLow(350);
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
