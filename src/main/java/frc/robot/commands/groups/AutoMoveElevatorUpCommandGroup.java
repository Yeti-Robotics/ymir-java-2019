/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.IncrementElevatorLevelCommand;
import frc.robot.commands.elevator.MoveElevatorCommand;

public class AutoMoveElevatorUpCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoMoveElevatorUpCommandGroup() {
      addSequential(new IncrementElevatorLevelCommand());
      addSequential(new MoveElevatorCommand(0));
    }
}
