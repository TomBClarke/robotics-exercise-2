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
	public lineFollower(){
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.B);
		LightSensor sensorL = new LightSensor(SensorPort.S2, false);
		LightSensor sensorR = new LightSensor(SensorPort.S3, false);
		while(true){
			System.out.println(sensorL.getNormalizedLightValue());
			Delay.msDelay(500);
		}
		
		/*
		pilot.forward();
		while(true){
			int left = sensorL.getNormalizedLightValue();
			int right = sensorR.getNormalizedLightValue();
			if(leftSide){
				pilot.arc(10, -10);//Can we use feedback control here?
				leftSide = false;
				pilot.forward();
			}
			if(rightSide){
				pilot.arc(10, -10);
				rightSide = false;
				pilot.forward();
			}
		}
		*/
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new lineFollower();
	}
}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/