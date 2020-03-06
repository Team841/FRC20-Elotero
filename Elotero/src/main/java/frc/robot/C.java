/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class C {

    public static final class OI{
        public static final int driverPort = 0; //controller USB port 0
        public static final int codriverPort = 1; //controller USB port 1
        public static final int kRB = 6; //button map
		public static final int kLT = 8;//button map
    }

    public static final class CANid{
        public static final int driveRight1 = 3;
        public static final int driveRight2 = 4; 
        public static final int driveLeft1 = 1;
        public static final int driveLeft2 = 2;
        public static final int shootLeft = 6;
        public static final int shootRight = 5;
        public static final int storage = 12; //SparkMAX needs to get programmed to match this
        public static final int index = 11; //SparkMAX needs to get programmed to match this
        public static final int intake = 10;
    }

    public static final class Drive{
        
        //Physical setup of the drive

        //Current limit setup based on legacy SRX interface //TODO: Update current limit for FX
//      public static final double contCurrentLimit = 45; //continuous current limit (45A default) 
//      public static final double currentLimitDuration = 0; //always be current limited (0sec default)
//      public static final double peakCurrentLimit = 0; //do not allow a peak above the continuous limit (peak = continuous when set below continous)

        //Tuning the Chezy Drive - deadband, sensitivity & tolerancing values on raw joystick inputs
        public static final double throttleDeadband = 0.02; 
        public static final double wheelDeadband = 0.02;	
         public static final double sensitivityHigh = 0.85;	
        public static final double sensitivityLow = 0.75;
        public static final double centervalue = 140;
        public static final double tolerance = 10;

    }

	public static final class Shoot{
        public static final double shotPower = 0.85;
        public static final double velocitySetPoint = 12000;
        public static final double kP = 0.01;
        public static final double kI = 0;
        public static final double kD = 5;
        public static final double kF = 0.045;
        public static final int kPIDLoopIdx = 0;
        public static final int kTimeoutMs = 30;

    }

	
	public static final class Storaged{
        public static final double StorePower = 0.6;
        public static final int SensorChannel = 9;
    
    }
    public static final class Index{
        public static final double IndexPower = 0.3;
        public static final int SensorChannel = 8;
    }
}
