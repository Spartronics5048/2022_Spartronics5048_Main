// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

  private CANSparkMax rf_drive, rr_drive, lf_drive, lr_drive;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
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

    rr_drive.follow(rf_drive);
    lr_drive.follow(lf_drive);
  }

  public void tankDrive(double turnPower, double forwardPower) {
   
    double rightPower = forwardPower + turnPower;
    double leftPower = forwardPower - turnPower;

    rf_drive.set(rightPower);
    lf_drive.set(leftPower);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
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

    /////////////////////////////////

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
