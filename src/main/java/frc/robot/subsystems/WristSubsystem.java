/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.wrist.JoystickWristCommand;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {
  
  private CustomTalon wristTal;


  public WristSubsystem(){
    
    wristTal = new CustomTalon(RobotMap.WRIST_TALON);

    wristTal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    

    
    wristTal.configContinuousCurrentLimit(RobotMap.WRIST_CONT_CURRENT_LIMIT);
    wristTal.configPeakCurrentLimit(RobotMap.WRIST_PEAK_CURRENT_LIMIT);
    wristTal.configPeakCurrentDuration(RobotMap.WRIST_PEAK_CURRENT_DURATION);
    wristTal.enableCurrentLimit(true);

    wristTal.configForwardSoftLimitThreshold(RobotMap.WRIST_LOWER_ENCODER_LIMIT);
    wristTal.configReverseSoftLimitThreshold(RobotMap.WRIST_UPPER_ENCODER_LIMIT);
  }


  public void printEncoders() {
    System.out.println("Wrist: " + getWristEncoderValue());
  }

  public void useWrist(double power){
    wristTal.set(ControlMode.PercentOutput, power);
  }

  public void stopWrist(){
    wristTal.set(ControlMode.PercentOutput, 0);

  }

  public void resetWristEncoder(){
    wristTal.setSelectedSensorPosition(0);
  }

  public double getWristEncoderValue(){
    return wristTal.getSelectedSensorPosition() * RobotMap.WRIST_DEGREES_PER_PULSE;
  }


  public void initDefaultCommand() {
    setDefaultCommand(new JoystickWristCommand());
  }

}
