/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends PIDSubsystem {
  private CustomTalon elevatorTalon;
  private VictorSPX elevatorVictor;
  private DigitalInput upperLimit, lowerLimit;

  public ElevatorSubsystem() {
    super(1, 0, 0);
    elevatorTalon = new CustomTalon(RobotMap.ELEVATOR_TALON);
    elevatorVictor = new VictorSPX(RobotMap.ELEVATOR_VICTOR);
    upperLimit = new DigitalInput(RobotMap.ELEVATOR_UPPER_LIMIT);
    lowerLimit = new DigitalInput(RobotMap.ELEVATOR_LOWER_LIMIT);
    elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    elevatorVictor.follow(elevatorTalon);
    elevatorTalon.setNeutralMode(NeutralMode.Brake);
    elevatorVictor.setNeutralMode(NeutralMode.Brake);
    elevatorTalon.configContinuousCurrentLimit(RobotMap.ELEVATOR_CONT_CURRENT_LIMIT);
    elevatorTalon.configPeakCurrentLimit(RobotMap.ELEVATOR_PEAK_CURRENT_LIMIT);
    elevatorTalon.configPeakCurrentDuration(RobotMap.ELEVATOR_PEAK_CURRENT_DURATION);
    elevatorTalon.setInverted(true);
    disable();
  }

  public boolean getUpperLimit() {
    return upperLimit.get();
  }

  public boolean getLowerLimit() {
    return lowerLimit.get();
  }

  public void elevatorStop() {
    elevatorTalon.set(0);
  }

  public void resetElevatorEncoder() {
    elevatorTalon.setSelectedSensorPosition(0);
  }

  public double getElevatorEncoder() {
    return elevatorTalon.getSelectedSensorPosition()*RobotMap.ELEVATOR_DISTANCE_PER_PULSE;
  }

  public void moveElevatorUp() {
    elevatorTalon.set(RobotMap.ELEVATOR_MANUAL_SPEED);
  }

  public void moveElevatorDown() {
    elevatorTalon.set(-RobotMap.ELEVATOR_MANUAL_SPEED);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    return getElevatorEncoder();
  }

  @Override
  protected void usePIDOutput(double output) {
    elevatorTalon.set(output);
  }
}
