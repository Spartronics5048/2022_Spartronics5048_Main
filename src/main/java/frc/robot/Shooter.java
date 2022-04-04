// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter {
	private CANSparkMax m_intake;

	public Shooter() {
		m_intake = new CANSparkMax(Constants.shooter_motor, MotorType.kBrushed);
		m_intake.restoreFactoryDefaults();
	}

	void periodic(double leftPower, double rightPower, XboxController controller) {
		double speed = 0;
		if (leftPower > 0.1){
			speed = -leftPower;
		} else if (rightPower > 0.1){
			speed = rightPower;
		}

		m_intake.set(speed);
		
	}

	void UpdateData() {
		// Pull Buss Voltage for each drive motor
		double Intake_Voltage = m_intake.getBusVoltage();
		// Pull Current for each drive motor
		double Intake_Current = m_intake.getOutputCurrent();
		// Pull Applied Output for each drive motor
		double Intake_appliedOut = m_intake.getAppliedOutput();

		////////////////////////////////////////

		// Print Buss Voltage of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Bus Voltage", Intake_Voltage);
		// Print Current of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Current", Intake_Current);
		// Print Applied Output of each drive motor to Smart Dashboard
		SmartDashboard.putNumber("Right Front Applied Output", Intake_appliedOut);
	}
}