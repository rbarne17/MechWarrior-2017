// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team4524.robot.subsystems;

import org.usfirst.frc.team4524.robot.Robot;

import org.usfirst.frc.team4524.robot.RobotMap;
import org.usfirst.frc.team4524.robot.commands.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private SpeedController frontLeftMotor = new Talon(RobotMap.frontLeftMotor);
	private SpeedController rearLeftMotor = new Talon(RobotMap.rearLeftMotor);
	private SpeedController frontRightMotor = new Talon(RobotMap.frontRightMotor);
	private SpeedController rearRightMotor = new Talon(RobotMap.rearRightMotor);
	private RobotDrive drive = new RobotDrive(frontLeftMotor, frontRightMotor);
	private Gyro gyro;
	private Encoder leftEncoder = new Encoder(RobotMap.leftEncoderChannel1, RobotMap.leftEncoderChannel2, true,
			EncodingType.k4X);
	private Encoder rightEncoder = new Encoder(RobotMap.rightEncoderChannel1, RobotMap.rightEncoderChannel2, true,
			EncodingType.k4X);
	private AnalogInput rangefinder = new AnalogInput(RobotMap.rangefinder);

	public static final double WHEEL_DIAMETER = 6;
	public static final double PULSE_PER_REVOLUTION = 360;
	public static final double ENCODER_GEAR_RATIO = 1;
	public static final double GEAR_RATIO = 10.71 / 1;
	public static final double FUDGE_FACTOR = 1.0;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public DriveTrain() {
		super();
		final double distancePerPulse = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION / ENCODER_GEAR_RATIO
				/ GEAR_RATIO * FUDGE_FACTOR;
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		System.out.println("Distance per pulse: " + distancePerPulse);
		if (!Robot.isReal()) {
			gyro = new AnalogGyro(2);
		} else {
			gyro = new ADXRS450_Gyro();
		}

	}

	@Override
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoystick());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 * 
	 * @param left
	 *            Speed in range [-1,1]
	 * @param right
	 *            Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}

	/**
	 * @param joy
	 *            The ps3 style joystick to use to drive tank style.
	 */
	public void drive(Joystick joy) {
		drive.arcadeDrive(joy);
	}

	/**
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return gyro.getAngle();
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}

	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	/**
	 * @return The distance to the obstacle detected by the rangefinder.
	 */
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder...
		return rangefinder.getAverageVoltage();
	}

	public void stop() {
		drive.tankDrive(0, 0);
	}

	public void invertDrive(boolean reverse) {
		if (Robot.isReal()) {
			frontLeftMotor.setInverted(reverse);
			frontRightMotor.setInverted(reverse);
			rearLeftMotor.setInverted(reverse);
			rearRightMotor.setInverted(reverse);
		} else {
			drive.setInvertedMotor(MotorType.kFrontLeft, reverse);
			drive.setInvertedMotor(MotorType.kFrontRight, reverse);
			drive.setInvertedMotor(MotorType.kRearLeft, reverse);
			drive.setInvertedMotor(MotorType.kRearRight, reverse);
		}
	}

}
