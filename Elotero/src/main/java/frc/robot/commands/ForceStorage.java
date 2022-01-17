/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.C;
import frc.robot.subsystems.Storage;

public class ForceStorage extends CommandBase {
  /**
   * Creates a new AutoStorage.
   */
  private final Storage m_storage;

   //private final HatchSubsystem m_hatchSubsystem;
  public ForceStorage( Storage subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_storage = subsystem;
   addRequirements(m_storage);
  }

 //   public void ForceStorage() {
 //       m_storage.startStore()
        

     
    }
  