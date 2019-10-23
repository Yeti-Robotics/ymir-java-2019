/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jdk.jfr.Threshold;

public class TurnAngleCommand extends PIDCommand {

  private static final double THRESHOLD = 0.5;
  private double angle;
  double targetAngle;
  double currentAngle;

  public TurnAngleCommand(double angle) {
    super(.05, 0, 0, 0);
    setTimeout(.75);
    setInputRange(-180, 180);
    // getPIDController().setContinuous(true);
    getPIDController().setAbsoluteTolerance(2);
    getPIDController().setOutputRange(-0.5, 0.5);
    
    requires(Robot.drivetrainSubsystem);
    this.angle = angle;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Robot.drivetrainSubsystem.resetGyro();
    setSetpoint(angle);
    getPIDController().enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Gyro PID", getPIDController().get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("**********************************FINISHED*******************************************");
  return getPIDController().onTarget() || isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    getPIDController().disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected double returnPIDInput() {
    // return Robot.drivetrainSubsystem.getAngle();
    return 5.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.drivetrainSubsystem.tankDrive(-output, output);
  }
}
