package part1;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * 
 * Follows a line.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class lineFollower {
	
	/**
	 * Allows the robot to follow a line using two light sensors.
	 */
	public lineFollower(int crossOver){
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.B);
		
		LightSensor sensorL = new LightSensor(SensorPort.S2, true);
		LightSensor sensorR = new LightSensor(SensorPort.S3, true);
		
		int turnTime = 50;
		
		/*
		while(true){
			System.out.println("L = " + sensorL.getNormalizedLightValue() + ", R = " + sensorR.getNormalizedLightValue());
			Delay.msDelay(1000);
		}
		*/
		
		sensorL.setHigh(440);
		sensorL.setLow(350);
		sensorR.setHigh(415);
		sensorR.setLow(350);
		
		/*
		while(true){
			System.out.println("L = " + sensorL.getLightValue() + ", R = " + sensorR.getLightValue());
			Delay.msDelay(1000);
		}
		*/
		
		pilot.forward();
		pilot.setTravelSpeed(100);
		while(true){
			System.out.println("L = " + sensorL.getLightValue() + ", R = " + sensorR.getLightValue());
			boolean leftSide = sensorL.getLightValue() < 50;
			boolean rightSide = sensorR.getLightValue() < 50;
			if(leftSide){
				leftSide = false;
				//pilot.stop();
				pilot.steer(200);
				Delay.msDelay(turnTime);
				//pilot.stop();
				pilot.forward();
			}
			if(rightSide){
				rightSide = false;
				//pilot.stop();
				pilot.steer(-200);
				Delay.msDelay(turnTime);
				//pilot.stop();
				pilot.forward();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new lineFollower(340);
	}
}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/