package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4524.robot.commands.PrintPath;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath1a extends CommandGroup {
	public AutonomousPath1a() {
		addSequential(new PrintPath("Path 1a"));
		addSequential(new DriveForward(10));

		//addSequential(new TurnHeading(45,.6,"left"));
		addSequential(new DriveForward(10));
	}
}
