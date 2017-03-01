package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4524.robot.Robot;


public class FuelDropDown extends Command {

	private boolean Down;

	public FuelDropDown() {
		requires(Robot.fuelPickup);
		Down = true;

	}

	public FuelDropDown(boolean Down) {
		requires(Robot.fuelPickup);
		this.Down = Down;
	}

	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.fuelPickup.down();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
		 // wait until 0v then stop
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.fuelPickup.stop();
	}
}
