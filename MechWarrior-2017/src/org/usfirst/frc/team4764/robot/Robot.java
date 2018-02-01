package org.usfirst.frc.team4764.robot;

import org.usfirst.frc.team4764.robot.commands.*;
import org.usfirst.frc.team4764.robot.subsystems.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static FuelPickupDropoff fuelPickup;
	public static RopeClimber ropeClimber;
	public static DriveTrain driveTrain;

	// what robot are we using?
	public static final String robotChoice = "goodrobot"; // pacgoat,gearbots,badrobot,goodrobot

	Timer timer = new Timer();
	double timerCount = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Official code for the MechWarrior 2017 FRC robot");

		RobotMap.init();

		//instantiate subsystems
		fuelPickup = new FuelPickupDropoff();
		ropeClimber = new RopeClimber();
		driveTrain = new DriveTrain();

		//instantiate OI (Operator Interface)
		oi = new OI();

		//reset the gyro on the main drive train
		Robot.driveTrain.gyroCalibrate();

		//send information to the SmartDashboard
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(fuelPickup);

		
	}
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
		//call various reset methods
		timer.reset();
		timer.start();
		Robot.driveTrain.reset();

		// instantiate the command used for the autonomous period
		oi.autonomousCommand = (Command) oi.autoPathChooser.getSelected();

		// schedule the autonomous command (example)
		if (oi.autonomousCommand != null)
			oi.autonomousCommand.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//get readings from sensors
		double encoderDistanceReading = Robot.driveTrain.getDistance();
		double voltage = Robot.fuelPickup.getVoltage();
		int encoderCount = Robot.driveTrain.getEncoderCount();
		double heading = Robot.driveTrain.getHeading();
		
		//send output data to SmartDashboard and System.Out
		SmartDashboard.putNumber("Encoder Distance", encoderDistanceReading);
		System.out.println("Encoder Distance: " + encoderDistanceReading);
		SmartDashboard.putNumber("Encoder Count", encoderCount);
		System.out.println("Encoder Count: " + encoderCount);
		SmartDashboard.putNumber("Heading", heading);
		System.out.println("Heading: " + heading);
		SmartDashboard.putNumber("Voltage", voltage);
		System.out.println("Voltage:" + voltage);

		//run the Scheduler
		Scheduler.getInstance().run();

	}

	@Override
	public void disabledPeriodic() {
		//run the Scheduler
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (oi.autonomousCommand != null) {
			oi.autonomousCommand.cancel();
		}
		
		//reset
		Robot.driveTrain.reset();

	}

	/**
	 * } This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//get readings from sensors
		double encoderDistanceReading = Robot.driveTrain.getDistance();
		double voltage = Robot.fuelPickup.getVoltage();
		int encoderCount = Robot.driveTrain.getEncoderCount();
		double heading = Robot.driveTrain.getHeading();
		
		//send output data to SmartDashboard and System.Out
		SmartDashboard.putNumber("Encoder Reading", encoderDistanceReading);
		System.out.println("Encoder Distance: " + encoderDistanceReading);
		SmartDashboard.putNumber("Encoder Count", encoderCount);
		System.out.println("Encoder Count: " + encoderCount);
		SmartDashboard.putNumber("Heading", heading);
		System.out.println("Heading: " + heading);
		SmartDashboard.putNumber("Voltage", voltage);
		System.out.println("Voltage:" + voltage);

		//run the Scheduler
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
