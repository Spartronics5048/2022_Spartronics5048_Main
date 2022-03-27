// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  Thread visionThread;
  
  final XboxController driveController;
  final XboxController operatorController;
  
  final Drive drive;
  final Arm arm;
  final Shooter shooter;
  final Autonomous auto;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
	  visionThread = CameraCode.initialize();
	  visionThread.setDaemon(true);
      visionThread.start();
	  
	  drive = new Drive();
	  arm = new Arm();
	  shoot = new Shoot();
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
   
    }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
   
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
	  auto = new Autonomous(drive, arm, shooter);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
	  auto.periodic();
  }

  @Override
  public void teleopInit() {
	  
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
	  //first go through and test for changes that could happen inside of the different subsystems to change values
	  
	  //not sure which buttons 6 and 8 are, but Im sure you can figure out the button numbers with a little researching/experimenting
	  if(operatorController.getRawButtonPressed(6)){
		 //set arm to go up
		 arm.setDirection(true);
	  }else if(operatorController.getRawButtonPressed(8)){
		  //set arm to go down
		 arm.setDirection(false);
	  }
	
	  //then run the periodic functions inside of the subsystems
	  drive.periodic(-driveController.getLeftY(), driveController.getRightX());
	  arm.periodic();
	  
	  //only one trigger at a time, both means nothing happens
	  if(operatorController.getLeftTriggerAxis() > Constants.intakeDeadBand && operatorController.getRightTriggerAxis() < Constants.intakeDeadBand){
		  shoot.periodic(-operatorController.getLeftTriggerAxis());
	  }else if(operatorController.getRightTriggerAxis() > Constants.intakeDeadBand && operatorController.getLeftTriggerAxis() < Constants.intakeDeadBand){
		  shoot.periodic(operatorController.getRightTriggerAxis());
	  }
  }

  @Override
  public void testInit() {
   
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
