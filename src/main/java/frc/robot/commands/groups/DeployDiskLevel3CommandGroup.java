/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.LineFollowCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.rack.MoveDiscRackCommand;

public class DeployDiskLevel3CommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployDiskLevel3CommandGroup() {
    addSequential(new LineFollowCommand());
    addSequential(new TurnToTargetCommandGroup());
    addSequential(new MoveDiscRackCommand());
    addSequential(new MoveElevatorCommand(RobotMap.ELEVATOR_LEVELS[3]));
    addSequential(new DeployHatchPanelCommandGroup());
  }
}
