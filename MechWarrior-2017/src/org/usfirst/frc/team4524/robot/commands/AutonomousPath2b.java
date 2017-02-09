package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath2b extends CommandGroup {
	public AutonomousPath2b() {
		System.out.println("Path 2b");
		addSequential(new PrintPath("Path 2b"));
		addSequential(new DriveForward(2));
		addSequential(new TurnHeading(90));
		addSequential(new DriveForward(10));

	}
}
