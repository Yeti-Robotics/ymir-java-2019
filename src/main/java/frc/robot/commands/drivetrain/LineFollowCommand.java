/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LineFollowCommand extends Command {

  private boolean lineVisible = true;
  public LineFollowCommand() {
    requires(Robot.drivetrainSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    String left = Robot.drivetrainSubsystem.lineSensorLeft.getAverageVoltage() > 0.5 ? "1" : "0";
    String center = Robot.drivetrainSubsystem.lineSensorCenter.getAverageVoltage() > 0.5 ? "1" : "0";
    String right = Robot.drivetrainSubsystem.lineSensorRight.getAverageVoltage() > 0.5 ? "1" : "0";
    String lineFollow = left + center + right;
    switch (lineFollow) {
    case "010":
      Robot.drivetrainSubsystem.tankDrive(0.5, 0.5);
      break;
    case "110":
    case "100":
      Robot.drivetrainSubsystem.tankDrive(0.5, 0.7);
      break;
    case "011":
    case "001":
      Robot.drivetrainSubsystem.tankDrive(0.7, 0.5);
      break;
    case "000": //for testing
      lineVisible = false;
      break;
    }
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.jevois.getLeftDistance() <= 15 || !lineVisible;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
