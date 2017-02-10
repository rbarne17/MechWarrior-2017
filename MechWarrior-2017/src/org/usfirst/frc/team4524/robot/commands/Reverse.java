package org.usfirst.frc.team4524.robot.commands;

import org.usfirst.frc.team4524.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Reverse extends Command {

    public Reverse() {
    	requires(Robot.driveTrain); 

    	
    }

    
    @Override
	protected void initialize() {
    }

    
    @Override
	protected void execute() {
    	Robot.driveTrain
    
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    }
    public void invertDrive()
}

