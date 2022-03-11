// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax m_intake;
  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    m_intake = new CANSparkMax(Constants.shooter_motor, MotorType.kBrushless);
    m_intake.restoreFactoryDefaults();
  }

  public void actuateShooter(double power){
    m_intake.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
