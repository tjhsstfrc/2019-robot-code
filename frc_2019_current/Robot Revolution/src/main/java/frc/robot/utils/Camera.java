package frc.robot.utils;

import frc.robot.utils.*;

import org.opencv.imgproc.Imgproc;

import org.opencv.core.Mat;
//import org.opencv.core.Scalar;
//import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera {

    private CameraServer cameraserver;
    private UsbCamera gearCamera;
    private CvSink gearVideo1;


    private CameraServer cameraserver1;
    private UsbCamera gearCamera1;

    private CvSink gearVideo;

    private int camInt;

    public Camera(int i){
        camInt = i;
    }

    public void switchCamera(){
        // if input = A, B, C
    }

    public void cameraInit(){
        new Thread(() -> {
            cameraserver = CameraServer.getInstance();
            gearCamera = cameraserver.startAutomaticCapture(camInt); 
            gearCamera.setResolution(320, 240);
            gearCamera.setFPS(30);
            gearVideo = cameraserver.getVideo(gearCamera);
            /*
            cameraserver1 = CameraServer.getInstance();
            gearCamera1 = cameraserver1.startAutomaticCapture(camInt+1); 
            gearCamera1.setResolution(320, 240);
            gearCamera1.setFPS(30);
            gearVideo1 = cameraserver1.getVideo(gearCamera1);
            */


            CvSink cvSink = CameraServer.getInstance().getVideo(); // get a CvSink. This will capture Mats from the camera
            CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480); // setup a CvSource. This will send images back to the Dashboard
            Mat mat = new Mat(); // Mats are very memory expensive. Lets reuse this Mat.

	    /* This cannot be 'true'. The program will never exit if it is. This
	     * lets the robot stop this thread when restarting robot code or
	     * deploying. */
             while (!Thread.interrupted()) {
                 /* Tell the CvSink to grab a frame from the camera and put it
                  * in the source mat.  If there is an error notify the output. */
                 if (cvSink.grabFrame(mat) == 0) {
                     outputStream.notifyError(cvSink.getError()); // send the output the error
                     continue; // skip the rest of the current iteration
                 }
                 /* left side of robot line */
                 //Point pt1 = new Point(40,0);
                 //Point pt2 = new Point(40,320);
           //      /* right side of robot line */
                 //Point pt3 = new Point(80,0);
                 //Point pt4 = new Point(80,320);
            
                 //Imgproc.line(mat, pt1, pt2, new Scalar(0,255,0), 3);
                 //Imgproc.line(mat, pt3, pt4, new Scalar(0,255,0), 3);
                 
                 Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
                 outputStream.putFrame(mat); // give the output stream a new image to display
             }       
        }).start();
    }


}