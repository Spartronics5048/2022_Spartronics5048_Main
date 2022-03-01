// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
  public final DriveSubsystem m_Subsystem;
  public final XboxController m_driveController;

  /** Creates a new DriveCommand. */
  public DriveCommand(DriveSubsystem subsystem, XboxController driveController) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Subsystem = subsystem;
    m_driveController = driveController;
    //DriveSubsystem is marked as requirment
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //DriveSubsystem is called using left-Y and Right-Y as inputs
    m_Subsystem.tankDrive(-m_driveController.getLeftY(), -m_driveController.getRightX());
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
