/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeOut extends CommandBase {
  /**
   * Creates a new IntakeOut.
   */
  private final Intake m_Intake;
  private final boolean m_Out;
  public IntakeOut(Intake subsystem,boolean Out) {
    // Use addRequirements() here to declare subsystem dependencies.
  m_Intake = subsystem;
  m_Out = Out;
   addRequirements(m_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_Out){
      m_Intake.elbowOut();
    }else {
      m_Intake.elbowIn();
    }
  
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
  }
}
