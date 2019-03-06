/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.rollerbar;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LaunchBallCommand extends Command {
  public LaunchBallCommand() {
    requires(Robot.rollerBarSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.rollerBarSubsystem.rollOut();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return !Robot.rollerBarSubsystem.getBeamBreakSensor();
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.rollerBarSubsystem.rollStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
