package frc.robot.commands;

//import java.util.concurrent.CancellationException;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.commands.ShooterSequence;



// NOTE:  Consider using this command inline rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutonomousStraight extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousStraight.
   */

  public AutonomousStraight(DriveTrain m_DriveTrain, Indexer m_Indexer, Shooter m_Shooter){
   // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new PrintCommand("back auto started"),
    new ShooterSequence(m_Shooter, m_Indexer),
    new ShooterSequence(m_Shooter, m_Indexer),
    
    //Stop the shot (it has to be called as a command)
    //m_Shooter.stopShot();
    new PrintCommand("Shooter Stop, Drive back start."),
    
    //Drive backwards
    new SetDriveStraightPower((m_DriveTrain),-.3).withTimeout(1),
    new PrintCommand("stopped driving in auto"), 

    //Wait 20 seconds
    new WaitCommand(20));
    System.out.println("created AutonomousStraight");
    
  }

}