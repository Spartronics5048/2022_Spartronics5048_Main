// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  Thread visionThread;

  //DriveController is defined
  public static XboxController driveController = new XboxController(Constants.Driver);
  //OperatorController is defined
  public static XboxController operatorController = new XboxController(Constants.Operator);

  // Commands? are defined
  private final Drive drive = new Drive();
  private final Arm arm = new Arm();
  private final Shooter Shooter = new Shooter();

  //Auton Init
  final Autonomous auton = new Autonomous(drive, arm, Shooter);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Initalize the Camera Server
    visionThread = Camera.initialize();
	  visionThread.setDaemon(true);
    visionThread.start();
    arm.armInit();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    robotInit();
    
    auton.InitTimer();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    auton.periodic();
  }

  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //first go through and test for changes that could happen inside of the different subsystems to change values
	  //System.out.println("BtnY: " + operatorController.getYButton() + "BtnX: " + operatorController.getXButton());
    //if(operatorController.getRawButtonPressed(4)){
    if(operatorController.getYButton()){
      //set arm to go up
      arm.setPIDDirection(75.0);
    //}else if(operatorController.getRawButtonPressed(1)){
    }else if(operatorController.getAButton()){
      //set arm to go down
      arm.setPIDDirection(-10.0);
    }

    if (operatorController.getXButton()){
      arm.setPercentOut(0.0);
      arm.armInit();
    }
    if (operatorController.getBButton()){
      arm.setPercentOut(0.0);
      arm.armEncZero();
    }
     
     //then run the periodic functions inside of the subsystems
     drive.periodic(-driveController.getLeftY(), driveController.getRightX());
     arm.periodic();

     Shooter.periodic(operatorController.getLeftTriggerAxis(), operatorController.getRightTriggerAxis(), operatorController);
  }
}
