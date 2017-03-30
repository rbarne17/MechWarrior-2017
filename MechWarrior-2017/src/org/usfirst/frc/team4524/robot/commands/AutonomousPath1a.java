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
		addSequential(new DriveForward(55));
    	addSequential(new TurnHeading(30,.7,"right"));
		addSequential(new DriveForward(72));
	}
}
