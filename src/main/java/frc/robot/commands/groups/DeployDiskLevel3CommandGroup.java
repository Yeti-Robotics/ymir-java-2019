/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.DriveStraightToTargetCommand;
import frc.robot.commands.drivetrain.DriveToLineCommand;
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.SetElevatorLevelCommand;

public class DeployDiskLevel3CommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployDiskLevel3CommandGroup() {
    addSequential(new DriveToLineCommand());
    addSequential(new LineFollowToTargetCommand());
    // addSequential(new TurnToTargetCommandGroup());
    addSequential(new SetElevatorLevelCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_3));
    addSequential(new DeployHatchPanelCommandGroup());
  }
}
