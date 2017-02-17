package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4524.robot.Robot;

/**
 * This command drives the robot over a given distance with simple proportional
 * control This command will drive a given distance limiting to a maximum speed.
 */
public class DriveForward extends Command {
	private double driveForwardSpeed;
	private double distance;
	private final double fps = 9.3888; //full speed
	private Timer timer = new Timer();
	private double error;
	private final double kTolerance = 0.1;
	private final double kP = -1.0 / 5.0;

	public DriveForward() {
		this(10, 0.5);
	}

	public DriveForward(double dist) {
		this(dist, -0.5);
	}

	public DriveForward(double dist, double maxSpeed) {
		requires(Robot.driveTrain);
		distance = dist;
		driveForwardSpeed = maxSpeed;
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.reset();
		timer.reset();
		timer.start();
		setTimeout(2.0);
	}

	@Override
	protected void execute() {
		System.out.println(Math.abs(((timer.get() / fps) * driveForwardSpeed)));
		Robot.driveTrain.drive(driveForwardSpeed,driveForwardSpeed);
//		error = (distance - Robot.driveTrain.getDistance());
//		if (driveForwardSpeed * kP * error >= driveForwardSpeed) {
//			Robot.driveTrain.drive(driveForwardSpeed, driveForwardSpeed);
//		} else {
//			Robot.driveTrain.drive(driveForwardSpeed * kP * error, driveForwardSpeed * kP * error);
//		}
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(((timer.get() * fps) / driveForwardSpeed)) >= distance);
	}

	@Override
	protected void end() {
		Robot.driveTrain.drive(0.0,0.0);
	}
}
