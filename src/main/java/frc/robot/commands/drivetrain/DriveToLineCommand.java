/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.controls.VisionProcessor;

public class DriveToLineCommand extends Command {
  public DriveToLineCommand() {
 
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrainSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("running!!!!!!!!!!!!!!!!!!!!!!!!!!!");
 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrainSubsystem.tankDrive(-.5, -.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drivetrainSubsystem.getCenterLineFollower() || Robot.drivetrainSubsystem.getLeftLineFollower() || Robot.drivetrainSubsystem.getRightLineFollower();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.drivetrainSubsystem.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
