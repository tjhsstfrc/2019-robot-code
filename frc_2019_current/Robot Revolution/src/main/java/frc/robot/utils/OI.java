package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	private static Joystick driveController = new Joystick(1); // driver controller
    private static Joystick intakeController = new Joystick(2); // intake controller
    
    /* mappings */
    /* [KEY]
     * [BUTTON]         [NUMBER]
     * A                1
     * B                2
     * X                3
     * Y                4
     * Left Trigger     5
     * Right Trigger    6       */

    private static final int INTAKE = 1; // intake button
    private static final int OUTTAKE = 5; // intake button
    private static final int REVERSE_OUTTAKE = 3; // intake button
    private static final int LOW_PRESET = 6; // intake button
    private static final int MID_PRESET = 2; // intake button
    //private static final int RESET_PRESET = 6; // intake button
    private static final int LEFT_DRIVE = 1; // driver joystick axis
    private static final int RIGHT_DRIVE = 5; // driver joystick axis
    private static final int BACK_STRAIGHT = 2; // driver joystick axis
    private static final int FRONT_STRAIGHT = 3; // driver joystick axis
    private static final int LOWER_ELEVATOR = 2; // intake joystick axis
    private static final int RAISE_ELEVATOR = 3; // intake joystick axis
    private static final int FULL_BACK = 5;
    
    /* ===== driver controls ===== */
    public static double getDriveStick(String side){
        double driveValue;
        if(side.equals("left")){
            driveValue = driveController.getRawAxis(LEFT_DRIVE);
        }
        else if(side.equals("right")){
            driveValue = driveController.getRawAxis(RIGHT_DRIVE);
        }
        else{
            driveValue = 0;
        }
        return driveValue;
    }

    public static double getDriveTrigger(String side){
        double triggerValue;
        if(side.equals("left")){
            triggerValue = driveController.getRawAxis(BACK_STRAIGHT);
        }
        else if(side.equals("right")){
            triggerValue = driveController.getRawAxis(FRONT_STRAIGHT);
        }
        else{
            triggerValue = 0;
        }
        return triggerValue;
    }

    public static boolean pressedFullBack(){
        return driveController.getRawButton(FULL_BACK);
    }

    /* ===== intake controls ===== */
    public static double getIntakeTrigger(String side){
        double triggerValue;
        if(side.equals("left")){
            triggerValue = intakeController.getRawAxis(LOWER_ELEVATOR);
        }
        else if(side.equals("right")){
            triggerValue = intakeController.getRawAxis(RAISE_ELEVATOR);
        }
        else{
            triggerValue = 0;
        }
        return triggerValue;
    }

    public static boolean pressedIntake(){
        return intakeController.getRawButton(INTAKE);
    } 

    public static boolean pressedOuttake(){
        return intakeController.getRawButton(OUTTAKE);
    }

    public static boolean pressedReverse(){
        return intakeController.getRawButton(REVERSE_OUTTAKE);
    }

    public static boolean pressedLiftPresetLow(){
        return intakeController.getRawButton(LOW_PRESET);
    }

    public static boolean pressedLiftPresetMid(){
        return intakeController.getRawButton(MID_PRESET);
    }

    // public static boolean pressedLiftPresetNone(){
    //     return intakeController.getRawButton(RESET_PRESET);
    // }
}