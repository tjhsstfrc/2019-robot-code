package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.utils.*;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    /* subsystems */
    Chassis drive;
    Intake intake;
    //ElevatorLine lift;
    ElevatorPID lift;
    // Climb climb;

    /* utitlities */
    Camera camera;
    //Camera camera2;

    /* sandstorm - code exactly same code as teleop */
    @Override
    public void autonomousPeriodic() {
        drive.drive();
        //lift.liftSimple();
        lift.lift();
        intake.intake();
    }

    @Override
    public void autonomousInit() {
        /* create subsystems objects */
        drive = new Chassis();
        intake = new Intake();
        //lift = new ElevatorLine();
        lift = new ElevatorPID();
        //climb = new Climb();

        /* run subsystem inits */
        drive.chassisInit();
        intake.intakeInit();
        lift.liftInit();
        //climb.climbInit();

        /* create utility objects */
        camera = new Camera(0);
        //camera2 = new Camera(1);

        // /* run utility inits */
        camera.cameraInit();
        //camera2.cameraInit();
    }

    /* teleop */
    @Override
    public void teleopPeriodic() {
        drive.drive();
        //lift.liftSimple();
        lift.lift();
        intake.intake();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void robotInit() {
        /* create subsystems objects */
        drive = new Chassis();
        intake = new Intake();
        //lift = new ElevatorLine();
        lift = new ElevatorPID();
        //climb = new Climb();

        /* run subsystem inits */
        drive.chassisInit();
        intake.intakeInit();
        lift.liftInit();
        //climb.climbInit();

        /* create utility objects */
        camera = new Camera(0);
        //camera2 = new Camera(1);

        /* run utility inits */
        camera.cameraInit();
        //camera2.cameraInit();
    }

    @Override
    public void disabledInit(){
        camera = new Camera(0);
        //camera2 = new Camera(1);
    }

    @Override
    public void robotPeriodic(){
    }
}