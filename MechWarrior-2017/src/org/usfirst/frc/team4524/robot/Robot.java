package org.usfirst.frc.team4524.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.buttons.Button;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String path1a = "Path 1a";
	final String path1b = "Path 1b";
	final String path1c = "Path 1c";
	final String path2a = "Path 2a";
	final String path2b = "Path 2b";
	final String path2c = "Path 2c";
	final String path3a = "Path 3a";
	final String path3b = "Path 3b";
	final String path3c = "Path 3c";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick stick = new Joystick(0);
	Timer timer = new Timer();
	Encoder sampleEncoder = new Encoder(1, 2, true, EncodingType.k4X);
	public static final double WHEEL_DIAMETER = 6;
	public static final double PULSE_PER_REVOLUTION = 360;
	public static final double ENCODER_GEAR_RATIO = 1;
	public static final double GEAR_RATIO = 10.71 / 1;
	public static final double FUDGE_FACTOR = 1.0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Official code for the MechWarrior 2017 FRC robot");
		chooser.addDefault("path1a", path1a);
		chooser.addObject("path1b", path1b);
		chooser.addObject("path1c", path1c);
		chooser.addObject(path2a, path2a);
		chooser.addObject(path2b, path2b);
		chooser.addObject(path2c, path2c);
		chooser.addObject(path3a, path3a);
		chooser.addObject(path3b, path3b);
		chooser.addObject(path3c, path3c);
		SmartDashboard.putData("Auto choices", chooser);
		Thread visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat. If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
		final double distancePerPulse = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION / ENCODER_GEAR_RATIO
				/ GEAR_RATIO * FUDGE_FACTOR;
		sampleEncoder.setDistancePerPulse(distancePerPulse);
		System.out.println("Distance per pulse: " + distancePerPulse);

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
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		sampleEncoder.reset();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		double encoderDistanceReading = sampleEncoder.getDistance();
		SmartDashboard.putNumber("encoder reading", encoderDistanceReading);
		switch (autoSelected) {
		case path1a:
			myRobot.setSafetyEnabled(false);
			myRobot.drive(-0.5, 1.0); // spin at half speed
			Timer.delay(.5); // for 2 seconds
			myRobot.drive(0.0, 0.0); // stop robot break;
		case path1b:
			System.out.println("Path 1b");
		case path1c:
			System.out.println("Path 1c");
		case path2a:
			System.out.println("Path 2a");
		case path2b:
			System.out.println("Path 2b");
		case path2c:
			System.out.println("Path 2c");
		case path3a:
			System.out.println("Path 3a");
		case path3b:
			System.out.println("Path 3b");
		case path3c:
			System.out.println("Path 3c");
		default:
			if (encoderDistanceReading < 5.0) {
				myRobot.setSafetyEnabled(false);
				myRobot.drive(-0.5, 0.0); // drive forwards half speed
				Timer.delay(.5); // for 2 seconds
				myRobot.drive(0.0, 0.0); // stop robot
				break;
			}
		}

	}

	public void teleopInit() {
		sampleEncoder.reset();

	}

	/**
	 * } This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double encoderDistanceReading = sampleEncoder.getDistance();
		SmartDashboard.putNumber("encoder reading", encoderDistanceReading);
		myRobot.arcadeDrive(stick);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
