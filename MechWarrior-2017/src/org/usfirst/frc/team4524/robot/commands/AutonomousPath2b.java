package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath2b extends CommandGroup {
	public AutonomousPath2b() {
		System.out.println("Path 2b");
		//Drive Forward ___ feet
				addSequential(new DriveForward(2));
				//Turn __ Degrees
				addSequential(new TurnHeading(90));
				//Drive forward ___ feet
				addSequential(new DriveForward(10));
	}
}
