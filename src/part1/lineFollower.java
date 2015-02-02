package part1;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * 
 * Follows a line.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class lineFollower {

	private static boolean leftSide;
	private static boolean rightSide;
	
	/**
	 * Allows the robot to follow a line using two light sensors.
	 */
	public lineFollower(){
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.B);		
		SensorPort.S2.addSensorPortListener(new SensorPortListener(){
			@Override
			public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
				leftSide = true; //Need to look at values.
			}
		});		
		SensorPort.S3.addSensorPortListener(new SensorPortListener(){
			@Override
			public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
				rightSide = true; //Need to look at values.
			}
		});
		
		pilot.forward();
		while(true){
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