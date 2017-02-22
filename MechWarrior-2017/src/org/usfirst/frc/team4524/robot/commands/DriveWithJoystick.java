/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4524.robot.Robot;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class DriveWithJoystick extends Command {

	private boolean forward;

	public DriveWithJoystick() {
		requires(Robot.driveTrain);
		forward = true;

	}

	public DriveWithJoystick(boolean forward) {
		requires(Robot.driveTrain);
		this.forward = forward;
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.invertDrive(forward);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.driveTrain.drive(Robot.oi.getDriverJoystick1());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}
}
