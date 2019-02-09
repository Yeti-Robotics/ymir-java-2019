/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class DiscRackSubsystem extends Subsystem {
 
  private CustomTalon rackTal;


  public DiscRackSubsystem() {
    

    rackTal = new CustomTalon(RobotMap.Rack_TALON);

    rackTal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

  }

  public void moveRack(double power){
    rackTal.set(ControlMode.PercentOutput, power);
  }

  public void resetRackEncoder(){
    rackTal.setSelectedSensorPosition(0);
  }

  public double getRackEncoderValue(){
    return rackTal.getSelectedSensorPosition();
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }



}
