package org.usfirst.frc.team4764.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintPath extends Command {
	private String autonomousPath;
	private int printCount = 0;

	public PrintPath() {
		// requires(Robot.myRobot);

	}

	public PrintPath(String autonomousPath) {
		this.autonomousPath = autonomousPath;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println(autonomousPath);
		printCount = printCount + 1;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (printCount >= 1);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
