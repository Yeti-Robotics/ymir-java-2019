/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.disk;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DeployOutCommand extends Command {
  public DeployOutCommand() {
    requires(Robot.hatchPanelSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("DeployOut");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatchPanelSubsystem.deployOut();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
