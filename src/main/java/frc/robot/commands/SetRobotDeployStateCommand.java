/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Robot.DeployState;
import frc.robot.subsystems.HatchPanelSubsystem;

public class SetRobotDeployStateCommand extends Command {

  private DeployState deployState;

  public SetRobotDeployStateCommand(DeployState deployState) {
    this.deployState = deployState;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.deployState = deployState;
      if(deployState == DeployState.HATCH_PANEL) {
        Robot.hatchPanelSubsystem.setHatchPanelDeployState(HatchPanelSubsystem.HatchPanelDeployState.LISTEN);
        Robot.rootNetworkTable.getEntry("Auto deploy mode").setString("Hatch Panel");
      } else {
        Robot.rootNetworkTable.getEntry("Auto deploy mode").setString("Ball");
      }
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
