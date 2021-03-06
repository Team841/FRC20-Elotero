/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousStraight extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousStraight.
   */
  public AutonomousStraight(DriveTrain m_DriveTrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new PrintCommand("running auto straight"),new SetDriveStraightPower((m_DriveTrain),.3).withTimeout(1),new PrintCommand("stopped driving in auto"), new WaitCommand(20));
    System.out.println("created AutonomousStraight");
  }
}
