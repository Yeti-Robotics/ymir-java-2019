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
import frc.robot.commands.elevator.MoveElevatorCommand;

public class ListenForDiskCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ListenForDiskCommandGroup() {
    // addParallel(new TurnToTargetCommandGroup());
    // addParallel(new MoveElevatorCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_1));
    addSequential(new OpenIntakeCommand());
    addSequential(new DeployOutCommand());
    // addSequential(new LineFollowToTargetCommand());
    addSequential(new IntakeDiskCommandGroup());
  }

  // @Override
  // protected void interrupted() {
  //   new IntakeDiskCommandGroup().start();
  // }
}
