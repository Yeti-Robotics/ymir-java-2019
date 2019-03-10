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
import frc.robot.commands.drivetrain.LineFollowCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;
import frc.robot.controls.VisionProcessor;

public class DeployBallRocketLevel3CommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployBallRocketLevel3CommandGroup() {
    addSequential(new LineFollowCommand(VisionProcessor.getAverageDistance(Robot.latestContours[0], Robot.latestContours[1])));
    addSequential(new TurnToTargetCommandGroup());
    addSequential(new MoveElevatorCommand(RobotMap.ELEVATOR_BALL_ROCKET_LEVEL_3));
    addSequential(new LaunchBallCommand());
  }
}
