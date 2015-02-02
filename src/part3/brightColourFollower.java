package part3;

import lejos.nxt.Button;

/**
 * 
 * Follows a brightly coloured object.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class brightColourFollower {

	/**
	 * Makes the robot travel towards a brightly coloured object.
	 */
	public brightColourFollower(){
		//TODO do this method
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new brightColourFollower();
	}

}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/