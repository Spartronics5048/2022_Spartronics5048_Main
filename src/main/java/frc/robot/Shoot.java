import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Shoot{
	private CANSparkMax intake;
	private power;
	public Shoot(){
		intake = new CANSparkMax(Constants.shooter_motor, MotorType.kBrushed);
		intake.restoreFactoryDefaults();
		intake.setInverted(false);
	}
	
	periodic(power){
		intake.set(power);
	}
	
	void UpdateData(){
		//Pull Buss Voltage for each drive motor
		double Intake_Voltage = m_intake.getBusVoltage();
		//Pull Current for each drive motor
		double Intake_current = m_intake.getOutputCurrent();
		//Pull Applied Output for each drive motor
		double Intake_appliedOut = m_intake.getAppliedOutput();

		////////////////////////////////////////

		//Print Buss Voltage of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Bus Voltage", Intake_Voltage);
		//Print Current of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Current", Intake_current);
		//Print Applied Output of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Applied Output", Intake_appliedOut);
	}
}