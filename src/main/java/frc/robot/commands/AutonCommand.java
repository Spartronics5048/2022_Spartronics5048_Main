// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutonCommand extends CommandBase {
  public final DriveSubsystem m_driveSubsystem;
  static double startTime;
  static boolean firstRun = true;
  /** Creates a new AutonCommand. */
  public AutonCommand(DriveSubsystem ds) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveSubsystem = ds;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(firstRun){
      startTime = Timer.getFPGATimestamp();
      firstRun = false;
    }
    if(Timer.getFPGATimestamp() - startTime < 2.0){
      m_driveSubsystem.tankDrive(1, 0);
    }else{
      m_driveSubsystem.tankDrive(0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    firstRun = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    return false;
  }
}