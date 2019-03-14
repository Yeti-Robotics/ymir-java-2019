/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MoveElevatorCommand extends Command {

  private double elevatorPosition = -1;
  private boolean stop;

  public MoveElevatorCommand(double elevatorPosition) {
    this.elevatorPosition = elevatorPosition;

    requires(Robot.elevatorSubsystem);
  }

  public MoveElevatorCommand() {
    requires(Robot.elevatorSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevatorSubsystem.disable();
    if (elevatorPosition == -1) {
      if (Robot.elevatorSubsystem.getLevel() != -1) {
        stop = false;
        if (Robot.deployState == Robot.deployState.HATCH_PANEL) {
          Robot.elevatorSubsystem.setMotionMagic(RobotMap.ELEVATOR_HATCH_PANEL_LEVELS[Robot.elevatorSubsystem.getLevel()]);
        } else {
          Robot.elevatorSubsystem.setMotionMagic(RobotMap.ELEVATOR_BALL_LEVELS[Robot.elevatorSubsystem.getLevel()]);
        }
      } else {
        stop = true;
      }
    } else {
      Robot.elevatorSubsystem.setMotionMagic(elevatorPosition);
    }
    // Robot.elevatorSubsystem.setSetpoint(elevatorPosition);
    // Robot.elevatorSubsystem.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println("running");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevatorSubsystem.motionMagicOnTarget() || stop;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // System.out.println("finished!!!!!!");
    if (Robot.elevatorSubsystem.getLevel() == -1) {
      Robot.elevatorSubsystem.elevatorOff();
      Robot.elevatorSubsystem.disable();
    } else {
      Robot.elevatorSubsystem.elevatorStop();
      // Robot.elevatorSubsystem.setSetpoint(elevatorPosition);
      // Robot.elevatorSubsystem.enable();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
