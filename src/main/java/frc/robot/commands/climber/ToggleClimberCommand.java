package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class ToggleClimberCommand extends Command {
    public ToggleClimberCommand() {
        requires(Robot.climbSubsystem);
    }

    @Override
    protected void initialize() {
        if (Robot.climbSubsystem.getClimberStatus() == Value.kForward) {
            Robot.climbSubsystem.retractClimber();
        } else {
            Robot.climbSubsystem.extendClimber();
        }
    }


    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
