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
	
	void periodic(){
		double elapsedTime = Timer.getFPGATimestamp() - startTime;
		//make sure only one of these isnt commented out
		spitBallandTurnAround(elapsedTime);
		//or if you just want to spit and drive backwards
		//spitaBallandDriveBack(elapsedTime);
	}
	
	void spitBallandTurnAround(double elapsedTime){
		if(elapsedTime < 1.5){	//for the first 1.5 seconds.....
			//eject ball
			//might be backwards, just switch to positive 1
			shooter.periodic(-1);
		}else if(elapsedTime >=1.5 &&  elapsedTime < 3.5){//for the next two seconds.......
			//set shooter to not move
			shooter.periodic(0);
			// and drive backwards at 60% power
			drive.periodic(-0.6, 0);
		}else if(elapsedTime >= 3.5 &&  elapsedTime < 4.5){//for the next one second.....
			//turn around at 60% power
			drive.periodic(0, 0.6);
		}else if(elapsedTime >= 5.5 &&  elapsedTime < 7.0){//for the next one and a half seconds.....
			//move forward at 40% power
			drive.periodic(0.4, 0);
			// and set shooter to intake ball, might need to flipslop value to negative 1
			shooter.periodic(1);
		}else if(elapsedTime >= 7.0){//for the rest of the time
			//turn off all used motors
			drive.periodic(0,0);
			shooter.periodic(0);
		}
	}
	
	void spitBallandDriveBack(double elapsedTime){
		if(elapsedTime < 1.5){	//for the first 1.5 seconds.....
			//eject ball
			//might be backwards, just switch to positive 1
			shooter.periodic(-1);
		}else if(elapsedTime >=1.5 &&  elapsedTime < 3.5){//for the next two seconds.......
			//set shooter to not move
			shooter.periodic(0);
			// and drive backwards at 60% power
			drive.periodic(-0.6, 0);
		}else if(elapsedTime >= 3.5){//for the rest of the time
			//turn off all used motors
			drive.periodic(0,0);
			shooter.periodic(0);
		}
	}
}