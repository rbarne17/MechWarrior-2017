package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4524.robot.Robot;

/**
 * This command turns the robot over a given angle with simple proportional
 * control This command will drive a given distance limiting to a maximum speed.
 */
public class TurnHeading extends Command {
	private double angle;
	private double driveForwardSpeed;
	private double driveReverseSpeed;
	private double targetHeading;
	private double error;
	private final double kTolerance = 0.1;
	private final double kP = -1.0 / 5.0;

	public TurnHeading() {
		this(90, 0.5);
	}

	public TurnHeading(double ang) {
		this(ang, 0.5);
	}

	public TurnHeading(double ang, double maxSpeed) {
		requires(Robot.driveTrain);
		angle = ang;
		driveForwardSpeed = maxSpeed;
		driveReverseSpeed = -maxSpeed;
	}

	@Override
	protected void initialize() {
		// Robot.driveTrain.getRightEncoder().reset();
		// setTimeout(2);
	}

	@Override
	protected void execute() {
		Robot.driveTrain.drive(0.0, 0.5);
	}

	@Override
	protected boolean isFinished() {
		targetHeading = Robot.driveTrain.getHeading() + angle;
		return Robot.driveTrain.getHeading() < targetHeading || isTimedOut();
	}

	@Override
	protected void end() {
		// Robot.drivetrain.stop();
	}
}
