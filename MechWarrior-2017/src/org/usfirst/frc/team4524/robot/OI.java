package org.usfirst.frc.team4524.robot;

import org.usfirst.frc.team4524.robot.commands.Reverse;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick Driver = new Joystick(0);
	Joystick Driver2 = new Joystick(1);
		
	public Joystick getDriverJoystick1(){
		return Driver;
	}
	
	public Joystick getDriver2Joystick(){
		return Driver2;
	}	
	
	Joystick stick = new Joystick(0);
	Button button1 = new JoystickButton(stick,1),
					button2 = new JoystickButton(stick,2),
					button3 = new JoystickButton(stick,3),
					button4 = new JoystickButton(stick,4),
					button5 = new JoystickButton(stick,5),
					button6 = new JoystickButton(stick,6),
					button7 = new JoystickButton(stick,7),
					button8 = new JoystickButton(stick,8),
					button9 = new JoystickButton(stick,9),
					button10 = new JoystickButton(stick,10),
					button11 = new JoystickButton(stick,11),
					button12 = new JoystickButton(stick,12);
public OI(){
		button7.toggleWhenPressed(new Reverse());
	}		
	
}