package part3;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.NXTCam;

/**
 * 
 * Follows a brightly coloured object.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class BrightColourFollower {

	/**
	 * Makes the robot travel towards a brightly coloured object.
	 */
	public BrightColourFollower(){
		NXTCam cam = new NXTCam(SensorPort.S1);
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new BrightColourFollower();
	}
}
