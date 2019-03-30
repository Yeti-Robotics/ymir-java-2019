/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToTargetCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DriveToTargetCommandGroup() {
    addSequential(new DriveToLineCommand());
    addSequential(new LineFollowToTargetCommand());
  }
}
