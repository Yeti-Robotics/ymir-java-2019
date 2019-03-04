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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends PIDSubsystem {
  private CustomTalon elevator1Talon, elevator2Talon;
  private DigitalInput upperLimit, lowerLimit;

  public ElevatorSubsystem() {
    super(0.00005, .000001, 0, 0.3);
    elevator1Talon = new CustomTalon(RobotMap.ELEVATOR1_TALON);
    elevator2Talon = new CustomTalon(RobotMap.ELEVATOR2_TALON);
    upperLimit = new DigitalInput(RobotMap.ELEVATOR_UPPER_LIMIT);
    lowerLimit = new DigitalInput(RobotMap.ELEVATOR_LOWER_LIMIT);
    elevator1Talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    elevator2Talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    elevator2Talon.follow(elevator1Talon);
    elevator1Talon.setNeutralMode(NeutralMode.Brake);
    elevator2Talon.setNeutralMode(NeutralMode.Brake);
    elevator1Talon.configContinuousCurrentLimit(RobotMap.ELEVATOR1_CONT_CURRENT_LIMIT);
    elevator1Talon.configPeakCurrentLimit(RobotMap.ELEVATOR1_PEAK_CURRENT_LIMIT);
    elevator1Talon.configPeakCurrentDuration(RobotMap.ELEVATOR1_PEAK_CURRENT_DURATION);
    elevator2Talon.configContinuousCurrentLimit(RobotMap.ELEVATOR2_CONT_CURRENT_LIMIT);
    elevator2Talon.configPeakCurrentLimit(RobotMap.ELEVATOR2_PEAK_CURRENT_LIMIT);
    elevator2Talon.configPeakCurrentDuration(RobotMap.ELEVATOR2_PEAK_CURRENT_DURATION);
    elevator1Talon.setInverted(true);

    setOutputRange(RobotMap.ELEVATOR_MANUAL_DOWN_SPEED, 1);
    setAbsoluteTolerance(250);
    disable();

    resetElevatorEncoder();
  }

  public boolean getUpperLimit() {
    return upperLimit.get();
  }

  public boolean getLowerLimit() {
    return lowerLimit.get();
  }

  public void elevatorStop() {
    elevator1Talon.set(0);
  }

  public void resetElevatorEncoder() {
    elevator1Talon.setSelectedSensorPosition(0);
  }

  public double getElevatorEncoder() {
    return elevator1Talon.getSelectedSensorPosition() * (RobotMap.ELEVATOR_DISTANCE_PER_PULSE);
  }

  public void printEncoders() {
    System.out.println("Elevator position: " + getElevatorEncoder());
  }

  public void moveElevatorUp() {
    SmartDashboard.putNumber("elevator current", elevator1Talon.getOutputCurrent());
    elevator1Talon.set(RobotMap.ELEVATOR_MANUAL_UP_SPEED);
  }

  public void moveElevatorDown() {
    SmartDashboard.putNumber("elevator current", elevator1Talon.getOutputCurrent());
    elevator1Talon.set(RobotMap.ELEVATOR_MANUAL_DOWN_SPEED);
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
    elevator1Talon.set(output);
  }

  public void moveElevator(double speed) {
    elevator1Talon.set(speed);
  }
}
