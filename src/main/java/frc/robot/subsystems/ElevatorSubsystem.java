/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
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
  public CustomTalon elevator1Talon, elevator2Talon;
  private DigitalInput upperLimit, lowerLimit;
  private double motionMagicTarget;

  public ElevatorSubsystem() {
    super(0.00005, .000001, 0, 0.3);
    elevator1Talon = new CustomTalon(RobotMap.ELEVATOR1_TALON);
    elevator2Talon = new CustomTalon(RobotMap.ELEVATOR2_TALON);
    upperLimit = new DigitalInput(RobotMap.ELEVATOR_UPPER_LIMIT);
    lowerLimit = new DigitalInput(RobotMap.ELEVATOR_LOWER_LIMIT);

    elevator1Talon.configVoltageCompSaturation(12);
    elevator2Talon.configVoltageCompSaturation(12);

    elevator1Talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 2, 30);
    elevator1Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    elevator2Talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 2, 30);
    elevator2Talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    elevator2Talon.follow(elevator1Talon);

    elevator1Talon.setNeutralMode(NeutralMode.Brake);
    elevator2Talon.setNeutralMode(NeutralMode.Brake);

    elevator1Talon.configContinuousCurrentLimit(RobotMap.ELEVATOR_CONT_CURRENT_LIMIT);
    elevator1Talon.configPeakCurrentLimit(RobotMap.ELEVATOR_PEAK_CURRENT_LIMIT);
    elevator1Talon.configPeakCurrentDuration(RobotMap.ELEVATOR_PEAK_CURRENT_DURATION);
    elevator1Talon.enableCurrentLimit(true);

    elevator2Talon.configContinuousCurrentLimit(RobotMap.ELEVATOR_CONT_CURRENT_LIMIT);
    elevator2Talon.configPeakCurrentLimit(RobotMap.ELEVATOR_PEAK_CURRENT_LIMIT);
    elevator2Talon.configPeakCurrentDuration(RobotMap.ELEVATOR_PEAK_CURRENT_DURATION);
    elevator1Talon.enableCurrentLimit(true);

    elevator1Talon.setInverted(true);
    
    elevator1Talon.configOpenloopRamp(RobotMap.ELEVATOR_OPEN_LOOP_RAMP);
        
    setOutputRange(RobotMap.ELEVATOR_MANUAL_DOWN_SPEED, 1);
    setAbsoluteTolerance(250);
    disable();

    resetElevatorEncoder();

    elevator1Talon.config_kP(0, RobotMap.ELEVATOR_P);
    elevator1Talon.config_kI(0, 0);
    elevator1Talon.config_kD(0, RobotMap.ELEVATOR_D);
    elevator1Talon.configMotionCruiseVelocity((int) (RobotMap.ELEVATOR_INITIAL_VELOCITY));
    elevator1Talon.configMotionAcceleration((int) (RobotMap.ELEVATOR_INITIAL_ACCELERATION));
    elevator1Talon.configPeakOutputReverse(RobotMap.ELEVATOR_MANUAL_DOWN_SPEED);
    elevator1Talon.configAllowableClosedloopError(0, convertInchesToCounts(RobotMap.ELEVATOR_TOLERANCE));
    elevator1Talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
    elevator1Talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);
    elevator1Talon.configPeakOutputForward(RobotMap.ELEVATOR_PEAK_OUTPUT_FORWARD);
    elevator1Talon.configForwardSoftLimitThreshold(convertInchesToCounts(RobotMap.ELEVATOR_FORWARD_SOFT_LIMIT));
    elevator1Talon.configReverseSoftLimitThreshold(convertInchesToCounts(RobotMap.ELEVATOR_REVERSE_SOFT_LIMIT));
    elevator1Talon.configReverseSoftLimitEnable(true);
    elevator1Talon.configForwardSoftLimitEnable(true);
    elevator1Talon.configClosedloopRamp(RobotMap.ELEVATOR_OPEN_LOOP_RAMP);
  }

  public int convertInchesToCounts(double inches) {
    return (int) (inches / RobotMap.ELEVATOR_DISTANCE_PER_PULSE);
  }

  public boolean getUpperLimit() {
    return upperLimit.get();
  }

  public boolean getLowerLimit() {
    return lowerLimit.get();
  }

  public void elevatorStop() {
    elevator1Talon.set(RobotMap.ELEVATOR_STABLE_SPEED);
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

  public void setPosition(double setpoint) {
    elevator1Talon.set(ControlMode.Position, setpoint / RobotMap.ELEVATOR_DISTANCE_PER_PULSE);
  }
  
  public void setMotionMagic(double setpoint) {
    motionMagicTarget = setpoint;
    elevator1Talon.set(ControlMode.MotionMagic, convertInchesToCounts(setpoint));
  }

  public boolean motionMagicOnTarget() {
    return Math.abs(elevator1Talon.getSelectedSensorPosition() - convertInchesToCounts(motionMagicTarget)) <= convertInchesToCounts(RobotMap.ELEVATOR_TOLERANCE);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void displayEncoderInfo() {
    SmartDashboard.putNumber("raw encoder value", elevator1Talon.getSelectedSensorPosition());
    SmartDashboard.putNumber("current target", convertInchesToCounts(motionMagicTarget));
    SmartDashboard.putNumber("tolerance", convertInchesToCounts(1));
    SmartDashboard.putNumber("error", elevator1Talon.getSelectedSensorPosition() - convertInchesToCounts(motionMagicTarget));
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
