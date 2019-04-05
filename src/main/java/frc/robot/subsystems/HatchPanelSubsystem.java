/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchPanelSubsystem extends Subsystem {
  private DoubleSolenoid intake, deploy;
  private DigitalInput leftSwitch, rightSwitch;
  private HatchPanelDeployState hatchPanelDeployState;
  public enum HatchPanelDeployState {
    LISTEN, DEPLOY
  }

  public HatchPanelSubsystem() {
    intake = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID[0], RobotMap.INTAKE_SOLENOID[1]);
    deploy = new DoubleSolenoid(RobotMap.DEPLOY_SOLENOID[0], RobotMap.DEPLOY_SOLENOID[1]);
    leftSwitch = new DigitalInput(RobotMap.LEFT_HATCH_PANEL_LIMIT);
    rightSwitch = new DigitalInput(RobotMap.RIGHT_HATCH_PANEL_LIMIT);
    hatchPanelDeployState = HatchPanelDeployState.DEPLOY;
  }

  public HatchPanelDeployState getHatchPanelDeployState() {
    return hatchPanelDeployState;
  }

  public void setHatchPanelDeployState(HatchPanelDeployState hatchPanelDeployState) {
    this.hatchPanelDeployState = hatchPanelDeployState;
  }

  public boolean getLeftSwitch() {
    return !leftSwitch.get();
  }

  public boolean getRightSwitch() {
    return !rightSwitch.get();
  }

  public void closeIntake() {
    intake.set(Value.kReverse);
  }
  public void openIntake() {
    intake.set(Value.kForward);
  }
  public void deployOut() {
    deploy.set(Value.kForward);
  }
  public void deployIn() {
    deploy.set(Value.kReverse);
  }

  public Value getIntakeState() {
    return intake.get();
  }


  @Override
  public void initDefaultCommand() {
    
  }

}
