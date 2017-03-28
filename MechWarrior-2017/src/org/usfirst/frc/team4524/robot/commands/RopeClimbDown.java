package org.usfirst.frc.team4524.robot.commands;

import org.usfirst.frc.team4524.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RopeClimbDown extends Command {

	private boolean stop;

	public RopeClimbDown() {
		requires(Robot.ropeClimber);
		stop = false;

	}

	public void stop() {
		this.stop = true;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {

		Robot.ropeClimber.climbDown();
	}

	@Override
	protected boolean isFinished() {
		return (this.stop);
	}

	protected void end() {
		Robot.ropeClimber.stop();
	}
}
