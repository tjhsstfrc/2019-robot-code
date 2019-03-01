// NOT USED

// package frc.robot.subsystems;

// import frc.robot.utils.*;

// import edu.wpi.first.wpilibj.command.Subsystem;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.InvertType;
// import com.ctre.phoenix.motorcontrol.NeutralMode;

// public class Climb extends Subsystem{
//     /* intake Talon motor controllers */
//     WPI_TalonSRX pushLeft;
//     WPI_TalonSRX pushRight;
//     WPI_TalonSRX rampDeployLeft;
//     WPI_VictorSPX rampDeployRight;

//     /* set Talon IDs */
//     private final int PUSHL_TALON_ID = 1;
//     private final int PUSHR_TALON_ID = 2;
//     private final int RAMPL_TALON_ID = 10;
//     private final int RAMPR_TALON_ID = 11;

//     /* speed variables */
//     double deploySpeed = 10; // Need to change
//     double pushSpeed = 10; // Need to change

//     public void initDefaultCommand(){
//     }

//     /* init settings */
//     public void climbInit(){
//         /* setup climb motor controllers */
//         rampDeployLeft = new WPI_TalonSRX(RAMPL_TALON_ID);
//         rampDeployRight = new WPI_VictorSPX(RAMPR_TALON_ID);
//         pushLeft = new WPI_TalonSRX(PUSHL_TALON_ID);
//         pushRight = new WPI_TalonSRX(PUSHR_TALON_ID);

//         /* factory default values */
//         rampDeployLeft.configFactoryDefault();
//         rampDeployRight.configFactoryDefault();
//         pushLeft.configFactoryDefault();
//         pushRight.configFactoryDefault();

//         /* set up followers */
//         rampDeployRight.follow(rampDeployLeft);
//         //pushRight.follow(pushLeft);

//         /* set motor controllers to brake vs coast */
//         pushLeft.setNeutralMode(NeutralMode.Brake);
//         pushRight.setNeutralMode(NeutralMode.Brake);
//         rampDeployLeft.setNeutralMode(NeutralMode.Coast);
//         rampDeployRight.setNeutralMode(NeutralMode.Coast);

//         /* flip values so robot moves forward when stick-forward/LEDs-green */
//         // pushLeft.setInverted(true);
//         // pushRight.setInverted(true);
//         // rampDeployLeft.setInverted(true);
//         // rampDeployRight.setInverted(InvertType.FollowMaster);

//         /* adjust sensor phase so sensor moves positive when Talon LEDs are green */
//         // lift.setSensorPhase(true);
//     }

//     public void deploy(){
//         if(OI.pressedClimb()){
//             rampDeployLeft.set(deploySpeed);        
//         }
//     }

//     /* !!! review code !!! */
//     public void climb(){
//         if(Math.abs(OI.getClimbStick("x") + 0.10) > 0){
//             if(OI.getClimbStick("x") > 0){
//                 pushLeft.set(0);
//                 pushRight.set(OI.getClimbStick("x"));
//             }
//             else if(OI.getClimbStick("y") > 0){
//                 pushLeft.set(OI.getClimbStick("y"));
//                 pushRight.set(0);
//             }
//         }
//         else if(Math.abs(OI.getClimbStick("y") + 0.10) > 0){
//             pushSpeed = OI.getClimbStick("y");
//             if (Math.abs(pushSpeed) < 0.10) {
//                 pushSpeed = 0;
//             }
//             pushLeft.set(pushSpeed);
//             pushRight.set(pushSpeed);
//         }
//     }
// }