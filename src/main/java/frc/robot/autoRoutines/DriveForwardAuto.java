package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.shifting.DriveTrainHighShiftCommand;
import frc.robot.commands.shifting.DriveTrainLowShiftCommand;

public class DriveForwardAuto extends CommandGroup {

    public DriveForwardAuto() {
        addSequential(new DriveTrainLowShiftCommand());
        addSequential(new DriveForDistanceCommand(30, 0.5, .5));
        addSequential(new DriveTrainHighShiftCommand());
        addSequential(new DriveForDistanceCommand(30, 0.5, .5));

    }
}