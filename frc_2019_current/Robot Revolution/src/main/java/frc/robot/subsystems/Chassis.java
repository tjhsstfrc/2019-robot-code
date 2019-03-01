package frc.robot.subsystems;

import frc.robot.utils.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Chassis extends Subsystem{
    /* drive Talon motor controllers */
    WPI_TalonSRX rightFront;
    WPI_TalonSRX rightFollower;
    WPI_TalonSRX leftFront;
    WPI_TalonSRX leftFollower;

    /* set Talon IDs */
    private final int RFRONT_TALON_ID = 6;
    private final int RFOLLOWER_TALON_ID = 7;
    private final int LFRONT_TALON_ID = 8;
    private final int LFOLLOWER_TALON_ID = 9;

    /* speed variables */
    double leftSpeed;
    double rightSpeed;
    double straightForward;
    double straightBackward;
    private final int SLOW_FACTOR = 1; // divide speed

    /* drive type */
    DifferentialDrive diffDriveTank;

    public void initDefaultCommand(){
    }

    /* init settings */
    public void chassisInit(){
        /* setup drive motor controllers */
        rightFront = new WPI_TalonSRX(RFRONT_TALON_ID);
        rightFollower = new WPI_TalonSRX(RFOLLOWER_TALON_ID);
        leftFront = new WPI_TalonSRX(LFRONT_TALON_ID);
        leftFollower = new WPI_TalonSRX(LFOLLOWER_TALON_ID);

        /* factory default values */
        rightFront.configFactoryDefault();
        rightFollower.configFactoryDefault();
        leftFront.configFactoryDefault();
        leftFollower.configFactoryDefault();

        /* set up followers */
        rightFollower.follow(rightFront);
        leftFollower.follow(leftFront);

        /* set motor controllers to brake vs coast */
        rightFront.setNeutralMode(NeutralMode.Brake);
        rightFollower.setNeutralMode(NeutralMode.Brake);
        leftFront.setNeutralMode(NeutralMode.Brake);
        leftFollower.setNeutralMode(NeutralMode.Brake);

        /* flip values so robot moves forward when stick-forward/LEDs-green */
        rightFront.setInverted(true);
        leftFront.setInverted(false);
        rightFollower.setInverted(InvertType.FollowMaster);
        leftFollower.setInverted(InvertType.FollowMaster);

        /* adjust sensor phase so sensor moves positive when Talon LEDs are green */
        rightFront.setSensorPhase(true);
        leftFront.setSensorPhase(true);

        diffDriveTank = new DifferentialDrive(leftFront, rightFront);

        /* WPI drivetrain classes defaultly assume left and right are opposite. call
         * this so we can apply + to both sides when moving forward. DO NOT CHANGE */
        diffDriveTank.setRightSideInverted(false);
    }

    /* tank drive with triggers */
    public void drive(){
        /* backwards straight */
        if(OI.getDriveTrigger("left") != 0){
            leftSpeed = 1 * OI.getDriveTrigger("left") / SLOW_FACTOR;
            rightSpeed = 1 * OI.getDriveTrigger("left") / SLOW_FACTOR;
        }
        /* frontwards straight */
        else if(OI.getDriveTrigger("right") != 0){
            leftSpeed = -1 * OI.getDriveTrigger("right") / SLOW_FACTOR;
            rightSpeed = -1 * OI.getDriveTrigger("right") / SLOW_FACTOR;
        }
        /* regular joystick drive */
        else if(OI.getDriveStick("left") != 0 || OI.getDriveStick("right") != 0){
            /* set speed to joystick values */
            leftSpeed = 1 * OI.getDriveStick("right") / SLOW_FACTOR;
            rightSpeed = 1 * OI.getDriveStick("left") / SLOW_FACTOR;
            straightForward = 1 * OI.getDriveTrigger("left") / SLOW_FACTOR;
            straightBackward = 1* OI.getDriveTrigger("right") / SLOW_FACTOR;
        }
        else{
            leftSpeed = 0;
            rightSpeed = 0;
        }

        /* deadband gamepad 10% */
        if (Math.abs(leftSpeed) < 0.10) {
            leftSpeed = 0;
        }
        if (Math.abs(rightSpeed) < 0.10) {
            rightSpeed = 0;
        }

        /* tank drive */
        diffDriveTank.tankDrive(leftSpeed, rightSpeed);
    }
}