package frc.robot;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends TimedRobot {
    WPI_TalonSRX rightFront = new WPI_TalonSRX(1);
    WPI_TalonSRX rightFollower = new WPI_TalonSRX(2);
    WPI_TalonSRX leftFront = new WPI_TalonSRX(3);
    WPI_TalonSRX leftFollower = new WPI_TalonSRX(4);

    DifferentialDrive _diffDrive = new DifferentialDrive(leftFront, rightFront);

    Joystick _joystick = new Joystick(1);
    Joystick _joystickClimb = new Joystick(2);

    Faults _faults_L = new Faults();
    Faults _faults_R = new Faults();

    Encoder encoderLeftDrive;
    Encoder encoderRightDrive;

    //for Climb
    WPI_TalonSRX leftClimb = new WPI_TalonSRX(6);
    WPI_TalonSRX rightClimb = new WPI_TalonSRX(7);
    DifferentialDrive _diffDriveClimb = new DifferentialDrive(leftClimb, rightClimb);

    WPI_TalonSRX intakeTalon = new WPI_TalonSRX(5);
    SpeedController intakeMotor = intakeTalon;

    @Override
    public void teleopPeriodic() {
        /* get gamepad stick values */
        double leftSpeed = -1 * _joystick.getRawAxis(5);
        double rightSpeed = -1 * _joystick.getRawAxis(1);
        //boolean btn1 = _joystick.getRawButton(1); /* is button is down, print joystick values */

        /* deadband gamepad 10% */
        if (Math.abs(leftSpeed) < 0.10) {
          leftSpeed = 0;
        }
        if (Math.abs(rightSpeed) < 0.10) {
            rightSpeed = 0;
        }

        /* drive robot */
        _diffDrive.tankDrive(leftSpeed, rightSpeed);
        
        // for Climb
        double ClimbSpeed = -1 * _joystickClimb.getRawAxis(1);          //left joystick

        _diffDriveClimb.tankDrive(ClimbSpeed,-1*ClimbSpeed);

        // for Intake
        double intakeSpeed = (_joystickClimb.getRawAxis(5)) / 4;          //right joystick
        if (Math.abs(intakeSpeed) < 0.10) {
            intakeSpeed = 0;
        }
        intakeMotor.set(intakeSpeed);
    }

    @Override
    public void robotInit() {

        /* set up encoders */
        encoderLeftDrive = new Encoder(0, 1);
        encoderRightDrive = new Encoder(2,3);

        /* factory default values */
        rightFront.configFactoryDefault();
        rightFollower.configFactoryDefault();
        leftFront.configFactoryDefault();
        leftFollower.configFactoryDefault();

        /* break vs coast */
        rightFront.setNeutralMode(NeutralMode.Brake);
        rightFollower.setNeutralMode(NeutralMode.Brake);
        leftFront.setNeutralMode(NeutralMode.Brake);
        leftFollower.setNeutralMode(NeutralMode.Brake);

        /* set up followers */
        rightFollower.follow(rightFront);
        leftFollower.follow(leftFront);

        /* [3] flip values so robot moves forward when stick-forward/LEDs-green */
        rightFront.setInverted(true); // !< Update this
        leftFront.setInverted(false); // !< Update this

        /*
         * set the invert of the followers to match their respective master controllers
         */
        rightFollower.setInverted(InvertType.FollowMaster);
        leftFollower.setInverted(InvertType.FollowMaster);

        /*
         * [4] adjust sensor phase so sensor moves positive when Talon LEDs are green
         */
        rightFront.setSensorPhase(true);
        leftFront.setSensorPhase(true);

        /*
         * WPI drivetrain classes defaultly assume left and right are opposite. call
         * this so we can apply + to both sides when moving forward. DO NOT CHANGE
         */
        _diffDrive.setRightSideInverted(false);
    }
}