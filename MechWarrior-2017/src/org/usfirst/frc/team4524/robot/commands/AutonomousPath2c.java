package org.usfirst.frc.team4524.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive over the line and then shoot the ball. If the hot goal is not detected,
 * it will wait briefly.
 */
public class AutonomousPath2c extends CommandGroup {
	public AutonomousPath2c() {
		System.out.println("Path 2c");
		// addSequential(new CloseClaw());
		// addSequential(new WaitForPressure(), 2);
		// if (Robot.isReal()) {
		// // NOTE: Simulation doesn't currently have the concept of hot.
		// addSequential(new CheckForHotGoal(2));
		// }
		// addSequential(new SetPivotSetpoint(45));
		// addSequential(new DriveForward(8, 0.3));
		// addSequential(new Shoot());
	}
}
