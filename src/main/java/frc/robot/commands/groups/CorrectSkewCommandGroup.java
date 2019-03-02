/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.DriveForDistancePIDCommand;
import frc.robot.commands.drivetrain.TurnAngleCommand;
import frc.robot.controls.VisionProcessor;

public class CorrectSkewCommandGroup extends CommandGroup {

  public CorrectSkewCommandGroup() {
    if (Robot.jevois.getCenterDistance() < 1000) {
      double leftDistance = Robot.jevois.getLeftDistance();
      double rightDistance = Robot.jevois.getRightDistance();
      double leftAzimuth = Robot.jevois.getLeftAzimuth();
      double rightAzimuth = Robot.jevois.getRightAzimuth();

      double C = Math.acos(((rightDistance * rightDistance) - (leftDistance * leftDistance) - (RobotMap.TARGET_BOUND_WIDTH_INCH * RobotMap.TARGET_BOUND_WIDTH_INCH)) / (-2 * RobotMap.TARGET_BOUND_WIDTH_INCH * leftDistance));
      double B = 180 - C - (rightAzimuth - leftAzimuth);
      double skew = -90 - leftAzimuth + C;
      double Cf = 90 - skew;
      double f = (RobotMap.TARGET_BOUND_WIDTH_INCH / 2) / Math.sin(90 - B);
      double e = ((rightDistance - f) * Math.sin(90 - B)) / Math.sin(Cf);
      double d = e * Math.sin(Cf);
      double x = 90 - Cf;

      addSequential(new TurnAngleCommand(90 - x));
      addSequential(new DriveForDistancePIDCommand(d));
      addSequential(new TurnAngleCommand(-90));
      addSequential(new DriveForDistancePIDCommand(Robot.jevois.getCenterDistance()));
    }
  }
}
