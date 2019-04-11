/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.controls.CustomTalon;

/**
 * Add your docs here.
 */
public class RollerBarSubsystem extends Subsystem {
  private CustomTalon wristRollerTalon;
  private DigitalInput beamBreakSensor;

  public RollerBarSubsystem() {
    wristRollerTalon = new CustomTalon(1);
    beamBreakSensor = new DigitalInput(RobotMap.BALL_BEAM_BREAK_SENSOR);
  }

  public void rollIn() {
    wristRollerTalon.set(ControlMode.PercentOutput, -RobotMap.ROLLER_SPEED);
  }

  public void rollOut() {
    wristRollerTalon.set(ControlMode.PercentOutput, RobotMap.ROLLER_SPEED);
  }

  public void rollStop() {
    wristRollerTalon.set(ControlMode.PercentOutput, 0);
  }

  public boolean getBeamBreakSensor() {
    return beamBreakSensor.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
