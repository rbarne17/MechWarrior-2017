package org.usfirst.frc.team4764.robot.commands;

import org.usfirst.frc.team4764.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
	private String direction;

	public TurnHeading() {
		this(90, 0.5, "left");
	}

	public TurnHeading(double ang) {
		this(ang, 0.5, "left");
	}

	public TurnHeading(double ang, String dir) {
		this(ang, 0.5, dir);
	}

	public TurnHeading(double ang, double maxSpeed) {
		this(ang, maxSpeed, "left");
	}

	public TurnHeading(double ang, double maxSpeed, String dir) {
		requires(Robot.driveTrain);
		angle = ang;
		driveForwardSpeed = maxSpeed;
		driveReverseSpeed = -maxSpeed;
		direction = dir;
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.reset();
		targetHeading = Robot.driveTrain.getHeading() + angle;

	}

	@Override
	protected void execute() {
		if (direction == "left") {
			Robot.driveTrain.drive(driveReverseSpeed, driveForwardSpeed, "tankDrive");
		} else {
			Robot.driveTrain.drive(driveForwardSpeed, driveReverseSpeed, "tankDrive");
		}
	}

	@Override
	protected boolean isFinished() {
		double presentHeading = Robot.driveTrain.getHeading();
		// return Robot.driveTrain.getHeading() < targetHeading || isTimedOut();
		return Math.abs(presentHeading) > targetHeading;
	}

	@Override
	protected void end() {
		System.out.println("Angle at end of turn:" + Robot.driveTrain.getHeading());
		Robot.driveTrain.stop();
	}
}
