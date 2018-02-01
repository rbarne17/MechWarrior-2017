package org.usfirst.frc.team4764.robot.commands;
import org.usfirst.frc.team4764.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RopeClimbUp extends Command {
	
	private boolean stop;
	private double speed;
	public RopeClimbUp() {
		requires(Robot.ropeClimber);
		stop = false;

	}
	public RopeClimbUp (double speed){
		requires(Robot.ropeClimber);
		this.speed = speed;
	}

	public void stop() {
		this.stop = true;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		Robot.ropeClimber.climbUp(speed);
	}

	@Override
	protected boolean isFinished() {
		return (this.stop);
	}

	protected void end() {
		Robot.ropeClimber.stop();
	}
}
