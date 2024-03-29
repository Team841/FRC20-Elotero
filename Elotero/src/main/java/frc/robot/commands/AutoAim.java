/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.C;
import frc.robot.subsystems.DriveTrain;


public class AutoAim extends CommandBase {
  /**
   * Creates a new AutoAim.
   */
  private final DriveTrain m_DriveTrain;
//  public final AutoAim C.java; 
  public AutoAim(DriveTrain subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
  m_DriveTrain = subsystem;
  addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  m_DriveTrain.TurnToTarget();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double target = C.Autoaim.targetDeadband;
    double status = m_DriveTrain.limelightX();
    return ((target>status) & (-target<status));
  }

  

  
  
}
