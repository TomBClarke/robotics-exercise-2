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
public class LineFollower {
	
	/**
	 * Allows the robot to follow a line using two light sensors.
	 */
	public LineFollower(){

		//Used for calibration purposes:
		/*
		while(true){
			System.out.println("L = " + sensorL.getNormalizedLightValue() + ", R = " + sensorR.getNormalizedLightValue());
			Delay.msDelay(1000);
		}
		*/
		
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.B);
		LightSensor sensorL = new LightSensor(SensorPort.S2, true);
		LightSensor sensorR = new LightSensor(SensorPort.S3, true);
		
		sensorL.setHigh(440);
		sensorL.setLow(350);
		sensorR.setHigh(415);
		sensorR.setLow(350);
		
			
		pilot.setTravelSpeed(100);
		pilot.forward();
		while(true){
			checkLine(pilot, sensorL, sensorR);
		}
	}
	
	/**
	 * Changes the motion of the robot to follow a line.
	 * 
	 * @param pilot The pilot controlling the robot.
	 * @param sensorL The left light sensor.
	 * @param sensorR The right light sensor.
	 */
	public static void checkLine(DifferentialPilot pilot, LightSensor sensorL, LightSensor sensorR){
		int turnTime = 50;
		System.out.println("L = " + sensorL.getLightValue() + ", R = " + sensorR.getLightValue());
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
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new LineFollower();
	}
}
