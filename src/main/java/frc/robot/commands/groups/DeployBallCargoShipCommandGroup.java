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
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.SetElevatorLevelCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;
import frc.robot.controls.VisionProcessor;

public class DeployBallCargoShipCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployBallCargoShipCommandGroup() {
    addSequential(new LineFollowToTargetCommand());
    addSequential(new TurnToTargetCommandGroup());
    addSequential(new SetElevatorLevelCommand(0));
    addSequential(new LaunchBallCommand());
  }
}