/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class DeployBallRocketLevel3 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployBallRocketLevel3() {
    addSequential(new LineFollowCommand());
    addSequential(new TurnToTargetCommandGroup());
    addSequential(new MoveElevatorCommand(RobotMap.ELEVATOR_LEVELS[8]));
    addSequential(new LaunchBallCommand());
  }
}
