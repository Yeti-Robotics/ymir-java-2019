/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.controls.VisionProcessor;

public class LineFollowToTargetCommand extends Command {

  private boolean lineVisible = true;
  private double distance;

  public LineFollowToTargetCommand() {
    requires(Robot.drivetrainSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrainSubsystem.resetEncoders();
    distance = VisionProcessor.getCenterDistance(Robot.latestContours[0], Robot.latestContours[1]) - RobotMap.CAMERA_BUMPER_BUFFER;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    String left = Robot.drivetrainSubsystem.getLeftLineFollower() ? "1" : "0";
    String center = Robot.drivetrainSubsystem.getCenterLineFollower() ? "1" : "0";
    String right = Robot.drivetrainSubsystem.getRightLineFollower() ? "1" : "0";
    String lineFollow = left + center + right;
    switch (lineFollow) {
    case "000":
    case "010":
      Robot.drivetrainSubsystem.tankDrive(-0.5, -0.5);
      break;
    case "110":
    case "100":
      Robot.drivetrainSubsystem.tankDrive(-0.5,-0.6);
      break;
    case "011":
    case "001":
      Robot.drivetrainSubsystem.tankDrive(-0.6, -0.5);
      break;
    // case "000":
      // lineVisible = false;
      // break;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return Robot.drivetrainSubsystem.getAvgEncoderDistance() >= distance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("'''''''''''''''''finished line follow'''''''''''''''''");
    Robot.drivetrainSubsystem.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("**********************line follow interrupted********************");
  }
}
