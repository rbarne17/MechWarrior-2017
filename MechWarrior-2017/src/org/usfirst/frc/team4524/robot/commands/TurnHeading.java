package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4524.robot.Robot;

/**
 * This command turns the robot over a given angle with simple proportional
 * control This command will drive a given distance limiting to a maximum speed.
 */
public class TurnHeading extends Command {
	private double angle;
	private double tolerance;
	private double driveForwardSpeed;
	private double driveReverseSpeed;
	private double targetHeading;
	private PIDController angleMotorController;

	public TurnHeading(double ang) {
		this(ang, 5);
	}

	public TurnHeading(double angle, double tolerance) {
		this.angle = angle;
		this.tolerance = tolerance;
		requires(Robot.driveTrain);
	}

	@Override
	protected void initialize() {
		Robot.driveTrain.reset();

		PIDSource angleSource = new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public double pidGet() {
				return Robot.driveTrain.getHeading();
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};

		PIDOutput angleOutput = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				Robot.driveTrain.rotate(output);
			}
		};

		final double Kp = 0.3;
		final double Ki = 0.01;
		final double Kd = 0.05;

		angleMotorController = new PIDController(Kp, Ki, Kd, angleSource, angleOutput);

		angleMotorController.setAbsoluteTolerance(tolerance);

		final double MIN_SPEED = 0.5;
		final double MAX_SPEED = 1.0;

		if (angle > 0) {
			angleMotorController.setOutputRange(MIN_SPEED, MAX_SPEED);
		} else {
			angleMotorController.setOutputRange(-MIN_SPEED, -MAX_SPEED);
		}

		angleMotorController.setSetpoint(angle);
		angleMotorController.enable();

	}

	@Override
	protected void execute() {
    	SmartDashboard.putNumber("Angle", Robot.driveTrain.getHeading());
	}

	@Override
	protected boolean isFinished() {
        return angleMotorController.onTarget();
	}

	@Override
	protected void end() {
    	angleMotorController.disable();
		Robot.driveTrain.stop();
	}
}
