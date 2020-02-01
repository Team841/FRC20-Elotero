/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

   
private final WPI_TalonFX shootRight = new WPI_TalonFX(5);
private final WPI_TalonFX shootLeft = new WPI_TalonFX(6);

//Constructor creates & sets up a new DriveTrain
  public ExampleSubsystem() {
    //Controller housekeeping in the constructor
    shootRight.configFactoryDefault();
    shootLeft.configFactoryDefault();

    //Set #2 controllers to follow #1 in both drives
    shootLeft.follow(shootRight);
    shootLeft.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
