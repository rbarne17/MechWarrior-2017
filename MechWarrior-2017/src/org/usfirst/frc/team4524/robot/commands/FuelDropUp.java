package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4524.robot.Robot;


public class FuelDropUp extends Command {

	private boolean Up;

	public FuelDropUp() {
		requires(Robot.fuelPickup);
		Up = true;

	}

	public FuelDropUp(boolean Up) {
		requires(Robot.fuelPickup);
		this.Up = Up;
	}

	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.fuelPickup.up();
		System.out.println(Robot.fuelPickup.getVoltage());

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (Robot.fuelPickup.getVoltage()>= 2.0);
		 // wait until 0v then stop
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.fuelPickup.stop();
	}
}