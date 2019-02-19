/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.rack;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class MoveDiscRackCommand extends PIDCommand {
  public MoveDiscRackCommand() {
    super(1,0,0);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.discRackSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.getPIDController().setSetpoint(0);
    this.getPIDController().enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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

  @Override
  protected double returnPIDInput() {
    return Robot.jevois.getAzimuth();
  }

  @Override
  protected void usePIDOutput(double output) {
  Robot.discRackSubsystem.moveRack(output);
  }
}
