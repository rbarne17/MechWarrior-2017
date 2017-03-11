package org.usfirst.frc.team4524.robot.commands;
import org.usfirst.frc.team4524.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RopeClimbUp extends Command {
	
	private boolean climbUp;
	
	public RopeClimbUp(){
		requires(Robot.ropeClimber);
		climbUp = true;
		
	}
	
	public RopeClimbUp(boolean climb){
		requires (Robot.ropeClimber);
		this.climbUp = climbUp;
		
	}
	
	@Override
	protected void initialize (){
		
		
	}
	@Override
	protected void execute(){
		
		Robot.ropeClimber.climbUp();
	}
	@Override
	protected boolean isFinished(){
		return(true);
	}
	

}
