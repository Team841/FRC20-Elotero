package frc.robot.commands;

//import java.util.concurrent.CancellationException;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.C;


// NOTE:  Consider using this command inline rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto6BallHold extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousStraight.
   */

  public Auto6BallHold(final DriveTrain m_DriveTrain, final Indexer m_Indexer, final Shooter m_Shooter, Intake m_Intake) {
   // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new PrintCommand("back auto started"),
  
    // Auto aim at goal
   //new AutoAim(m_DriveTrain),
   //new PrintCommand("Aim Goal"),

    // 3 ball auto starts here
    new AutoShoot(m_Shooter, m_Indexer),
    new PrintCommand("Three balls shot"),

    //Turn right 160
    new TurnRight((m_DriveTrain),.3).withTimeout(.6),
    new PrintCommand("Turned Right 160."),

     // Intake down
    new IntakeOut((m_Intake), true).withTimeout(.05),
     // Intake Roller on
    new RollerOn((m_Intake), true).withTimeout(.05),

    // Drive straight
    new SetDriveStraightPower((m_DriveTrain),.25).withTimeout(1.75),
    new PrintCommand("Drove Straight"),

    // Turn right 20
    new TurnRight((m_DriveTrain),.4).withTimeout(.35),
    new PrintCommand("TurnRight slight"),

    //Drive Straight
    new SetDriveStraightPower((m_DriveTrain), .3).withTimeout(2.5),
    new PrintCommand("Drive Straight"),
    new WaitCommand(.5),

    //Intake up
    new IntakeOut((m_Intake), false).withTimeout(.05),

    // Roller off
    new RollerOff((m_Intake), true).withTimeout(.05),

    //Drive backwards
    new SetDriveStraightPower((m_DriveTrain),-.3).withTimeout(2),
    new PrintCommand("Stopped driving in auto"), 
    
    //Turn Right 180
    new TurnRight((m_DriveTrain),.3).withTimeout(.65),
    new PrintCommand("Turn Right."),

    new WaitCommand(20));
    System.out.println("created AutonomousStraight");
    
  }

}