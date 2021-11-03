package frc.robot.commands;

//import java.util.concurrent.CancellationException;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;


// NOTE:  Consider using this command inline rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousStraight extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousStraight.
   */

  public AutonomousStraight(final DriveTrain m_DriveTrain, final Indexer m_Indexer, final Shooter m_Shooter, Intake m_Intake) {
   // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new PrintCommand("back auto started"),
  
    // 3 ball auto starts here
    new AutoShoot(m_Shooter, m_Indexer),
    new PrintCommand("Three balls shot"),

    //Stop the shot (it has to be called as a command)
    new StopShoot(m_Shooter).withTimeout(.05),
    new PrintCommand("Shooter Stop, Drive back start."),
    
    //Turn right 160
    new TurnRight((m_DriveTrain),.3).withTimeout(.6),
    new PrintCommand("Turned Right 160."),

    // Drive straight
    new SetDriveStraightPower((m_DriveTrain),.3).withTimeout(1.85),
    new PrintCommand("Drove Straight"),

    // Turn right 180
    new TurnRight((m_DriveTrain),.3).withTimeout(.33),
    new PrintCommand("TurnRight slight"),

    // Intake down
    new IntakeOut((m_Intake), Out), 
    
    //Drive Straight
    new SetDriveStraightPower((m_DriveTrain), .3).withTimeout(2.5),
    new PrintCommand("Drive Straight"),
    new WaitCommand(.5),
    //Drive backwards
    new SetDriveStraightPower((m_DriveTrain),-.3).withTimeout(2),
    new PrintCommand("stopped driving in auto"), 
    
    //Turn Right 180
    new TurnRight((m_DriveTrain),.3).withTimeout(.53),
    new PrintCommand("Turn Right."),

    //Wait 20 seconds
    new WaitCommand(20));
    System.out.println("created AutonomousStraight");
    
  }

}