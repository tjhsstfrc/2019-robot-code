/* Callable methods
 * ================
 * liftInit()
 * lift() */

package frc.robot.subsystems;

import frc.robot.utils.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

public class Elevator extends Subsystem{
    /* intake Talon motor controllers */
    WPI_TalonSRX lift;
    WPI_TalonSRX liftFollower;

    /* set Talon IDs */
    private final int LIFT_TALON_ID = 6;
    private final int LIFTFOLLOWER_TALON_ID = 7;

    /* speed variables */
    double liftSpeed ;
    private final int SPEED_FACTOR = 1; // actual lisft 
    private final double LIFT_SPEED = .75;

    /* lift position presets */
    private final int LOW_POSITION_PRESET = 2000;
    private final int MID_POSITION_PRESET = 10000;

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

        /*
        * flip value so that motor output and sensor velocity are the same polarity. Do
        * this before closed-looping
        */
        lift.setSensorPhase(true); // <<<<<< Adjust this

        /* set position value for encoder at 0 to start */
        lift.setSelectedSensorPosition(0);
    }

    public void lift(){
        // if(OI.pressedLiftPresetLow()){
        //     if(lift.getSelectedSensorPosition() > LOW_POSITION_PRESET){
        //         lift.set(-1 * LIFT_SPEED);
        //     }
        //     else{
        //         lift.set(0);
        //     }
        // }
        // else if(OI.pressedLiftPresetMid()){
        //     if(lift.getSelectedSensorPosition() < MID_POSITION_PRESET){
        //             lift.set(1 * LIFT_SPEED);
        //     }
        //     else{
        //         lift.set(0);
        //     }
        // }
        if(OI.getIntakeTrigger("left") != 0){
            lift.set(-1 * OI.getIntakeTrigger("left") / SPEED_FACTOR);
        }
        else if(OI.getIntakeTrigger("right") != 0){
            lift.set(OI.getIntakeTrigger("right") / SPEED_FACTOR);
        }
        else{
            lift.set(0);
        }
        SmartDashboard.putString("DB/String 0", "Lift Pos: " + lift.getSelectedSensorPosition());
    }
}