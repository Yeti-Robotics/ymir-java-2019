/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import java.util.ArrayList;

import org.opencv.core.Rect;

import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class VisionProcessor {

    public static Rect boundRect(Contour leftCon, Contour rightCon ) {
        return new Rect(leftCon.x, leftCon.y, (rightCon.x + rightCon.w) - leftCon.x, rightCon.h);
    }

    public static double getLeftDistance(Contour leftCon, Contour rightCon ) {
        double leftDistance = (RobotMap.TAPE_BOUND_WIDTH_INCH * RobotMap.FOCAL_LENGTH) / leftCon.w;
        return leftDistance;
    }

    public static double getRightDistance(Contour leftCon, Contour rightCon ) {
        double rightDistance = (RobotMap.TAPE_BOUND_WIDTH_INCH * RobotMap.FOCAL_LENGTH) / rightCon.w;
        return rightDistance;
    }

    public static double getAzimuth(Contour leftCon, Contour rightCon ) {
        double centerX = (boundRect(leftCon, rightCon).x + boundRect(leftCon, rightCon).width/2);
        double azimuth = (centerX*RobotMap.FOV/RobotMap.IMAGE_WIDTH) - (RobotMap.FOV/2);
        return azimuth;
        
    }

    public static double getCenterDistance(Contour leftCon, Contour rightCon) {

       if (leftCon != null && leftCon != null) {
        return (RobotMap.TARGET_BOUND_WIDTH_INCH * RobotMap.FOCAL_LENGTH) / boundRect(leftCon, rightCon).width;
        }
        return 1000;
    }

    public static double getAverageDistance(Contour leftCon, Contour rightCon) {
        if (leftCon != null && leftCon != null) {        
            return (getLeftDistance(leftCon, rightCon) + getRightDistance(leftCon, rightCon)) / 2;
            }
            return 1000;
    }

}
