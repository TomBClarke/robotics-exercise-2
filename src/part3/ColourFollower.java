package part3;

import java.awt.Rectangle;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.NXTCam;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * 
 * Follows a brightly coloured object.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class ColourFollower {

	/**
	 * Makes the robot travel towards a brightly coloured object.
	 */
	public ColourFollower(){
		DifferentialPilot pilot = new DifferentialPilot(88.0, 162, Motor.C, Motor.B);
		NXTCam cam = new NXTCam(SensorPort.S1);
		double turnRate=0;


		cam.setTrackingMode(NXTCam.COLOR);
		cam.enableTracking(true);
		
		Delay.msDelay(1000);
		
		cam.sortBy(NXTCam.SIZE);
		pilot.forward();
		
		while(true){
			Rectangle ball = cam.getRectangle(0);
			double position = ball.getCenterX();
			turnRate=position-90;
			pilot.steer(-turnRate);
			
			
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Colour follower ready. Press any button to begin.");
		Button.waitForAnyPress();
		new ColourFollower();
	}
}
