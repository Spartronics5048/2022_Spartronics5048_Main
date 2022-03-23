// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

  //Varibles needed for the code
  private CANSparkMax arm_motor;
  private boolean armUp = true; //Arm initialized to up because that's how it would start a match
  private double lastBurstTime = 0;

  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {
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

  public void actuateArm(boolean arm_Up){
    if(arm_Up && !armUp){
      lastBurstTime = Timer.getFPGATimestamp();
      armUp = true;
    }
    else if(!arm_Up && armUp){
      lastBurstTime = Timer.getFPGATimestamp();
      armUp = false;
    }  

    if(armUp){
      if(Timer.getFPGATimestamp() - lastBurstTime < Constants.armTimeUp){
        arm_motor.set(Constants.armTravel);
      }
      else{
        arm_motor.set(Constants.armHoldUp);
      }
    }
    else{
      if(Timer.getFPGATimestamp() - lastBurstTime < Constants.armTimeDown){
        arm_motor.set(-Constants.armTravel);
      }
      else{
        arm_motor.set(-Constants.armHoldDown);
      }
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
