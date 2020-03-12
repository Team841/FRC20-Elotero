/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoAim;
import frc.robot.commands.SetDriveStraightPower;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousShoot extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousShoot.
   */
  
  public AutonomousShoot(DriveTrain m_DriveTrain) {
    // Add your commands in the super() call, e.g.
   //  super(new FooCommand(), new BarCommand());
   // super(new SetDriveStraightPower(m_DriveTrain, -.3).withTimeout(.5), new AutoAim(1);

  }
}
