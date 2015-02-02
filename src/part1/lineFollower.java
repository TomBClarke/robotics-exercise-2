package part1;

import lejos.nxt.Button;

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
		//TODO do this method
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