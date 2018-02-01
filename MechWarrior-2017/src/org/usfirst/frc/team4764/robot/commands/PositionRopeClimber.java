package org.usfirst.frc.team4764.robot.commands;

import org.usfirst.frc.team4764.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PositionRopeClimber extends Command {

	public PositionRopeClimber() {
		requires(Robot.ropeClimber);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.ropeClimber.climbUp();
		System.out.println(Robot.ropeClimber.getVoltage());
	}

	@Override
	protected boolean isFinished() {
		return (Robot.ropeClimber.getVoltage() == 2.5);
	}

	protected void end() {
		Robot.ropeClimber.stop();
	}
}
