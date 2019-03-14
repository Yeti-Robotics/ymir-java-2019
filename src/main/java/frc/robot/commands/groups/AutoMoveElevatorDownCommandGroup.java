/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.DecrementElevatorLevelCommand;
import frc.robot.commands.elevator.MoveElevatorCommand;

public class AutoMoveElevatorDownCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoMoveElevatorDownCommandGroup() {
      addSequential(new DecrementElevatorLevelCommand());
      addSequential(new MoveElevatorCommand(0));
    }
}
