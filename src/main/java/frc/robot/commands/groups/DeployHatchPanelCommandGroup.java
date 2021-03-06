/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.disk.DeployInCommand;
import frc.robot.commands.disk.DeployOutCommand;
import frc.robot.commands.disk.OpenIntakeCommand;
import frc.robot.subsystems.HatchPanelSubsystem.HatchPanelDeployState;

public class DeployHatchPanelCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployHatchPanelCommandGroup() {
    addSequential(new DeployOutCommand());
    addSequential(new WaitCommand(0.25));
    addSequential(new OpenIntakeCommand());
    addSequential(new WaitCommand(0.25));
    addSequential(new DeployInCommand());
  }

  @Override
  protected void end() {
    Robot.hatchPanelSubsystem.setHatchPanelDeployState(HatchPanelDeployState.LISTEN);
  }
}
