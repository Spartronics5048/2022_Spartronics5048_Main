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

    //Xbox Controllers
    public static final int Driver = 0;
    //public static final int Operator = 1;

    //Xbox Controller Axis
    public static final int Left_Y_axis = 1;
    public static final int Right_Y_axis = 5;
}
