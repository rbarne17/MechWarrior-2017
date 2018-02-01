package org.usfirst.frc.team4764.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath1c extends CommandGroup {
	public AutonomousPath1c() {
		addSequential(new PrintPath("Path 1c"));
		addSequential(new DriveForward(15.3385416));
		addSequential(new TurnHeading(60,.7,"right"));
		addSequential(new DriveForward(4));
	}
}
