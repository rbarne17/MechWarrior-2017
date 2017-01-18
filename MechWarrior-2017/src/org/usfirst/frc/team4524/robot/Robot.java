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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick stick = new Joystick(0);
	Timer timer = new Timer();
	Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Official code for the MechWarrior 2017 FRC robot");
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
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
		System.out.println("Auto selected: " + autoSelected);
		sampleEncoder.reset();
		sampleEncoder.setMaxPeriod(.1);
		sampleEncoder.setMinRate(10);
		sampleEncoder.setDistancePerPulse(5);
		sampleEncoder.setReverseDirection(true);
		sampleEncoder.setSamplesToAverage(7);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			myRobot.setSafetyEnabled(false);
			myRobot.drive(-0.5, 1.0); // spin at half speed
			Timer.delay(2.0); // for 2 seconds
			myRobot.drive(0.0, 0.0); // stop robot break;
		case defaultAuto:
		default:
			myRobot.setSafetyEnabled(false);
			myRobot.drive(-0.5, 0.0); // drive forwards half speed
			Timer.delay(2.0); // for 2 seconds
			myRobot.drive(0.0, 0.0); // stop robot
			break;
		}
		int count = sampleEncoder.get();
		System.out.println("Encoder count: " + count);
		double distance = sampleEncoder.getRaw();
		System.out.println("Encoder distance (getRaw): " + distance);
		distance = sampleEncoder.getDistance();
		System.out.println("Encoder distance (getDistance): " + distance);
		double period = sampleEncoder.getPeriod();
		System.out.println("Encoder period: " + period);
		double rate = sampleEncoder.getRate();
		System.out.println("Encoder rate: " + rate);
		boolean direction = sampleEncoder.getDirection();
		System.out.println("Encoder direction: " + direction);
		boolean stopped = sampleEncoder.getStopped();
		System.out.println("Encoder stopped: " + stopped);
	}

	public void teleopInit() {
		sampleEncoder.reset();
		sampleEncoder.setMaxPeriod(.1);
		sampleEncoder.setMinRate(10);
		sampleEncoder.setDistancePerPulse(5);
		sampleEncoder.setReverseDirection(true);
		sampleEncoder.setSamplesToAverage(7);

	}

	/**
	 * } This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		myRobot.arcadeDrive(stick);
		int count = sampleEncoder.get();
		System.out.println("Encoder count: " + count);
		double distance = sampleEncoder.getRaw();
		System.out.println("Encoder distance (getRaw): " + distance);
		distance = sampleEncoder.getDistance();
		System.out.println("Encoder distance (getDistance): " + distance);
		double period = sampleEncoder.getPeriod();
		System.out.println("Encoder period: " + period);
		double rate = sampleEncoder.getRate();
		System.out.println("Encoder rate: " + rate);
		boolean direction = sampleEncoder.getDirection();
		System.out.println("Encoder direction: " + direction);
		boolean stopped = sampleEncoder.getStopped();
		System.out.println("Encoder stopped: " + stopped);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
