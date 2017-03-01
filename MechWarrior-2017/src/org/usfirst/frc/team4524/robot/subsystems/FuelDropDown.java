package org.usfirst.frc.team4524.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4524.robot.Robot;


public class FuelDropDown extends Command {

	private boolean Down;

	public FuelDropDown() {
		requires(Robot.driveTrain);
		Down = true;

	}

	public FuelDropDown(boolean Down) {
		requires(Robot.driveTrain);
		this.Down = Down;
	}

	@Override
	protected void initialize() {
		
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
