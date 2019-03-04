package frc.robot.subsystems;

import frc.robot.utils.*;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Intake extends Subsystem{
    /* intake Talon motor controllers */
    WPI_TalonSRX intake;

    /* set Talon IDs */
    private final int INTAKE_TALON_ID = 5;

    /* speed settings by percent output */
    private final double INTAKE_SPEED = 0.2;
    private final double OUTTAKE_SPEED = 0.95;
    private final double REVERSE_OUTTAKE_SPEED = 0.50;

    /* speed variables */
    double intakeSpeed;

    public void initDefaultCommand(){
    }

    /* init settings */
    public void intakeInit(){
        /* setup intake motor controller */
        intake = new WPI_TalonSRX(INTAKE_TALON_ID);

        /* factory default values */
        intake.configFactoryDefault();

        /* set motor controller to brake vs coast */
        intake.setNeutralMode(NeutralMode.Coast);
    }

    public void intake(){
        if(OI.pressedIntake() && !OI.pressedOuttake()){ // intake slow
            intakeSpeed = -INTAKE_SPEED;
        }
        else if(OI.pressedIntake() || OI.pressedOuttake()){ // normal outtake fast
            intakeSpeed = -OUTTAKE_SPEED;
        }
        else if(OI.pressedReverse()){ // outtake from bottom
            intakeSpeed = REVERSE_OUTTAKE_SPEED;
        }
        else{
            intakeSpeed = 0;
        }

        /* set speed */
        intake.set(ControlMode.PercentOutput, intakeSpeed);
    }
}