/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;

public class Storage extends SubsystemBase {
  private final CANSparkMax Storemotor = new CANSparkMax(C.CANid.storage, MotorType.kBrushless);

  private final DigitalInput Sensor = new DigitalInput(C.Storaged.SensorChannel);
  //private final CANSparkMax Indexmotor = new CANSparkMax(C.CANid.index, MotorType.kBrushless);
  /**
   * Creates a new ExampleSubsystem.
   */
  public Storage() {

Storemotor.restoreFactoryDefaults();


Storemotor.setSmartCurrentLimit(30, 15); //Set current limits to be 40A while spinning & 20A while stopped

Storemotor.burnFlash();


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Storage_sense", isBallPresent());
  }

public void stopStore(){
  Storemotor.set(0);


}

  //private static void set(double store) {
 // }

  public void startStore() {
 
    Storemotor.set(C.Storaged.StorePower);

  }
  public boolean isBallPresent(){
    return !Sensor.get();
  }

}
