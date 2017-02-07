package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintPath extends Command {
	private String autonomousPath;

	public PrintPath() {
		// requires(Robot.myRobot);

	}

	public PrintPath(String autonomousPath) {
		this.autonomousPath = autonomousPath;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println(autonomousPath);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
