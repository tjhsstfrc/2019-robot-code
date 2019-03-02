package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.AnalogInput;

public class LineSensor {
    AnalogInput lineSensor = new AnalogInput(0); // static
    
    /* value the IR sensor reads as a line 
     * - may need to adjust depending on environment */
    private final int HIT_VALUE = 170; // static

    private boolean hit = false; // static

    public void printSensorValue(){
        SmartDashboard.putString("DB/String 1", lineSensor.getValue() + "");
    }

    public int getSensorValue(){
        return lineSensor.getValue();
    }

    public boolean hitLine(){
        if(lineSensor.getValue() >= HIT_VALUE){
            hit = true;
        }
        else{
            hit = false;
        }
        return hit;
    }
}