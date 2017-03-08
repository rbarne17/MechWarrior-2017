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

import org.usfirst.frc.team4524.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FuelPickupDropoff extends Subsystem {

	private SpeedController fuelPickupDropoffController;
	private AnalogInput analogInput;
	
	public FuelPickupDropoff(){
		fuelPickupDropoffController = new Talon(RobotMap.fuelPickupDropoffController);
		analogInput = new AnalogInput(RobotMap.analogInput);
	}
	
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
	public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void down(){
    	fuelPickupDropoffController.set(-.5);;
    	 }
    public void up(){
    	fuelPickupDropoffController.set(.5);
    }
    public void stop(){
    	fuelPickupDropoffController.set(.0);
    }
    
    public double getVoltage(){
    	return analogInput.getVoltage();
    }
}

