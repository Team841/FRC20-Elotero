//This file is heavily modified from RobotBuilder to resemble ExampleSubsystem.java

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.C;

/**
 *
 */
public class DriveTrain extends SubsystemBase {

    private final WPI_TalonFX left1 = new WPI_TalonFX(C.CANid.driveLeft1);
    private final WPI_TalonFX left2 = new WPI_TalonFX(C.CANid.driveLeft2);
    private final WPI_TalonFX right1 = new WPI_TalonFX(C.CANid.driveRight1);
    private final WPI_TalonFX right2 = new WPI_TalonFX(C.CANid.driveRight2);
    private double x = 0;

    private double a = 0;
    private NetworkTable table;

    private NetworkTableEntry tx;

    // NetworkTableEntry ty;

    // NetworkTableEntry ta;

    private NetworkTableEntry ta;

    // Constructor creates & sets up a new DriveTrain
public DriveTrain() {

    table = NetworkTableInstance.getDefault().getTable("limelight");
    //Controller housekeeping in the constructor
    left1.configFactoryDefault();
    right1.configFactoryDefault();
    left2.configFactoryDefault();
    right2.configFactoryDefault();
    left1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
    left2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
    right1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));
    right2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));

    //Set #2 controllers to follow #1 in both drives
    left2.follow(left1);
    right2.follow(right1);

    //Set current limits on the supply side of the TalonFX
//CTRE hasn't released documentation on this yet; TBD whether it's necessary anyway. 

