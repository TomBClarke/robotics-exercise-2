package part2;

import lejos.nxt.Button;

/**
 * 
 * Allows the robot to turn at junctions of lines.
 * 
 * @author Thomas Clarke, Rowan Cole and Kyle Allen-Taylor
 *
 */
public class gridNavigator {

	/**
	 * Allows the robot to turn  when it hits a junction between lines.
	 */
	public gridNavigator(){
		//TODO do this method
	}
	
	public static void main(String[] args) {
		System.out.println("Press any button to begin.");
		Button.waitForAnyPress();
		new gridNavigator();
	}

}

/*
Ultrasonic sensor = SensorPort.S1
Left light sensor = SensorPort.S2
Right light sensor = SensorPort.S3
*/