package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4524.robot.Robot;

/**
 * This command drives the robot over a given distance with simple proportional
 * control This command will drive a given distance limiting to a maximum speed.
 */
public class DriveForward extends Command {
	private double driveForwardSpeed;
	private double distance;

	private PIDController leftMotorController;
	private PIDController rightMotorController;

	private double tolerance;
	private double error;
	private final double motorkP = -1.0 / 5.0;
	private final double gyrokP = .15;

	public DriveForward() {
		this(5);
	}

	public DriveForward(double dist) {
		this(dist, .1);
	}

	public DriveForward(double distance, double tolerance) {
		this.distance = distance;
		this.tolerance = tolerance;
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
		System.out.println(Robot.driveTrain.getHeading());
		Robot.driveTrain.reset();
		PIDSource leftMotorSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public double pidGet() {
				return Robot.driveTrain.getLeftDistance();
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};

		PIDSource rightMotorSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public double pidGet() {
				return Robot.driveTrain.getRightDistance();
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};

		PIDOutput leftMotorOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				Robot.driveTrain.setLeftMotors(output);
			}
		};

		PIDOutput rightMotorOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				Robot.driveTrain.setRightMotors(output);
			}
		};
		final double Kp = 0.5;
		final double Ki = 0.0;
		final double Kd = 0.001;

		leftMotorController = new PIDController(Kp, Ki, Kd, leftMotorSource, leftMotorOutput);
		rightMotorController = new PIDController(Kp, Ki, Kd, rightMotorSource, rightMotorOutput);

		leftMotorController.setAbsoluteTolerance(tolerance);
		rightMotorController.setAbsoluteTolerance(tolerance);

		final double MIN_SPEED = 0.1;
		final double MAX_SPEED = 0.5;

		if (distance > 0) {
			leftMotorController.setOutputRange(MIN_SPEED, MAX_SPEED);
			rightMotorController.setOutputRange(MIN_SPEED, MAX_SPEED);
		} else {
			leftMotorController.setOutputRange(-MIN_SPEED, -MAX_SPEED);
			rightMotorController.setOutputRange(-MIN_SPEED, -MAX_SPEED);
		}

		leftMotorController.setSetpoint(distance);
		rightMotorController.setSetpoint(distance);

		leftMotorController.enable();
		rightMotorController.enable();

	}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("Left Distance", Robot.driveTrain.getLeftDistance());
		SmartDashboard.putNumber("Right Distance", Robot.driveTrain.getRightDistance());
	}

	@Override
	protected boolean isFinished() {
		return leftMotorController.onTarget() && rightMotorController.onTarget();
	}

	@Override
	protected void end() {
		System.out.println(Robot.driveTrain.getHeading());
		leftMotorController.disable();
		rightMotorController.disable();
		Robot.driveTrain.stop();
	}

	protected void interrupted() {
		end();
	}
}
