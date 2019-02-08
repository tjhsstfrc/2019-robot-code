package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
    WPI_TalonSRX intake = new WPI_TalonSRX(5);

    public void initDefaultCommand(){
    }
}