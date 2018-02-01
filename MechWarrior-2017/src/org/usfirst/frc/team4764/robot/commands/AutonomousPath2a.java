package org.usfirst.frc.team4764.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath2a extends CommandGroup {
	public AutonomousPath2a() {
		addSequential(new PrintPath("Path 2a"));
		addSequential(new DriveForward(2,.25));
		addSequential(new TurnHeading(180,.6,"right"));
		addSequential(new DriveForward(2));
	}
}
