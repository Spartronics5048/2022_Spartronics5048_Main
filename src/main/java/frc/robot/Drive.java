// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Drive{
	private CANSparkMax rf_drive, rr_drive, lf_drive, lr_drive;
	public Drive(){
		 //Drive motors are defined
		rf_drive = new CANSparkMax(Constants.rf_drive, MotorType.kBrushed);
		rr_drive = new CANSparkMax(Constants.rr_drive, MotorType.kBrushed);
		lf_drive = new CANSparkMax(Constants.lf_drive, MotorType.kBrushed);
		lr_drive = new CANSparkMax(Constants.lr_drive, MotorType.kBrushed);
    
		//Restory Factory Default of Drive Motors
		//Factor Default is not saved between power cycles
		rf_drive.restoreFactoryDefaults();
		rr_drive.restoreFactoryDefaults();
		lf_drive.restoreFactoryDefaults();
		lr_drive.restoreFactoryDefaults();

		//Left Drive train is inverted
		lf_drive.setInverted(true);
		lr_drive.setInverted(true);

		//Rear motors are set to follow forward motors
		rr_drive.follow(rf_drive);
		lr_drive.follow(lf_drive);
	}
	
	public void periodic(double value1, double value2){
		//More methods for different drive types could be written and then you can just change which one could be used
		TankDrive(value1, value2);
		UpdateData();
	}
	
	void TankDrive(double forwardPower, double turnPower){
			/* we want to be able to have it so that either stick can affect the
		robots movement without them both needing to be above dead band, so we need
		to split up the statements from the old implementation into step 1, applying forward power
		step 2, applying turning power. We can then place the sections into if statements to see
		if the values of the controllers are above deadband before applying their value to the right 
		and left power. We could define the dead band values here in the code, but to be clean they
		should be defined in the file Constants.java, which I believe I did correctly*/
	
		double rightPower = 0;
		double leftPower = 0;
	
		//if forwardPower is greater than deadband OR forwardPower is less than deadband
		if(forwardPower > Constants.forwardDeadBand || (Constants.forwardDeadBand * -1) > forwardPower){
			//True, set input to power
			rightPower = forwardPower;
			leftPower = forwardPower;
		}
		//else do nothing
	
		//if turnPower is greater than deadband OR turnPower is less than deadband
		if(turnPower > Constants.turnDeadBand || (Constants.turnDeadBand * -1) > turnPower){
			//take the current values of the motors and add the turn value to them, and then set that as the new values
			//we could have done this with forward power too, but since the value is just 0 to start always, there is no point
			//in taking the current value of the motors.
			rightPower = rightPower - turnPower;
			leftPower = leftPower + turnPower;
		}
		//else do nothing
	
		//forward the values on to the motors
		rf_drive.set(rightPower);
		lf_drive.set(leftPower);
	}
	
	void UpdateData(){

		//Pull Buss Voltage for each drive motor
		double rf_Voltage = rf_drive.getBusVoltage();
		double rr_Voltage = rr_drive.getBusVoltage();
		double lf_Voltage = lf_drive.getBusVoltage();
		double lr_Voltage = lr_drive.getBusVoltage();

		//Pull Current for each drive motor
		double rf_current = rf_drive.getOutputCurrent();
		double rr_current = rr_drive.getOutputCurrent();
		double lf_current = lf_drive.getOutputCurrent();
		double lr_current = lr_drive.getOutputCurrent();

		//Pull Applied Output for each drive motor	
		double rf_appliedOut = rf_drive.getAppliedOutput();
		double rr_appliedOut = rr_drive.getAppliedOutput();
		double lf_appliedOut = lf_drive.getAppliedOutput();
		double lr_appliedOut = lr_drive.getAppliedOutput();

		////////////////////////////////

		// Open SmartDashboard when your program is running to see the values

		//Print Buss Voltage of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Bus Voltage", rf_Voltage);
		SmartDashboard.putNumber("Right Rear  Bus Voltage", rr_Voltage);
		SmartDashboard.putNumber("Left  Front Bus Voltage", lf_Voltage);
		SmartDashboard.putNumber("Left  Rear  Bus Voltage", lr_Voltage);

		//Print Current of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Current", rf_current);
		SmartDashboard.putNumber("Right Rear  Current", rr_current);
		SmartDashboard.putNumber("Left  Front Current", lf_current);
		SmartDashboard.putNumber("Left  Rear  Current", lr_current);

		//Print Applied Output of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Applied Output", rf_appliedOut);
		SmartDashboard.putNumber("Right Rear  Applied Output", rr_appliedOut);
		SmartDashboard.putNumber("Left  Front Applied Output", lf_appliedOut);
		SmartDashboard.putNumber("Left  Rear  Applied Output", lr_appliedOut);

	}
}