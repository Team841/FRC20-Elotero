/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

   
private final WPI_TalonFX shootRight = new WPI_TalonFX(Constants.Shoot.CANidShootRight);
private final WPI_TalonFX shootLeft = new WPI_TalonFX(Constants.Shoot.CANidShootLeft);

//Constructor creates & sets up a new DriveTrain
  public Shooter() {
    //Controller housekeeping in the constructor
    shootRight.configFactoryDefault();
    shootLeft.configFactoryDefault();

    //Set Left controller to follow Right while Inverted
    shootLeft.follow(shootRight);
    shootLeft.setInverted(true);

    shootRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,Constants.kPIDLoopIdx,Constants.kTimeoutMs);
    shootLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,Constants.kPIDLoopIdx,Constants.kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		shootRight.config_kF(Constants.kPIDLoopIdx, Constants.kF, Constants.kTimeoutMs);
		shootRight.config_kP(Constants.kPIDLoopIdx, Constants.kP, Constants.kTimeoutMs);
		shootLeft.config_kF(Constants.kPIDLoopIdx, Constants.kF, Constants.kTimeoutMs);
		shootLeft.config_kP(Constants.kPIDLoopIdx, Constants.kP, Constants.kTimeoutMs);



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stopShot() {
    shootRight.set(0);
  }
  public void startShot() {
    shootRight.set(Constants.Shoot.shotPower);
  }
  public void startVeloShot() {
    shootRight.set(ControlMode.Velocity, 19800);
  }

}
