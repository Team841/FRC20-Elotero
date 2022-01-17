/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.C;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /**
   * Creates a new Shoot.
   */
  
  private final Shooter m_Shooter;
  public Shoot(Shooter subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Shooter = subsystem;
    addRequirements(m_Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Shooter.startShot();
 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    //if(m_Shooter.getSpeed() > (0.90*C.Shoot.velocitySetPoint)){
    //  return true;
   // }
    //else{
    //  return false;

    //}
    //return  m_Shooter.getSpeed() > (0.95*C.Shoot.velocitySetPoint)  ;
  }
}
