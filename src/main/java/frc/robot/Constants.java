// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Drive CAN Motors
    public static final int rf_drive = 3;
    public static final int rr_drive = 4;
    public static final int lf_drive = 1;
    public static final int lr_drive = 2;
    //Arm CAN Motor
    public static final int arm_motor = 5;
    //Shooter CAN Motor
    public static final int shooter_motor = 6;

    /////////////////////////

    //Xbox Controllers
    public static final int Driver = 0;
    public static final int Operator = 1;

    //Drive Dead band
	public static final double forwardDeadBand = 0.1;
	public static final double turnDeadBand = 0.15; 

    //Intake Dead Band
    public static final double intakeDeadBand = 0.1;

    //Arm values
    public static final double armHoldUp = 0.08;
    public static final double armHoldDown = 0.13;
    public static final double armTravel = 0.5;
    public static final double armTimeUp = 0.5;
    public static final double armTimeDown = 0.35;

    //Arm Soft Limit values
    public static final float maxForward = 50;
    public static final float maxReverse = 50;
}
