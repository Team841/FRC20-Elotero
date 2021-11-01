/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.C.*;
import frc.robot.commands.AutoIndex;
import frc.robot.commands.AutoStorage;
import frc.robot.commands.AutonomousStraight;
import frc.robot.commands.ClimbSequence;
import frc.robot.commands.ExtendOut;
import frc.robot.commands.ExtendRectract;
import frc.robot.commands.FlipOut;
import frc.robot.commands.IntakePowerCell;
import frc.robot.commands.IntakeSpitOut;
import frc.robot.commands.RectractIntake;
import frc.robot.commands.ShooterSequence;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
  private final Shooter m_shooter = new Shooter();
  private final Indexer m_indexer = new Indexer();
  private final Storage m_storage = new Storage();
  private final Intake m_intake = new Intake();
  private final Climber m_Climber = new Climber();
//Compresser defined here\ Compresser definia aqui
  private final Compressor m_Compresser = new Compressor(0);
  // OI defined here
private final Joystick m_driverCtrl = new Joystick(C.OI.driverPort);
private final Joystick m_codriverCtrl = new Joystick(C.OI.codriverPort);

private SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    m_Compresser.setClosedLoopControl(true);

    // Put stuff on Shuffleboard/SmartDashboard
    chooser.setDefaultOption("Autonomous Straight", new AutonomousStraight(m_driveTrain, m_indexer, m_shooter));
    //chooser.addOption("Auto 2", new ...);
    SmartDashboard.putData("Auto mode", chooser);

      //stuff
    // Configure the button bindings
    configureButtonBindings();
    // Assign default commands (Formerly located in Subsystems)
    m_driveTrain.setDefaultCommand(
      new RunCommand(() -> m_driveTrain.Drive(m_driverCtrl),m_driveTrain)
      );
    
    //m_storage.setDefaultCommand(
      //new RunCommand(()-> m_driveTrain.Drive(m_driveCtrl),m_driveTrain));
    m_storage.setDefaultCommand(  new AutoStorage(m_storage) );
    m_indexer.setDefaultCommand(new AutoIndex(m_indexer));
    //
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //DRIVER
    //Quick turn
    final JoystickButton qT = new JoystickButton(m_driverCtrl, C.OI.kRB);
    qT.whenPressed(new InstantCommand(m_driveTrain::setQuickTurn, m_driveTrain));
    qT.whenReleased(new InstantCommand(m_driveTrain::resetQuickTurn, m_driveTrain));

     //AutoAim
     final JoystickButton turnTarget = new JoystickButton(m_driverCtrl, C.OI.kRT);
     turnTarget.whileHeld(new InstantCommand(m_driveTrain::TurnToTarget, m_driveTrain));

    
    //Shoot
    final JoystickButton flywheel = new JoystickButton(m_driverCtrl, C.OI.kLT);
    flywheel.whileHeld(new ShooterSequence(m_shooter, m_indexer));
    flywheel.whenReleased(new InstantCommand(m_shooter::stopShot, m_shooter));

   //CODRIVER

    final JoystickButton LimeLightLed = new JoystickButton(m_codriverCtrl, C.OI.kX);
    LimeLightLed.whenPressed(new InstantCommand(m_driveTrain::LEDon, m_driveTrain));
    LimeLightLed.whenReleased(new InstantCommand(m_driveTrain::LEDoff, m_driveTrain));

    //Activate the Intake (suck IN) / Activar Intake hacia adentro
    final JoystickButton intakeControl = new JoystickButton(m_codriverCtrl,C.OI.kB);
    intakeControl.whenPressed(new IntakePowerCell(m_intake));
    intakeControl.whenReleased(new RectractIntake(m_intake));

    //Intake OUT / Sacar intake afuera
    final JoystickButton intakeSpitOut = new JoystickButton(m_codriverCtrl,C.OI.kA);
    intakeSpitOut.whenPressed(new IntakeSpitOut(m_intake));
    intakeSpitOut.whenReleased(new RectractIntake(m_intake));
    //Reach the climber (extend it) / Desplegar el gancho
    final JoystickButton FlipControl = new JoystickButton(m_codriverCtrl, C.OI.kLT);
    FlipControl.whenPressed(new ClimbSequence(m_intake,m_Climber));

    final JoystickButton ExtendControl = new JoystickButton(m_codriverCtrl, C.OI.kLB);
    ExtendControl.whenPressed(new ExtendOut(m_Climber));
    ExtendControl.whenReleased(new ExtendRectract(m_Climber));
    }
  


  /**   
     * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

 

    // An ExampleCommand will run in autonomous
//    return m_autoCommand;
    //Command m_command = new AutonomousStraight(m_driveTrain);
    //return  m_command;
    return new AutonomousStraight(m_driveTrain, m_indexer, m_shooter); // THIS ONE IS ONLY FOR TESTING, IT GOES STRAIGHT
    //chooser.
    //return chooser.getSelected(); //TODO, THIS ONE IS THE ONE!
  }
}
