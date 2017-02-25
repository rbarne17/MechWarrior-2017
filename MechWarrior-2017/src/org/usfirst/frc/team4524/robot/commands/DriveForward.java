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
	private Timer timer = new Timer();
	private double error;
	private final double kTolerance = 0.1;
	private final double motorkP = -1.0 / 5.0;
	private final double gyrokP = .03;

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
		System.out.println(Math.abs(Robot.driveTrain.getDistance()));
		if (Robot.robotChoice == "goodrobot") {
			error = (distance - Robot.driveTrain.getDistance());
			if (driveForwardSpeed * motorkP * error >= driveForwardSpeed) {
				Robot.driveTrain.drive(driveForwardSpeed, driveForwardSpeed, "tankDrive");
			} else {
				Robot.driveTrain.drive(driveForwardSpeed * motorkP * error, driveForwardSpeed * motorkP * error,
						"tankDrive");
			}
		} else {
			error = (distance - Robot.driveTrain.getDistance());
			double angle = Robot.driveTrain.getHeading(); // get current heading
			if (driveForwardSpeed * motorkP * error >= driveForwardSpeed) {
				Robot.driveTrain.drive(driveForwardSpeed, -angle * gyrokP, "arcadeDrive");
			} else {
				Robot.driveTrain.drive(driveForwardSpeed * motorkP * error, -angle * gyrokP, "arcadeDrive");
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs((Robot.driveTrain.getDistance())) >= distance);
	}

	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}
}
