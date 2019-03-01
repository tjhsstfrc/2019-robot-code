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

    /* sandstorm - code exactly same code as teleop */
    @Override
    public void autonomousPeriodic() {
        drive.drive();
        intake.intake();
        lift.lift();
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

        /* run utility inits */
        camera.cameraInit();
    }

    /* teleop */
    @Override
    public void teleopPeriodic() {
        drive.drive();
        intake.intake();
        lift.lift();
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

        /* run utility inits */
        camera.cameraInit();
    }
}