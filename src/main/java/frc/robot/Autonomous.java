// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous{
	Drive drive;
  	Arm arm;
    Shooter shooter;
	
	double startTime;
	public Autonomous(Drive d, Arm a, Shooter s){
		drive = d;
		arm = a;
		shooter = s;
		startTime = Timer.getFPGATimestamp();
	}
	
	void InitTimer() {
		startTime = Timer.getFPGATimestamp();
	}

	void periodic(){
		double elapsedTime = Timer.getFPGATimestamp() - startTime;
		//make sure only one of these is enabled
		//spitBallandTurnAround(elapsedTime);
		//or if you just want to spit and drive backwards
		spitBallandDriveBack(elapsedTime);
	}
	
	//Disabled Code
	//Does not have code to move arm
	void spitBallandTurnAround(double elapsedTime){
		//System.out.println("E Time: " + elapsedTime);
		if(elapsedTime < 1.5){	//for the first 1.5 seconds.....
			//eject ball
			//might be backwards, just switch to positive 1
			shooter.periodic(0, 1, null);
		}else if(elapsedTime >=1.5 &&  elapsedTime < 3.5){//for the next two seconds.......
			//set shooter to not move
			shooter.periodic(0, 0, null);
			// and drive backwards at 60% power
			drive.periodic(-0.6, 0);
		}else if(elapsedTime >= 3.5 &&  elapsedTime < 4.5){//for the next one second.....
			//turn around at 60% power
			drive.periodic(0, 0.6);
		}else if(elapsedTime >= 5.5 &&  elapsedTime < 7.0){//for the next one and a half seconds.....
			//move forward at 40% power
			drive.periodic(0.4, 0);
			// and set shooter to intake ball, might need to flipslop value to negative 1
			shooter.periodic(1, 0, null);
		}else if(elapsedTime >= 7.0){//for the rest of the time
			//turn off all used motors
			drive.periodic(0,0);
			shooter.periodic(0, 0, null);
		}
	}
	
	//Active Code
	void spitBallandDriveBack(double elapsedTime){
		if(elapsedTime < 1.5){	//for the first 1.5 seconds.....
			//eject ball
			//might be backwards, just switch to positive 1
			shooter.periodic(0, 1, null);
		}else if(elapsedTime >=1.5 &&  elapsedTime < 2.5){//for the next two seconds.......
			//set shooter to not move
			shooter.periodic(0, 0, null);
			// and drive backwards at 60% power
			drive.periodic(-0.6, 0);
		}else if(elapsedTime >= 3.5){//for the rest of the time
			//turn off all used motors
			drive.periodic(0,0);
			shooter.periodic(0, 0, null);
		}
	}
}
