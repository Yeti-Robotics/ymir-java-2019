/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class WristSubsystem extends PIDSubsystem {
  
  private CustomTalon wristTal;


  public WristSubsystem(){
    super(1, 0, 0);
    wristTal = new CustomTalon(RobotMap.WRIST_TALON);

    wristTal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    setAbsoluteTolerance(3);

    
    wristTal.configContinuousCurrentLimit(RobotMap.WRIST_CONT_CURRENT_LIMIT);
    wristTal.configPeakCurrentLimit(RobotMap.WRIST_PEAK_CURRENT_LIMIT);
    wristTal.configPeakCurrentDuration(RobotMap.WRIST_PEAK_CURRENT_DURATION);
  }

  public void printEncoders() {
    System.out.println("Wrist: " + wristTal.getSelectedSensorPosition());
  }

  public void useWrist(double power){
    wristTal.set(power);
  }

  public void resetWristEncoder(){
    wristTal.setSelectedSensorPosition(0);
  }

  public double getWristEncoderValue(){
    return wristTal.getSelectedSensorPosition()*RobotMap.WRIST_DEGREES_PER_PULSE;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    return getWristEncoderValue();
  }

  @Override
  protected void usePIDOutput(double output) {
    wristTal.set(output);
  }
}
