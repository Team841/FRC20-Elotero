/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.*;

public class Indexer extends SubsystemBase {
  public CANSparkMax m_motor = new CANSparkMax(C.CANid.index, MotorType.kBrushless);
  /**
   * Creates a new indexer.
   */
  public Indexer() {
    m_motor.restoreFactoryDefaults();
    m_motor.setSmartCurrentLimit(40, 20); //Set current limits to be 40A while spinning & 20A while stopped
    m_motor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public void startIndex(){
  m_motor.set(C.Indexer.indexPower);
}

public void stopIndex(){
  m_motor.set(0);
}

}
