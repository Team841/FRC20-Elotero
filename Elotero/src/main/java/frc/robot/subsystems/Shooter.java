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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.C;

public class Shooter extends SubsystemBase {
  private final WPI_TalonFX shootRightTalonFX = new WPI_TalonFX(C.CANid.shootRight);
  private final WPI_TalonFX shootLeftTalonFX = new WPI_TalonFX(C.CANid.shootLeft);
  /**
   * Creates a new ExampleSubsystem.
   */
  public Shooter() {


    shootRightTalonFX.configFactoryDefault();
    shootLeftTalonFX.configFactoryDefault();



    shootLeftTalonFX.follow(shootRightTalonFX);
    shootLeftTalonFX.setInverted(true);

    shootRightTalonFX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,

    C.Shoot.kPIDLoopIdx, 

    C.Shoot.kTimeoutMs);
   
    shootRightTalonFX.setSensorPhase(true);



		/* Config the peak and nominal outputs */

		shootRightTalonFX.configNominalOutputForward(0,  C.Shoot.kTimeoutMs);

		shootRightTalonFX.configNominalOutputReverse(0,  C.Shoot.kTimeoutMs);

		shootRightTalonFX.configPeakOutputForward(1,  C.Shoot.kTimeoutMs);

		shootRightTalonFX.configPeakOutputReverse(-1,  C.Shoot.kTimeoutMs);



		/* Config the Velocity closed loop gains in slot0 */

		shootRightTalonFX.config_kF( C.Shoot.kPIDLoopIdx,  C.Shoot.kF,  C.Shoot.kTimeoutMs);

		shootRightTalonFX.config_kP( C.Shoot.kPIDLoopIdx,  C.Shoot.kP,  C.Shoot.kTimeoutMs);

		shootRightTalonFX.config_kI( C.Shoot.kPIDLoopIdx,  C.Shoot.kI,  C.Shoot.kTimeoutMs);

		shootRightTalonFX.config_kD( C.Shoot.kPIDLoopIdx,  C.Shoot.kD,  C.Shoot.kTimeoutMs);
    
    SmartDashboard.putNumber("velocity",C.Shoot.velocitySetPoint);
    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public void stopShot(){
  shootRightTalonFX.set(ControlMode.PercentOutput,0);


}
public void startShot(){
 
  //shootRightTalonFX.set(SmartDashboard.getNumber("ShotPower", C.Shoot.shotPower));
  shootRightTalonFX.set(ControlMode.Velocity, SmartDashboard.getNumber("velocity",C.Shoot.velocitySetPoint ));

}
public int getSpeed(){
  return shootRightTalonFX.getSelectedSensorVelocity(C.Shoot.kPIDLoopIdx);
}
}
