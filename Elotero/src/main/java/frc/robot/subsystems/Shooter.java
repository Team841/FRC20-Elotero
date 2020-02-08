/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;

public class Shooter extends SubsystemBase {
  private final WPI_TalonFX shootRightTalonFX = new WPI_TalonFX(C.Shoot.CANidShootRight);
  private final WPI_TalonFX shootLeftTalonFX = new WPI_TalonFX(C.Shoot.CANidShootLeft);
  /**
   * Creates a new ExampleSubsystem.
   */
  public Shooter() {


    shootRightTalonFX.configFactoryDefault();
    shootLeftTalonFX.configFactoryDefault();



    shootLeftTalonFX.follow(shootRightTalonFX);
    shootLeftTalonFX.setInverted(true);




  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public void stopShot(){
  shootRightTalonFX.set(0);


}
public void startShot(){
 
  shootRightTalonFX.set(C.Shoot.shotPower);


}

}
