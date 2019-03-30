/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.SetElevatorLevelCommand;
import frc.robot.commands.rollerbar.IntakeBallCommand;
import frc.robot.commands.wrist.WristDownCommand;
import frc.robot.commands.wrist.WristUpCommand;

public class IntakeBallCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeBallCommandGroup() {
    addSequential(new WristDownCommand());
    addSequential(new IntakeBallCommand());
    addSequential(new WristUpCommand());
    addSequential(new SetElevatorLevelCommand(RobotMap.ELEVATOR_REST_LEVEL));
  }
}
