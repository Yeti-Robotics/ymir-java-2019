/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveWristCommand extends Command {
  private double angle;
  public MoveWristCommand(double angle) {
    requires(Robot.wristSubsystem);

    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.wristSubsystem.resetWristEncoder();
    Robot.wristSubsystem.setSetpoint(angle);
    Robot.wristSubsystem.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.wristSubsystem.getPIDController().setF(RobotMap.WRIST_FEED_FORWARD * (1 / Math.cos(Robot.wristSubsystem.getWristEncoderValue())));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.wristSubsystem.onTarget();
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
