package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4524.robot.commands.PrintPath;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPathDefault extends CommandGroup {
	public AutonomousPathDefault() {
		addSequential(new PrintPath("Path Default"));
		addSequential(new DriveForward(10));
	}
}
