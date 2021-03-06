package org.usfirst.frc.team4764.robot.commands;

import org.usfirst.frc.team4764.robot.commands.PrintPath;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPathDefault extends CommandGroup {
	public AutonomousPathDefault() {
		addSequential(new PrintPath("Path Default"));
		addSequential(new DriveForward(180));
	}
}
