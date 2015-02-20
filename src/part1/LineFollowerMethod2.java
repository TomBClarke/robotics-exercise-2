package part1;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * 
 * Follows a line. An experinmental second way of doing it.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class LineFollowerMethod2 {
	
	/**
	 * Allows the robot to follow a line using two light sensors.
	 */
	public LineFollowerMethod2(){
		DifferentialPilot pilot = new DifferentialPilot(56, 182, Motor.C, Motor.B);
		
		LightSensor sensorL = new LightSensor(SensorPort.S2, true);
		LightSensor sensorR = new LightSensor(SensorPort.S3, true);
		
		calibrate(sensorL,sensorR);
		
		double turnAngle=0;
		pilot.forward();
		pilot.setTravelSpeed(300);
		int k = 10000;
		while(true){
			System.out.println("L = " + sensorL.getLightValue() + ", R = " + sensorR.getLightValue());
			int leftReading=sensorL.getLightValue();
			int rightReading=sensorR.getLightValue();
			turnAngle = rightReading-leftReading;

			System.out.println(turnAngle);
			if(turnAngle == 0){
				pilot.forward();
			}
			else {
				pilot.arcForward(k * (1 / turnAngle));
			}
				
			Delay.msDelay(1);
		}
	}
	
	public static void calibrate(LightSensor sL, LightSensor sR){
		int sumLl = 0;
		int sumRl = 0;
		int sumLd = 0;
		int sumRd = 0;
		
		System.out.println("Light surface calibration...");
		Button.waitForAnyPress();
		for(int i = 0; i < 5; i++){
			sumLl += sL.getNormalizedLightValue();
			sumRl += sR.getNormalizedLightValue();
		}
		
		System.out.println("Dark surface calibration...");
		Button.waitForAnyPress();
		for(int i = 0; i < 5; i++){
			sumLd += sL.getNormalizedLightValue();
			sumRd += sR.getNormalizedLightValue();
		}
		
		sL.setHigh(sumLl / 5);
		sR.setHigh(sumRl / 5);
		sL.setLow(sumLd / 5);
		sR.setLow(sumRd / 5);
		
		System.out.println("Light values: Left = " + (sumLl / 5) + " Right = " + (sumRl / 5));
		System.out.println("Dark values: Left = " + (sumLd/ 5) + " Right = " + (sumRd / 5));
		System.out.println("Done!");
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new LineFollowerMethod2();
	}
}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/