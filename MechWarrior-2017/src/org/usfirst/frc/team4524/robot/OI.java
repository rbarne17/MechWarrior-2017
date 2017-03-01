package org.usfirst.frc.team4524.robot;

import org.usfirst.frc.team4524.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick1 = new Joystick(0);
	Joystick stick2 = new Joystick(1);

	public Joystick getDriverJoystick1() {
		return stick1;
	}

	public Joystick getDriver2Joystick() {
		return stick2;
	}

	Button button1 = new JoystickButton(stick1, 1), button2 = new JoystickButton(stick1, 2),
			button3 = new JoystickButton(stick1, 3), button4 = new JoystickButton(stick1, 4),
			button5 = new JoystickButton(stick1, 5), button6 = new JoystickButton(stick1, 6),
			button7 = new JoystickButton(stick1, 7), button8 = new JoystickButton(stick1, 8),
			button9 = new JoystickButton(stick1, 9), button10 = new JoystickButton(stick1, 10),
			button11 = new JoystickButton(stick1, 11), button12 = new JoystickButton(stick1, 12);
	//Joystick2(PS3 controller) buttons
	Button buttonA = new JoystickButton(stick2, 1), buttonB = new JoystickButton(stick2, 2),
			buttonX = new JoystickButton(stick2, 3), buttonY = new JoystickButton(stick2, 4),
			buttonLB = new JoystickButton(stick2,5), buttonRB = new JoystickButton(stick2, 6),
			buttonBack = new JoystickButton(stick2, 7), buttonStart = new JoystickButton(stick2, 8);

	public OI() {
		button7.toggleWhenPressed(new DriveWithJoystick(false));
		SmartDashboard.putData("Run Path 1a", new AutonomousPath1a());
		SmartDashboard.putData("Drive Forward", new DriveForward(2));
		SmartDashboard.putData("Turn 45 Degrees", new TurnHeading(45));
	}
}