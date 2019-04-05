package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.drivetrain.DriveToLineCommand;
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.SetElevatorLevelCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;

public class AutoDeployBallCommandGroup extends CommandGroup {
    public AutoDeployBallCommandGroup() {
        addParallel(new MoveElevatorCommand());
        addSequential(new TurnToTargetCommandGroup(), 0.75);
        addSequential(new DriveToLineCommand());
        addSequential(new LineFollowToTargetCommand());
        addSequential(new LaunchBallCommand());
        addSequential(new DriveForDistanceCommand(5,-.4,-.4));
        addSequential(new SetElevatorLevelCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_1));
    }
}
