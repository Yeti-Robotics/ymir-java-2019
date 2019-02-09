/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class DeployDiskLevel2CommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployDiskLevel2CommandGroup() {
    addSequential(new LineFollowCommand());
    addSequential(new TurnToTargetCommandGroup());
    // rack and pinion command
    addSequential(new MoveElevatorCommand(RobotMap.ELEVATOR_LEVELS[5]));
    addSequential(new DeployHatchPanelCommandGroup());
  }
}
