/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.disk;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.DriveToLineCommand;
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.SetElevatorLevelCommand;
import frc.robot.subsystems.HatchPanelSubsystem;

public class AutoIntakeDiskCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoIntakeDiskCommandGroup() {
    addParallel(new TurnToTargetCommandGroup());
    addSequential(new SetElevatorLevelCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_1));
    addParallel(new IntakeDiskCommandGroup());
    // addSequential(new DriveToLineCommand());
    addSequential(new LineFollowToTargetCommand());
  }

  @Override
  protected void end() {
    Robot.hatchPanelSubsystem.setHatchPanelDeployState(HatchPanelSubsystem.HatchPanelDeployState.DEPLOY);
  }
}
