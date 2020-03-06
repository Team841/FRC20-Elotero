/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */

   private final Solenoid elbow;

   private final CANSparkMax roller;
  public Intake() {
    elbow = new Solenoid(C.intake.Module, C.intake.Channel);
roller = new CANSparkMax(C.CANid.intake, MotorType.kBrushless);
  }
public void elbowOut(){
  elbow.set(true);
}
public void elbowIn(){
  elbow.set(false);
}
public void rollerOn(){
  roller.set(C.intake.rollerPower);
}
public void rollerOff(){
  roller.set(0);
}
public void rollerSpitOut(){
  roller.set (C.intake.rollerSpitOutPower);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
