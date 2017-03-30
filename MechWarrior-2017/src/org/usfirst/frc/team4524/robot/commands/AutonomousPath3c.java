package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath3c extends CommandGroup {
	public AutonomousPath3c() {
		System.out.println("Path 3c");
		addSequential(new PrintPath("Path 3c"));
		addSequential(new DriveForward(15.3385416));
		addSequential(new TurnHeading(60,1));
		addSequential(new DriveForward(4));
	}
}
