/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SetElevatorLevelCommand extends Command {
  double elevatorLevel;
  public SetElevatorLevelCommand(double elevatorLevel) {
    requires(Robot.elevatorSubsystem);
    this.elevatorLevel = elevatorLevel;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevatorSubsystem.setMotionMagic(elevatorLevel);
    System.out.println("***********ELEVATOR STARTED***************");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println(Robot.elevatorSubsystem.getElevatorEncoder());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevatorSubsystem.motionMagicOnTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("***********ELEVATOR FINISHED********************");
    // Robot.elevatorSubsystem.elevatorStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.elevatorSubsystem.disableElevator();
  }
}
