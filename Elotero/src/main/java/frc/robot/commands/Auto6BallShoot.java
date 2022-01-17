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
public class Auto6BallShoot extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousStraight.
   */

  public Auto6BallShoot(final DriveTrain m_DriveTrain, final Indexer m_Indexer, final Shooter m_Shooter, Intake m_Intake) {
   // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new PrintCommand("back auto started"),
  
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
    new SetDriveStraightPower((m_DriveTrain),.285).withTimeout(1.85),
    new PrintCommand("Drove Straight"),

    // Turn right slightly (20 degrees)
    new TurnRight((m_DriveTrain),.3).withTimeout(.2),
    new PrintCommand("TurnRight slight"),

    //Drive Straight
    new SetDriveStraightPower((m_DriveTrain), .3).withTimeout(2.33),
    new PrintCommand("Drive Straight"),
    new WaitCommand(.5),

    //Intake up
    new IntakeOut((m_Intake), false).withTimeout(.05),
    // ROller off
    new RollerOff((m_Intake), true).withTimeout(.05),

    //Drive backwards
    new SetDriveStraightPower((m_DriveTrain),-.3).withTimeout(1.8),
    new PrintCommand("stopped driving in auto"), 
    
    //Turn Right 180
    new TurnRight((m_DriveTrain),.3).withTimeout(.53),
    new PrintCommand("Turn Right."),

    //Shoot 3 balls
    new AutoShoot(m_Shooter, m_Indexer),
    new PrintCommand("Three balls shot"),

    //*Optional code// Turn


    //Wait 20 seconds
    new WaitCommand(20));
    System.out.println("created AutonomousStraight");
    
  }

}