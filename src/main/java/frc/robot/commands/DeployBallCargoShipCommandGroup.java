/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class DeployBallCargoShipCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployBallCargoShipCommandGroup() {
    addSequential(new LineFollowCommand());
    addSequential(new TurnToTargetCommandGroup());
    addSequential(new MoveElevatorCommand(RobotMap.ELEVATOR_LEVELS[3]));
    addSequential(new LaunchBallCommand());
  }
}
