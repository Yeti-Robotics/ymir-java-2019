package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.drivetrain.DriveToLineCommand;
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.SetElevatorLevelCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;
import frc.robot.subsystems.HatchPanelSubsystem;

public class AutoDeployDiskCommandGroup extends CommandGroup {
    public AutoDeployDiskCommandGroup() {
        addParallel(new MoveElevatorCommand());
        addSequential(new TurnToTargetCommandGroup(), 0.75);
        // addSequential(new DriveToLineCommand());
        addSequential(new LineFollowToTargetCommand());
        addSequential(new DeployHatchPanelCommandGroup());
        addSequential(new DriveForDistanceCommand(5,-.4,-.4));
        addSequential(new SetElevatorLevelCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_1));
    }
    @Override
    protected void end() {
        Robot.hatchPanelSubsystem.setHatchPanelDeployState(HatchPanelSubsystem.HatchPanelDeployState.LISTEN);
    }
}
