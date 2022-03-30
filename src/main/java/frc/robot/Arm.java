// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Arm{
	private CANSparkMax arm_motor;
	private boolean armUp = true; //Arm initialized to up because that's how it would start a match
	private double lastBurstTime = 0;
	private boolean moving; //indicates if the arm is currently moving, we dont want to switch directions while it is moving else potentialy messing up the timing and the robot wont know where the arm is
	public Arm(){
		//Create arm motor
		arm_motor = new CANSparkMax(Constants.arm_motor, MotorType.kBrushless);
		arm_motor.restoreFactoryDefaults();
		//Set Inverted state
		arm_motor.setInverted(false);
		//Set break on Idle
		arm_motor.setIdleMode(IdleMode.kBrake);

		//Enable Soft Limit
		arm_motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
		arm_motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
		//Set Soft Limit
		arm_motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.maxForward);
		arm_motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.maxReverse);
	}
	
	public void setDirection(boolean arm_Up){
		if(arm_Up && !armUp && !moving){
			lastBurstTime = Timer.getFPGATimestamp();
			armUp = true;
			moving = true;
		}
		else if(!arm_Up && armUp && !moving){
			lastBurstTime = Timer.getFPGATimestamp();
			armUp = false;
			moving = true;
		}  
	}
	
	public void actuateArm(){
		if(armUp){
			if(Timer.getFPGATimestamp() - lastBurstTime < Constants.armTimeUp){
			arm_motor.set(Constants.armTravel);
			}
			else{
				arm_motor.set(Constants.armHoldUp);
				//arm has stoppped moving, we can switch direction again
				moving = false;
			}
		}else{
			if(Timer.getFPGATimestamp() - lastBurstTime < Constants.armTimeDown){
			arm_motor.set(-Constants.armTravel);
			}
			else{
				arm_motor.set(-Constants.armHoldDown);
				//arm has stoppped moving, we can switch direction again
				moving = false;
			}
		}
	}
  
  public void periodic() {
	  actuateArm();
	  UpdateData();
  }
  
  void UpdateData(){
	  //Pull Buss Voltage for each drive motor
    double Arm_Voltage = arm_motor.getBusVoltage();
    //Pull Current for each drive motor
    double Arm_current = arm_motor.getOutputCurrent();
    //Pull Applied Output for each drive motor
    double Arm_appliedOut = arm_motor.getAppliedOutput();

    ////////////////////////////////////////

    //Print Buss Voltage of each drive motor to Smart Dashboard
    SmartDashboard.putNumber("Right Front Bus Voltage", Arm_Voltage);
    //Print Current of each drive motor to Smart Dashboard
    SmartDashboard.putNumber("Right Front Current", Arm_current);
    //Print Applied Output of each drive motor to Smart Dashboard
    SmartDashboard.putNumber("Right Front Applied Output", Arm_appliedOut);
  }
}