package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.DriveToLineCommand;
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;

public class AutoDeployDiskCommandGroup extends CommandGroup {
    public AutoDeployDiskCommandGroup() {
        addParallel(new MoveElevatorCommand());
        addSequential(new TurnToTargetCommandGroup(), 0.75);
        addSequential(new DriveToLineCommand());
        addSequential(new LineFollowToTargetCommand());
        addSequential(new DeployHatchPanelCommandGroup());
    }
}
