package org.usfirst.frc.team4524.robot.commands;

import org.usfirst.frc.team4524.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Reverse extends Command {

	public Reverse() {
		requires(Robot.driveTrain);
<<<<<<< HEAD
=======
	}>>>>>>>refs/remotes/origin/Solomon

	<<<<<<<HEAD

	}

	=======>>>>>>>refs/remotes/origin/Solomon

	@Override
	protected void initialize() {
		Robot.driveTrain.invertDrive(true);
	}

	@Override
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {

		Robot.driveTrain.invertDrive(false);
	}

}
