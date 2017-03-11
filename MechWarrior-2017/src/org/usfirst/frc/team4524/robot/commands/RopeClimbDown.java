package org.usfirst.frc.team4524.robot.commands;
import org.usfirst.frc.team4524.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RopeClimbDown extends Command {
	
	private boolean climbDown;
	
	public RopeClimbDown(){
		requires(Robot.ropeClimber);
		climbDown = true;
		
	}
	
	public RopeClimbDown(boolean down){
		requires (Robot.ropeClimber);
		this.climbDown = climbDown;
		
	}
	
	@Override
	protected void initialize (){
		
	}
	@Override
	protected void execute(){
		
		Robot.ropeClimber.climbDown();
	}
	@Override
	protected boolean isFinished(){
		return(true);
	}
	

}
