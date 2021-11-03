/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoShoot extends SequentialCommandGroup {
  /**
   * Creates a new AutoShoot.
   */
  public AutoShoot(Shooter m_Shooter,Indexer m_Indexer) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new Shoot(m_Shooter).withTimeout(.5), 
    new ForceIndex(m_Indexer).withTimeout(.2),
    new StopIndex(m_Indexer).withTimeout(.1),
    new ForceIndex(m_Indexer).withTimeout(.3),
    new StopIndex(m_Indexer).withTimeout(.05),
    new ForceIndex(m_Indexer).withTimeout(.4),
    new StopIndex(m_Indexer).withTimeout(.05));
  }
}
