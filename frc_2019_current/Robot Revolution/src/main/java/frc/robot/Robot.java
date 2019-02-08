package frc.robot;

//import frc.robot.subsystems.*;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends TimedRobot {
    // public static Chassis chassis = new Chassis();

    // public void robotInit(){
    //     chassis.robotInit();
    // }
        
    Compressor compressor = new Compressor(0);
    Solenoid solenoid1 = new Solenoid(0);
    Solenoid solenoid2 = new Solenoid(1);
    int shiftCount = 0;

    WPI_TalonSRX rightFront = new WPI_TalonSRX(1);
    WPI_TalonSRX rightFollower = new WPI_TalonSRX(2);
    WPI_TalonSRX leftFront = new WPI_TalonSRX(3);
    WPI_TalonSRX leftFollower = new WPI_TalonSRX(4);

    DifferentialDrive _diffDrive = new DifferentialDrive(leftFront, rightFront);

    //main pilot joystick
    Joystick _joystick = new Joystick(1);

    Faults _faults_L = new Faults();
    Faults _faults_R = new Faults();

    Encoder encoderLeftDrive;
    Encoder encoderRightDrive;

    //secondary joystick and talons for climb
    Joystick _joystickClimb = new Joystick(1);
    WPI_TalonSRX leftClimb = new WPI_TalonSRX(5);
    WPI_TalonSRX rightClimb = new WPI_TalonSRX(6);
    DifferentialDrive _diffDriveClimb = new DifferentialDrive(leftClimb, rightClimb);

    WPI_TalonSRX intakeTalon = new WPI_TalonSRX(7);

    @Override
    public void teleopPeriodic() {
        double current = compressor.getCompressorCurrent();
        System.out.println(current);
        // String work = "";

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

        // work += " GF:" + leftSpeed + " GT:" + rightSpeed;

        /* get sensor values */
        //double leftVelUnitsPer100ms = leftFront.getSelectedSensorVelocity(0);
        //double rghtVelUnitsPer100ms = rightFront.getSelectedSensorVelocity(0);

        //work += " L:" + leftVelUnitsPer100ms + " R:" + rghtVelUnitsPer100ms;

        /*
         * drive motor at least 25%, Talons will auto-detect if sensor is out of phase
         */
        leftFront.getFaults(_faults_L);
        rightFront.getFaults(_faults_R);

        // if (_faults_L.SensorOutOfPhase) {
        //     work += " L sensor is out of phase";
        // }
        // if (_faults_R.SensorOutOfPhase) {
        //     work += " R sensor is out of phase";
        // }

        /* print to console if btn1 is held down */
        // if (btn1) {
        //     System.out.println(work);
        // }

        /* use the two left right trigger buttons to shift up/down */
        if(_joystick.getRawButton(6) == true)
        {
            solenoid1.set(true);
            solenoid2.set(false);
        }
        if(_joystick.getRawButton(5) == true)
        {
            solenoid1.set(false);
            solenoid2.set(true);
        }

        /* get encoder data */
        // SmartDashboard.putString("DB/String 0", Math.abs(encoderRightDrive.getRate()) + "");
        // SmartDashboard.putString("DB/String 1", Math.abs(encoderLeftDrive.getRate()) +"");
        // SmartDashboard.putString("DB/String 2", solenoid1.get() + " " + solenoid2.get());
        // if((Math.abs(encoderRightDrive.getRate()) > 1500) && Math.abs(encoderLeftDrive.getRate()) > 1500)
        // {
        //     SmartDashboard.putString("DB/String 5", "FIRST CONDITION TRUE");
        //     if(solenoid1.get() == false && solenoid2.get() == true)
        //     {
        //         SmartDashboard.putString("DB/String 6", "UP");
        //         solenoid1.set(true);
        //         solenoid2.set(false);
        //     }
        // }
        // else
        // {
        //     SmartDashboard.putString("DB/String 5", "FIRST CONDITION FALSE");
        //     if(solenoid1.get() == true && solenoid2.get() == false)
        //     {
        //         SmartDashboard.putString("DB/String 6", "DOWN");
        //         solenoid1.set(false);
        //         solenoid2.set(true);
        //     }
        // }
        
        
        // for Climb
        double ClimbSpeed = -1 * _joystickClimb.getRawAxis(1);          //left joystick

        _diffDriveClimb.tankDrive(ClimbSpeed,-1*ClimbSpeed);

        // for Intake
        double intakeSpeed = -1 * _joystickClimb.getRawAxis(5)          //right joystick

        
    }

    @Override
    public void robotInit() {
        /* set up pneumatics air compressor in closed loop: if under 120psi? activate compressor */
        compressor.setClosedLoopControl(true);

        solenoid1.set(false);
        solenoid2.set(true);

        /* set up evo shifter encoders
           left shifter pin RIO I/O 0-1
           right shifter pin RIO I/O 2-3 */
        encoderLeftDrive = new Encoder(0, 1);
        encoderRightDrive = new Encoder(2,3);

        /* factory default values */
        rightFront.configFactoryDefault();
        rightFollower.configFactoryDefault();
        leftFront.configFactoryDefault();
        leftFollower.configFactoryDefault();

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