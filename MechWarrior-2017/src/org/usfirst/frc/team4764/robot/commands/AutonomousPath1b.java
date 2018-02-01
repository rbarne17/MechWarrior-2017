package org.usfirst.frc.team4764.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath1b extends CommandGroup {
	public AutonomousPath1b() {
		addSequential(new PrintPath("Path 1b"));
		addSequential(new DriveForward(15));
		//addSequential(new TurnHeading(180,.6,"left"));
		//addSequential(new DriveForward(3));
	}
}
