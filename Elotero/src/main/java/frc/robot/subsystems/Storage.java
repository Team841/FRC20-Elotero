/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;

public class Storage extends SubsystemBase {
  private final CANSparkMax motor1 = new CANSparkMax(C.Storage.CANidStorage, MotorType.kBrushless);

  /**
   * Creates a new ExampleSubsystem.
   */
  public Storage() {

//



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public void stopSfore(){
  motor1.set(0);


}

  //private static void set(double store) {
 // }

  public void startStore() {
 
  motor1.set(C.Storage.Store);


}

}
