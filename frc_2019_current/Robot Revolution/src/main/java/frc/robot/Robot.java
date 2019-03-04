package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.utils.*;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    /* subsystems */
    Chassis drive;
    Intake intake;
    ElevatorLine lift;
    // Climb climb;

    /* utitlities */
    Camera camera;
    //Camera2 camera2;

    /* sandstorm - code exactly same code as teleop */
    @Override
    public void autonomousPeriodic() {
        drive.drive();
        lift.liftSimple();
        intake.intake();
    }

    @Override
    public void autonomousInit() {
        /* create subsystems objects */
        drive = new Chassis();
        intake = new Intake();
        lift = new ElevatorLine();
        //climb = new Climb();

        /* run subsystem inits */
        drive.chassisInit();
        intake.intakeInit();
        lift.liftInit();
        //climb.climbInit();

        /* create utility objects */
        camera = new Camera();
        //camera2 = new Camera2();

        // /* run utility inits */
        camera.cameraInit();
        //camera2.cameraInit2();
    }

    /* teleop */
    @Override
    public void teleopPeriodic() {
        drive.drive();
        lift.liftSimple();
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
        lift = new ElevatorLine();
        //climb = new Climb();

        /* run subsystem inits */
        drive.chassisInit();
        intake.intakeInit();
        lift.liftInit();
        //climb.climbInit();

        /* create utility objects */
        camera = new Camera();
        //camera2 = new Camera2();

        /* run utility inits */
        camera.cameraInit();
        //camera2.cameraInit2();
    }

    @Override
    public void disabledInit(){
        // camera = new Camera();
        // camera.cameraInit();
    }

    @Override
    public void robotPeriodic(){
    }
}