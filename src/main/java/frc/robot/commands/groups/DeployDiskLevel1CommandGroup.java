/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.DriveStraightToTargetCommand;
import frc.robot.commands.drivetrain.DriveToLineCommand;
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.SetElevatorLevelCommand;
import frc.robot.controls.VisionProcessor;

public class DeployDiskLevel1CommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployDiskLevel1CommandGroup() {
        addParallel(new SetElevatorLevelCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_1));
        addSequential(new TurnToTargetCommandGroup(), 0.75);
        addSequential(new DriveToLineCommand());
        addSequential(new LineFollowToTargetCommand());
        addSequential(new DeployHatchPanelCommandGroup());
  }
}
