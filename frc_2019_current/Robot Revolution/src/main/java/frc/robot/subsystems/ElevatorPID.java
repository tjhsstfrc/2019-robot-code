// NOT USED ATM BUT SAVE FOR LATER

// package frc.robot.subsystems;

// import frc.robot.utils.*;

// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.smartdashboard.*;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;

// public class ElevatorPID extends Subsystem{
//     /* intake Talon motor controllers */
//     WPI_TalonSRX lift;
//     WPI_TalonSRX liftFollower;

//     /* set Talon IDs */
//     private final int LIFT_TALON_ID = 3; //3
//     private final int LIFTFOLLOWER_TALON_ID = 4; //4

//     /* speed variables */
//     double liftSpeed ;
//     private final int SPEED_FACTOR = 2;
//     private final double LIFT_SPEED = .3;

//     /* lift position presets adjust as necessary */
//     private final int LOW_POSITION_PRESET = 0;
//     private final int MID_POSITION_PRESET = 64;
//     private final int MIN_POSITION = -10;
//     private final int MAX_POSITION = 526;

//     private final int LOWER_DISTANCE = 50;
//     private final int RAISE_DISTANCE = 50;

//     private double elevatorSpeed;

//     public void initDefaultCommand(){
//     }

//     /* init settings */
//     public void liftInit(){
//         /* setup elevator motor controllers */
//         lift = new WPI_TalonSRX(LIFT_TALON_ID);
//         liftFollower = new WPI_TalonSRX(LIFTFOLLOWER_TALON_ID);

//         /* factory default values */
//         lift.configFactoryDefault();
//         liftFollower.configFactoryDefault();

//         /* set up followers */
//         liftFollower.follow(lift);

//         /* set motor controllers to brake vs coast */
//         lift.setNeutralMode(NeutralMode.Brake);
//         liftFollower.setNeutralMode(NeutralMode.Brake);

//         /* configure sensor to absolute */
//         //lift.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
//         lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

//         /*
//         * flip value so that motor output and sensor velocity are the same polarity. Do
//         * this before closed-looping
//         */
//         lift.setSensorPhase(true); // <<<<<< Adjust this

//         /* set position value for encoder at 0 to start */
//         lift.setSelectedSensorPosition(0);

//         /* PID settings */
//         lift.configMotionAcceleration(6000);
//         lift.configMotionCruiseVelocity(1500);                //for initial testing
//         lift.configMotionCruiseVelocity(2500);
//         lift.config_kF(0, 0);
//         lift.config_kP(0, 2);
//         lift.config_kI(0, .005);
//         lift.config_kD(0, .001);
//     }

//     public void lift(){
//         // if(OI.pressedLiftPresetLow()){
//         //     lift.set(ControlMode.MotionMagic, LOW_POSITION_PRESET);
//         // }
//         // else if(OI.pressedLiftPresetMid()){
//         //     lift.set(ControlMode.MotionMagic, MID_POSITION_PRESET);
//         // }
//         if(OI.getIntakeTrigger("left") != 0){
//             //if(lift.getSelectedSensorPosition(0) >= MIN_POSITION){
//                 elevatorSpeed = -1 * OI.getIntakeTrigger("left");
//                 if (Math.abs(elevatorSpeed) < 0.20) {
//                     elevatorSpeed = 0;
//                 }
//                 lift.set(ControlMode.PercentOutput, elevatorSpeed / SPEED_FACTOR);
//             //}
//             // else{
//             //     lift.set(ControlMode.Velocity, 0.0);
//             // }
            
//             // if(lift.getSelectedSensorPosition(0) > MIN_POSITION + 5000){
//             //     lift.set(ControlMode.MotionMagic, MIN_POSITION);
//             // }
//             // else{
//             //     lift.set(-1 * OI.getIntakeTrigger("left") / SPEED_FACTOR);
//             // }
//         }
//         else if(OI.getIntakeTrigger("right") != 0){
//             //if(lift.getSelectedSensorPosition(0) <= MAX_POSITION){
//                 elevatorSpeed = OI.getIntakeTrigger("right");
//                 if (Math.abs(elevatorSpeed) < 0.20) {
//                     elevatorSpeed = 0;
//                 }
//                 lift.set(ControlMode.PercentOutput, elevatorSpeed / SPEED_FACTOR);
//             //}
//             // else{
//             //     lift.set(ControlMode.Velocity, 0.0);
//             // }
//             // if(lift.getSelectedSensorPosition(0) < MAX_POSITION){
//             //     lift.set(ControlMode.MotionMagic, MAX_POSITION);
//             // }
//             // else{
//             //     lift.set(OI.getIntakeTrigger("right") / SPEED_FACTOR);
//             // }
//         }

//         //lower and raise PID
//         // else if(OI.getLowerButton() != false){
//         //     //lift.set(ControlMode.MotionMagic,lift.getSelectedSensorPosition(0) - LOWER_DISTANCE);
//         //     lift.set(ControlMode.MotionMagic, LOWER_DISTANCE);
//         // } else if(OI.getUpperButton() != false){
//         //     //lift.set(ControlMode.MotionMagic,lift.getSelectedSensorPosition(0) + RAISE_DISTANCE);
//         //     lift.set(ControlMode.MotionMagic, RAISE_DISTANCE);
//         // }

//         else{
//             lift.set(ControlMode.PercentOutput, 0.0);
//             //OI.setRumble(3);
//         }
//          SmartDashboard.putString("DB/String 0", "Lift Pos: " + lift.getSelectedSensorPosition(0));
//          SmartDashboard.putString("DB/String 1", "Lift Pos: " + lift.getOutputCurrent());

//          //SmartDashboard.putString("DB/String 0", "Lift Pos: " + FeedbackDevice.valueOf(0));

//         //SmartDashboard.putString("DB/String 0", "Lift Pos: " + enc.get());

//     }

//     /* manually set encoder to 0 */
//     public void manualZero(){
//         lift.setSelectedSensorPosition(0);
//     }
// }