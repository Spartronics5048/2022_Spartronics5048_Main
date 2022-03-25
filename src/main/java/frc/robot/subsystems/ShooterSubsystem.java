// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax m_intake;
  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    m_intake = new CANSparkMax(Constants.shooter_motor, MotorType.kBrushed);
    m_intake.restoreFactoryDefaults();
    m_intake.setInverted(false);
  }

  public void actuateShooter(double power){
    m_intake.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Pull Buss Voltage for each drive motor
    double Intake_Voltage = m_intake.getBusVoltage();
    //Pull Current for each drive motor
    double Intake_current = m_intake.getOutputCurrent();
    //Pull Applied Output for each drive motor
    double Intake_appliedOut = m_intake.getAppliedOutput();

    ////////////////////////////////////////

    //Print Buss Voltage of each drive motor to Smart Dashboard
    SmartDashboard.putNumber("Right Front Bus Voltage", Intake_Voltage);
    //Print Current of each drive motor to Smart Dashboard
    SmartDashboard.putNumber("Right Front Current", Intake_current);
    //Print Applied Output of each drive motor to Smart Dashboard
    SmartDashboard.putNumber("Right Front Applied Output", Intake_appliedOut);
  }
}
