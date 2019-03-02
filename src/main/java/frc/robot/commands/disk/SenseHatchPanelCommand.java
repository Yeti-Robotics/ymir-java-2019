/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.disk;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SenseHatchPanelCommand extends Command {
  public SenseHatchPanelCommand() {
    requires(Robot.hatchPanelSubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.hatchPanelSubsystem.getLeftSwitch() || Robot.hatchPanelSubsystem.getRightSwitch()){
      Robot.hatchPanelSubsystem.openIntake();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}