package frc.robot.subsystems;

import frc.robot.utils.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ElevatorLine extends Subsystem{
    /* intake Talon motor controllers */
    WPI_TalonSRX lift;
    WPI_TalonSRX liftFollower;

    /* set Talon IDs */
    private final int LIFT_TALON_ID = 3;
    private final int LIFTFOLLOWER_TALON_ID = 4;

    /* speed variables */
    double liftSpeed ;
    private final int SPEED_FACTOR = 2;

    private double elevatorSpeed;

    /* tracking variables */
    private boolean hitLow;
    private boolean hitMid;
    private boolean inBetween;
    private String target;
    private String state;

    LineSensor lineSensor;

    public void initDefaultCommand(){
    }

    /* init settings */
    public void liftInit(){
        /* setup elevator motor controllers */
        lift = new WPI_TalonSRX(LIFT_TALON_ID);
        liftFollower = new WPI_TalonSRX(LIFTFOLLOWER_TALON_ID);

        /* factory default values */
        lift.configFactoryDefault();
        liftFollower.configFactoryDefault();

        /* set up followers */
        liftFollower.follow(lift);

        /* set motor controllers to brake vs coast */
        lift.setNeutralMode(NeutralMode.Brake);
        liftFollower.setNeutralMode(NeutralMode.Brake);

        lineSensor = new LineSensor();

        hitLow = false;
        hitMid = false;
        inBetween = false;
        target = "none";
    }

    public void lift(){
        /* target select */
        if(OI.pressedLiftPresetLow()){
            target = "low";
        }
        else if(OI.pressedLiftPresetMid()){
            target = "mid";
        }
        else if(OI.pressedLiftPresetNone()){
            target = "none";
        }

        /* elevator control */
        if(OI.getIntakeTrigger("right") != 0){
            /* detect which line elevator is at */
            if(!hitLow && lineSensor.hitLine()){ // if haven't crossed first line yet and detects line
                hitLow = true;
            }
            else if(hitLow && !lineSensor.hitLine()){ //  if detected first line and went off first line
                inBetween = true;
            }
            else if(hitLow && inBetween && !hitMid){ // if detected second line
                hitMid = true;
                hitLow = false;
                inBetween = false;
            }
            // /* stop commands */
            // if(target.equals("low") && hitLow && !inBetween && !hitMid){ //  if target is low and hit first line
            //     elevatorSpeed = 0;
            // }
            // else if(target.equals("mid") && hitMid){ // if target is mid and hit second line
            //     elevatorSpeed = 0;
            // }
            else{
                elevatorSpeed = -1 * OI.getIntakeTrigger("right");
            }
        }
        else if(OI.getIntakeTrigger("left") != 0){
            /* detect which line elevator is at */
            if(!hitLow && !inBetween && hitMid && !lineSensor.hitLine()){ // if leaves second line
                hitMid = false;
                inBetween = true;
            }
            else if(!hitLow && !hitMid && inBetween && lineSensor.hitLine()){ // if hit first line
                hitLow = true;
                inBetween = false;
            }
            /* stop commands */
            if(target.equals("low") && hitLow){
                elevatorSpeed = 0;
            }
            else{
                elevatorSpeed = 1 * OI.getIntakeTrigger("left");
            }
        }
        else{
            elevatorSpeed = 0;
        }

        /* deadband */
        if (Math.abs(elevatorSpeed) < 0.20) {
            elevatorSpeed = 0;
        } 
                    /* stop commands */
                    if(target.equals("low") && hitLow && !inBetween && !hitMid){ //  if target is low and hit first line
                        elevatorSpeed = 0;
                    }
                    else if(target.equals("mid") && hitMid){ // if target is mid and hit second line
                        elevatorSpeed = 0;
                    }
                    /* stop commands */
            if(target.equals("low") && hitLow){
                elevatorSpeed = 0;
            }

        /* set elevator */
        lift.set(ControlMode.PercentOutput, elevatorSpeed / SPEED_FACTOR);

        /* dashboard prints */
        if(hitLow && !inBetween && !hitMid){
            state = "low";
        }
        else if(hitLow && inBetween && !hitMid){
            state = "in between";
        }
        else if(!hitLow && !inBetween && hitMid){
            state = "mid";
        }
        else{
            state = "intake";
        }
        
        SmartDashboard.putString("DB/String 0", "target: " + target);
        SmartDashboard.putString("DB/String 1", "sensor Val: " + lineSensor.getSensorValue());
        SmartDashboard.putString("DB/String 2", "line?: " + lineSensor.hitLine());
    }

    /* We're using this one */
    public void liftSimple(){
        /* target select */
        if(OI.pressedLiftPresetLow()){
            target = "low";
        }
        // else if(OI.pressedLiftPresetMid()){
        //     target = "mid";
        // }
        else if(OI.pressedLiftPresetNone()){
            target = "none";
        }

        /* elevator control */
        if(OI.getIntakeTrigger("right") != 0){
            if(target.equals("low") && lineSensor.hitLine()){
                elevatorSpeed = 0; // try comment out this to see if skipping
            }
            else{
                elevatorSpeed = -1 * OI.getIntakeTrigger("right");
            }
        }
        else if(OI.getIntakeTrigger("left") != 0){
            if(target.equals("low") && lineSensor.hitLine()){
                elevatorSpeed = 0;
            }
            else{
                elevatorSpeed = 1 * OI.getIntakeTrigger("left");
            }
        }
        else{
            elevatorSpeed = 0;
        }

        /* deadband */
        if (Math.abs(elevatorSpeed) < 0.20) {
            elevatorSpeed = 0;
        } 

        /* set elevator */
        lift.set(ControlMode.PercentOutput, elevatorSpeed / SPEED_FACTOR);

        /* dashboard prints */
        if(hitLow && !inBetween && !hitMid){
            state = "low";
        }
        else if(hitLow && inBetween && !hitMid){
            state = "in between";
        }
        else if(!hitLow && !inBetween && hitMid){
            state = "mid";
        }
        else{
            state = "intake";
        }
        
        SmartDashboard.putString("DB/String 0", "target: " + target);
        SmartDashboard.putString("DB/String 1", "sensor Val: " + lineSensor.getSensorValue());
        SmartDashboard.putString("DB/String 2", "line?: " + lineSensor.hitLine());
    }

    public void liftNew(){
        /* target select */
        if(OI.pressedLiftPresetLow()){
            target = "low";
        }
        else if(OI.pressedLiftPresetNone()){
            target = "none";
        }

        /* elevator control */
        if(OI.getIntakeTrigger("right") != 0){
            if(target.equals("low") && lineSensor.hitLine()){
                elevatorSpeed = 0; // try comment out this to see if skipping
            }
            else{
                elevatorSpeed = -1 * OI.getIntakeTrigger("right");
            }
        }
        else if(OI.getIntakeTrigger("left") != 0){
            if(target.equals("low") && lineSensor.hitLine()){
                elevatorSpeed = 0;
            }
            else{
                elevatorSpeed = 1 * OI.getIntakeTrigger("left");
            }
        }
        else{
            elevatorSpeed = 0;
        }

        /* deadband */
        if (Math.abs(elevatorSpeed) < 0.20) {
            elevatorSpeed = 0;
        } 

        /* set elevator */
        lift.set(ControlMode.PercentOutput, elevatorSpeed / SPEED_FACTOR);

        /* dashboard prints */
        if(hitLow && !inBetween && !hitMid){
            state = "low";
        }
        else if(hitLow && inBetween && !hitMid){
            state = "in between";
        }
        else if(!hitLow && !inBetween && hitMid){
            state = "mid";
        }
        else{
            state = "intake";
        }
        
        SmartDashboard.putString("DB/String 0", "target: " + target);
        SmartDashboard.putString("DB/String 1", "sensor Val: " + lineSensor.getSensorValue());
        SmartDashboard.putString("DB/String 2", "line?: " + lineSensor.hitLine());
    }

    public void liftComplex(){
        /* target select */
        if(OI.pressedLiftPresetLow()){
            target = "low";
        }
        else if(OI.pressedLiftPresetMid()){
            target = "mid";
        }
        else if(OI.pressedLiftPresetNone()){
            target = "none";
        }

        if(hitLow == false && hitMid == false && lineSensor.hitLine()){
            hitLow = true;
            hitMid = false;
        }
        else if(hitLow == true && hitMid == false && lineSensor.hitLine()){
            hitMid = true;
            hitLow = false;
        }

        /* elevator control */
        if(OI.getIntakeTrigger("right") != 0){
            if(target.equals("low") && hitLow == true){
                elevatorSpeed = 0;
            }
            else if(target.equals("mid") && hitMid == true){
                elevatorSpeed = 0;
            }
            else{
                elevatorSpeed = -1 * OI.getIntakeTrigger("right");
            }
        }
        else if(OI.getIntakeTrigger("left") != 0){
            if(target.equals("low") && hitLow == true){
                elevatorSpeed = 0;
            }
            else if(target.equals("mid") && hitMid == true){
                elevatorSpeed = 0;
            }
            else{
                elevatorSpeed = 1 * OI.getIntakeTrigger("left");
            }
        }
        else{
            elevatorSpeed = 0;
        }

        /* deadband */
        if (Math.abs(elevatorSpeed) < 0.20) {
            elevatorSpeed = 0;
        } 

        /* set elevator */
        lift.set(ControlMode.PercentOutput, elevatorSpeed / SPEED_FACTOR);

        /* dashboard prints */
        if(hitLow && !inBetween && !hitMid){
            state = "low";
        }
        else if(hitLow && inBetween && !hitMid){
            state = "in between";
        }
        else if(!hitLow && !inBetween && hitMid){
            state = "mid";
        }
        else{
            state = "intake";
        }
        
        SmartDashboard.putString("DB/String 0", "target: " + target);
        SmartDashboard.putString("DB/String 1", "sensor Val: " + lineSensor.getSensorValue());
        SmartDashboard.putString("DB/String 2", "line?: " + lineSensor.hitLine());
    }
}