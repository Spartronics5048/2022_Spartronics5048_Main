// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class DisabledCommand extends CommandBase {
  public final DriveSubsystem m_driveSubsystem;
  public final ShooterSubsystem m_shootSubsystem;
  public final ArmSubsystem m_armSubsystem;

  /** Creates a new DriveCommand. */
  public DisabledCommand(DriveSubsystem dsubsystem, ShooterSubsystem shsubsystem, ArmSubsystem asubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveSubsystem = dsubsystem;
    m_shootSubsystem = shsubsystem;
    m_armSubsystem = asubsystem;
    //DriveSubsystem is marked as requirment
    addRequirements(dsubsystem, shsubsystem, asubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //DriveSubsystem set to 0
    m_driveSubsystem.tankDrive(0, 0);
    //set shoot to 0
    m_shootSubsystem.actuateShooter(0);
    //set arm to not do anything
    m_armSubsystem.actuateArm(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
