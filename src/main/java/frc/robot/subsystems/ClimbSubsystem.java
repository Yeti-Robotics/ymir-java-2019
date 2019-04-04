package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimbSubsystem extends Subsystem {

    private DoubleSolenoid climber;

    public ClimbSubsystem() {
        climber = new DoubleSolenoid(RobotMap.CLIMBER_SOLENOID[0], RobotMap.CLIMBER_SOLENOID[1]);
    }

    public void extendClimber(){
        climber.set(DoubleSolenoid.Value.kForward);
    }

    public void retractClimber(){
        climber.set(DoubleSolenoid.Value.kReverse);
    }

    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

    public Value getClimberStatus(){
        return climber.get();
    }
}

