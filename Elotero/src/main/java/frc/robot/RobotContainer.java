/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.*;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here...
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Shooter m_flywheel = new Shooter();
  // OI defined here
private final Joystick m_driverCtrl = new Joystick(C.OI.driverPort);
private final Joystick m_codriverCtrl = new Joystick(C.OI.codriverPort);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Put stuff on Shuffleboard/SmartDashboard

      //stuff
    // Configure the button bindings
    configureButtonBindings();
    // Assign default commands (Formerly located in Subsystems)
    m_driveTrain.setDefaultCommand(
      new RunCommand(() -> m_driveTrain.Drive(m_driverCtrl),m_driveTrain)
      );
    //
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
//Create a quickturn (qt) button and assign commands on press & release
    final JoystickButton qT = new JoystickButton(m_driverCtrl, C.OI.quickTurn);
    qT.whenPressed(new InstantCommand(m_driveTrain::setQuickTurn, m_driveTrain));
    qT.whenReleased(new InstantCommand(m_driveTrain::resetQuickTurn, m_driveTrain));


    final JoystickButton flywheel = new JoystickButton(m_codriverCtrl, C.OI.flywheel);
    flywheel.whenPressed(new InstantCommand(m_flywheel::startShot, m_flywheel));
    flywheel.whenReleased(new InstantCommand(m_flywheel::stopShot, m_flywheel));
  }
  


  /**   
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    return null; //TODO Auto command is broken right now, remove this when ready to run

    // An ExampleCommand will run in autonomous
//    return m_autoCommand;
  }
}
