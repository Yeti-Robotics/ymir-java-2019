/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.disk.AutoIntakeDiskCommandGroup;
import frc.robot.subsystems.HatchPanelSubsystem;

public class AutoDeployCommandGroup extends CommandGroup {
    /**
     * Add your docs here.
     */
    public AutoDeployCommandGroup() {

    }

    @Override
    protected void initialize() {
        if (Robot.deployState == Robot.DeployState.HATCH_PANEL) {
            if (Robot.hatchPanelSubsystem.getHatchPanelDeployState() == HatchPanelSubsystem.HatchPanelDeployState.LISTEN) {
                new AutoIntakeDiskCommandGroup().start();
            } else {
                new AutoDeployDiskCommandGroup().start();
            }
        } else {
            new AutoDeployBallCommandGroup().start();
        }
    }
}
