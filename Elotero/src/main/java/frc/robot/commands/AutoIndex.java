/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.subsystems.Indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoIndex extends CommandBase {
  /**
   * Creates a new AutoIndex.
   */

   private final Indexer m_indexer;
  public AutoIndex(Indexer subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_indexer = subsystem;
    addRequirements(m_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_indexer.isBallPresent()){
      m_indexer.stopIndex();
    }
    else{
      m_indexer.startIndex();
    }
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
