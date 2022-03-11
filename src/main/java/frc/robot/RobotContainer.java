// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.AutonCommand;
import frc.robot.commands.DisabledCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined in this class

  ////////////////////////////////////////////

  //DriveSubsystem is defined
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  //ArmSubsystem is defined
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  //ShooterSubsystem is defined
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

  ///////////////////////////////////////////

  //DriveController is defined
  public XboxController driveController = new XboxController(Constants.Driver);
  //OperatorController is defined
  public XboxController operatorController = new XboxController(Constants.Operator);

  ///////////////////////////////////////////

  //Shutdown Command for the robot
  private final Command disabledCommand = new DisabledCommand(driveSubsystem, shooterSubsystem, armSubsystem);

  //Arm Movement Commands
  private final Command armUp = new ArmCommand(armSubsystem, true);
  private final Command armDown = new ArmCommand(armSubsystem, false);

  //Auton Command
  private final Command autonCommand = new AutonCommand(driveSubsystem);
 
  //Drive Command
  private Command driveCommand = new DriveCommand(driveSubsystem, driveController);

  ///////////////////////////////////////////

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
      //Define Operator D-Pad up
      new POVButton(operatorController, 1).whenPressed(armUp);

      //Define Operator Buttons - Arm
      new JoystickButton(operatorController, Button.kY.value).whenPressed(armUp);
      new JoystickButton(operatorController, Button.kA.value).whenPressed(armDown);
      if(operatorController.getRightTriggerAxis() <= 0){
        new JoystickButton(operatorController, Axis.kLeftTrigger.value).whileActiveContinuous(new ShooterCommand(shooterSubsystem, -operatorController.getLeftTriggerAxis()));
      }else if(operatorController.getLeftTriggerAxis() <= 0){
        new JoystickButton(operatorController, Axis.kRightTrigger.value).whileActiveContinuous(new ShooterCommand(shooterSubsystem, operatorController.getRightTriggerAxis()));
      }
     
      //Define Operator Buttons - Shooter
      //new JoystickButton(operatorController, Button.kX.value).whenPressed(suckIn);
      //new JoystickButton(operatorController, Button.kB.value).whenPressed(blowOut);
  }

  public Command getTeleopDriveCommand() {
    return driveCommand;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autonCommand;
  }

  public Command getDisabledCommand(){
    //Order execution of Shutdown Code
    return disabledCommand;
  }
}
