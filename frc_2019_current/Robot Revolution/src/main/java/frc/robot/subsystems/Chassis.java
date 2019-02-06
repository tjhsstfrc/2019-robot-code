// package frc.robot.subsystems;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// import edu.wpi.first.wpilibj.command.Subsystem;

// import com.ctre.phoenix.motorcontrol.Faults;
// import com.ctre.phoenix.motorcontrol.InvertType;
// //import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// import edu.wpi.first.wpilibj.Joystick;
// //import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// public class Chassis extends Subsystem{
//     WPI_TalonSRX rightFront = new WPI_TalonSRX(1);
//     WPI_TalonSRX rightFollower = new WPI_TalonSRX(2);
//     WPI_TalonSRX leftFront = new WPI_TalonSRX(3);
//     WPI_TalonSRX leftFollower = new WPI_TalonSRX(4);
    
//     DifferentialDrive _diffDrive = new DifferentialDrive(leftFront, rightFront);

//     Joystick _joystick = new Joystick(1);

//     Faults _faults_L = new Faults();
//     Faults _faults_R = new Faults();
    
//     public void initDefaultCommand(){
//     }

//     // public void teleopPeriodic() {

//     //     // String work = "";

//     //     /* get gamepad stick values */
//     //     double leftSpeed = -1 * _joystick.getRawAxis(1);
//     //     double rightSpeed = -1 * _joystick.getRawAxis(5);
//     //     //boolean btn1 = _joystick.getRawButton(1); /* is button is down, print joystick values */

//     //     /* deadband gamepad 10% */
//     //     if (Math.abs(leftSpeed) < 0.10) {
//     //       leftSpeed = 0;
//     //     }
//     //     if (Math.abs(rightSpeed) < 0.10) {
//     //         rightSpeed = 0;
//     //     }

//     //     /* drive robot */
//     //     _diffDrive.tankDrive(leftSpeed, rightSpeed);

//     //     // work += " GF:" + leftSpeed + " GT:" + rightSpeed;

//     //     /* get sensor values */
//     //     //double leftVelUnitsPer100ms = leftFront.getSelectedSensorVelocity(0);
//     //     //double rghtVelUnitsPer100ms = rightFront.getSelectedSensorVelocity(0);

//     //     //work += " L:" + leftVelUnitsPer100ms + " R:" + rghtVelUnitsPer100ms;

//     //     /*
//     //      * drive motor at least 25%, Talons will auto-detect if sensor is out of phase
//     //      */
//     //     leftFront.getFaults(_faults_L);
//     //     rightFront.getFaults(_faults_R);

//     //     // if (_faults_L.SensorOutOfPhase) {
//     //     //     work += " L sensor is out of phase";
//     //     // }
//     //     // if (_faults_R.SensorOutOfPhase) {
//     //     //     work += " R sensor is out of phase";
//     //     // }

//     //     /* print to console if btn1 is held down */
//     //     // if (btn1) {
//     //     //     System.out.println(work);
//     //     // }
//     // }

//     public void robotInit() {
//         /* factory default values */
//         rightFront.configFactoryDefault();
//         rightFollower.configFactoryDefault();
//         leftFront.configFactoryDefault();
//         leftFollower.configFactoryDefault();

//         /* set up followers */
//         rightFollower.follow(rightFront);
//         leftFollower.follow(leftFront);

//         /* [3] flip values so robot moves forward when stick-forward/LEDs-green */
//         rightFront.setInverted(true); // !< Update this
//         leftFront.setInverted(false); // !< Update this

//         /*
//          * set the invert of the followers to match their respective master controllers
//          */
//         rightFollower.setInverted(InvertType.FollowMaster);
//         leftFollower.setInverted(InvertType.FollowMaster);

//         /*
//          * [4] adjust sensor phase so sensor moves positive when Talon LEDs are green
//          */
//         rightFront.setSensorPhase(true);
//         leftFront.setSensorPhase(true);

//         /*
//          * WPI drivetrain classes defaultly assume left and right are opposite. call
//          * this so we can apply + to both sides when moving forward. DO NOT CHANGE
//          */
//         _diffDrive.setRightSideInverted(false);
//     }
// }