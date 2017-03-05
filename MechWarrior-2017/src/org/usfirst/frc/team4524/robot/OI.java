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

	public Joystick getDriverJoystick2() {
		return stick2;
	}

	Button button1 = new JoystickButton(stick1, 1), button2 = new JoystickButton(stick1, 2),
			button3 = new JoystickButton(stick1, 3), button4 = new JoystickButton(stick1, 4),
			button5 = new JoystickButton(stick1, 5), button6 = new JoystickButton(stick1, 6),
			button7 = new JoystickButton(stick1, 7), button8 = new JoystickButton(stick1, 8),
			button9 = new JoystickButton(stick1, 9), button10 = new JoystickButton(stick1, 10),
			button11 = new JoystickButton(stick1, 11), button12 = new JoystickButton(stick1, 12);
	// Joystick2(PS3 controller) buttons
	Button buttonA = new JoystickButton(stick2, 1), buttonB = new JoystickButton(stick2, 2),
			buttonX = new JoystickButton(stick2, 3), buttonY = new JoystickButton(stick2, 4),
			buttonLB = new JoystickButton(stick2, 5), buttonRB = new JoystickButton(stick2, 6),
			buttonBack = new JoystickButton(stick2, 7), buttonStart = new JoystickButton(stick2, 8);

	public OI() {
		button7.toggleWhenPressed(new DriveWithJoystick(false));
		buttonA.toggleWhenPressed(new FuelDropUp());
		buttonB.toggleWhenPressed(new FuelDropDown());

		SmartDashboard.putData("Run Path Default", new AutonomousPathDefault());
		SmartDashboard.putData("Run Path 1a", new AutonomousPath1a());
		SmartDashboard.putData("Run Path 1b", new AutonomousPath1b());
		SmartDashboard.putData("Run Path 1c", new AutonomousPath1c());
		SmartDashboard.putData("Run Path 2a", new AutonomousPath2a());
		SmartDashboard.putData("Run Path 2b", new AutonomousPath2b());
		SmartDashboard.putData("Run Path 2c", new AutonomousPath2c());
		SmartDashboard.putData("Run Path 3a", new AutonomousPath3a());
		SmartDashboard.putData("Run Path 3b", new AutonomousPath3b());
		SmartDashboard.putData("Run Path 3c", new AutonomousPath3c());
		SmartDashboard.putData("Drive Forward, 2 feet", new DriveForward(2));
		SmartDashboard.putData("Drive Forward, 20 feet", new DriveForward(20));
		SmartDashboard.putData("Turn 45 Degrees, Right", new TurnHeading(45, .6, "right"));
		SmartDashboard.putData("Turn 45 Degrees, Left", new TurnHeading(45, .6, "left"));

	}
}