/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ClimbSequence extends SequentialCommandGroup {
  /**
   * Creates a new ClimbSequence.
   */
  
  public ClimbSequence(Intake m_Intake,Climber m_Climber) {

    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new IntakeOut(m_Intake, true).withTimeout(1),new FlipOut(m_Climber).withTimeout(1),new WaitCommand(2.5), new IntakeOut((m_Intake), false).withTimeout(1) );

  }
}
