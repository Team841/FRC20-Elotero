/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;

public class Indexer extends SubsystemBase {
  /**
   * Creates a new Indexer.
   */

  private final CANSparkMax Indexmotor  = new CANSparkMax(C.CANid.index,MotorType.kBrushless);
private final DigitalInput Sensor = new DigitalInput(C.Index.SensorChannel);
  public Indexer() {
    Indexmotor.restoreFactoryDefaults();
    Indexmotor.setSmartCurrentLimit(40, 20); //Set current limits to be 40A while spinning & 20A while stopped
    Indexmotor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void startIndex() {
    Indexmotor.set(C.Index.IndexPower);
  }
  public void stopIndex(){
    Indexmotor.set(0);
  }
  public boolean isBallPresent(){
    return !Sensor.get();
  }
}