//Additional drive housekeeping - such as setting encoder distances per pulse - go here

}


    @Override
    public void periodic() {
        // Put code here to be run every loop: vision updates, odometry updates, etc
        // Normal drive code doesn't go here, drive is a method below
     
            
    
            // Update values of the table
    
           
    
            tx = table.getEntry("tx");
    
            ta = table.getEntry("ta");

           
    
    
    
        //    NetworkTableEntry ty = table.getEntry("ty");
    
          //  NetworkTableEntry ta = table.getEntry("ta");
    
                   
    
             // read  values periodically
    
            x = tx.getDouble(0.0);
            //this.y = ty.getDouble(0.0);
            a = ta.getDouble(0.0);
    

           // this.area = ta.getDouble(0.0);
    
            
    
    
            //post to smart dashboard periodically
    
            SmartDashboard.putNumber("LimelightX", x);
    
            //SmartDashboard.putNumber("LimelightY", y);
    

            //SmartDashboard.putNumber("LimelightArea", area);
    
            SmartDashboard.putNumber("LimelightA", a);
    
            // Note quickturn and shift is taken care of with buttons in OI.
    
            // Put code here to be run every loop
    
    
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

//These variables are used in ChezyDrive, the Halo-type (similar to Arcade) drive we borrow from team 254 (Uses an additional control term that compensates for robot momentum)
private boolean isHighGear = false; //haven't used highgear for a few years, but no reason to delete
private double oldWheel = 0.0; //accumulator to handle inertia
private double quickStopAccumulator = 0; 
private boolean isQuickTurn = false;

    public void setQuickTurn() {
        isQuickTurn = true;
    }
    public void resetQuickTurn() {
        isQuickTurn = false;
    }
 /**
     * This method does the Halo drive and is the slim down version of the cheesy
     * poofs drive style. 
     * QuickTurn is handled inside this method.
     * It has the capability to auto shift if uncommented.
     * 
     * @param stick
     */
    public void cheesyDrive(Joystick stick) {


        double wheelNonLinearity;
        double wheel = handleDeadband(getWheel(stick), C.Drive.wheelDeadband); // double
                                                                         // wheel
                                                                         // =
                                                                         // handleDeadband(controlBoard.rightStick.getX(),
                                                                         // wheelDeadband);
        double throttle = -handleDeadband(getThrottle(stick), C.Drive.throttleDeadband);
        double negInertia = wheel - oldWheel;
        /*
         * if(getAverageSpeed()> 2000){ SetHighGear(); } else if (getAverageSpeed() <
         * 1500){ SetLowGear(); } //Autoshifting code based on a speed threshold from encoder data (not implemented)
         */

        oldWheel = wheel;
        if (isHighGear) {
            wheelNonLinearity = 0.6;
            // Apply a sin function that's scaled to make it feel better. WPILib does similar thing by squaring inputs.
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        } else {
            wheelNonLinearity = 0.5;
            // Apply a sin function that's scaled to make it feel better. WPILib does a similar thing by squaring inputs.
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        }

        double leftPwm, rightPwm, overPower;
        double sensitivity = 1.7;
        double angularPower;
        double linearPower;
        // Negative inertia!
        double negInertiaAccumulator = 0.0;
        double negInertiaScalar;

        if (isHighGear) {
            negInertiaScalar = 5.0;
            sensitivity = C.Drive.sensitivityHigh; // sensitivity =
                                             // C.sensitivityHigh.getDouble();
        } else {
            if (wheel * negInertia > 0) {
                negInertiaScalar = 2.5;
            } else {
                if (Math.abs(wheel) > 0.65) {
                    negInertiaScalar = 5.0;
                } else {
                    negInertiaScalar = 3.0;
                }
            }
            sensitivity = C.Drive.sensitivityLow; // sensitivity =
                                            // C.sensitivityLow.getDouble();
            if (Math.abs(throttle) > 0.1) {
                // sensitivity = 1.0 - (1.0 - sensitivity) / Math.abs(throttle);
            }
        }

        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;
        wheel = wheel + negInertiaAccumulator;
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }
        linearPower = throttle;
        // Quickturn!
        if (isQuickTurn) {
            if (Math.abs(linearPower) < 0.2) {
                double alpha = 0.1;
                quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * limit(wheel, 1.0) * 5;
            }
            overPower = 1.0;
            if (isHighGear) {
                sensitivity = .005;
            } else {
                sensitivity = 0.005;

            }
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }
        rightPwm = leftPwm = linearPower;
        leftPwm -= angularPower; //Flipped in 2020 for flipped gearboxes
        rightPwm += angularPower; //Flipped in 2020 for flipped gearboxes
        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }
        SetLeftRight(leftPwm, -rightPwm);

    }

    /**
     * This method takes two params (left and right speed) and must be opposite to
     * go straight (right side pre-inverted). It is set up for up to three motors, but we use Smart Motors to
     * follow the lead right now so motors 2 & 3 are commented out. 
     * 
     * @param stick
     * @return yAxis
     */
    public void SetLeftRight(double LPower, double RPower) {
        right1.set(RPower * 1);
        //right2.set(RPower); 
        //right3.set(RPower);
        left1.set(LPower * 1);
        //left2.set(LPower);
        //left3.set(LPower);

    }

    /**
     * This method takes in the object joystick and returns the y axis value to the
     * left most side of the gamepad.
     * 
     * @param stick
     * @return yAxis
     */
    public double getYAxisLeftSide(Joystick stick) {
        return stick.getY();
    }

    /**
     * This method takes inthe ojbect joystick and returns the y axis value to the
     * right most siide of the gamepad.
     * 
     * @param stick
     * @return
     */
    public double getYAxisRightSide(Joystick stick) {
        return stick.getThrottle();
    }

    /**
     * If the value is too small make it zero
     * 
     * @param val
     * @param deadband
     * @return value with deadband
     */
    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }

    public void Drive(Joystick stick) {
        cheesyDrive(stick);
    }

    /**
     * If the value is too large limit it.
     * 
     * @param v
     * @param limit
     * @return value with a max limit
     */

    public double limit(double v, double limit) {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }

    /**
     * This method takes in object joystick and returns the yaxis value of the left
     * most side of the gamepad.
     * 
     * @param stick
     * @return yAxis
     */
    public double getThrottle(Joystick stick) {
        return stick.getY();
    }

    /**
     * This method takes in the object joystick and returns the x axis value to the
     * right most side of the gamepad.
     * 
     * @param stick
     * @return xAxis
     */
    public double getWheel(Joystick stick) {
        return stick.getZ();
    }

    public void TurnToTarget() {



        SmartDashboard.putNumber("LimelightX", x);

        SmartDashboard.putNumber("LimelightA", a);


        // If the area is greather than 0, center left or right.
        if (a > 0){

            if (x >= 3){

                SetLeftRight(-.1,-.1); //TODO: is this the correct direction?

            }

            else if (x <= -3){

                SetLeftRight(.1,.1); //TODO: is this the correct direction?

            }

            else {
                SetLeftRight(0, 0);
                //if pointed at target, do nothing

            }

        }


    }

    public void LEDon(){
      // NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
      table.getEntry("ledMode").setNumber(3);//putNumber("ledMode",3);
    }
    public void LEDoff(){
      //  NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
      table.getEntry("ledMode").setNumber(1);//putNumber("ledMode",1);
    }
    
}

