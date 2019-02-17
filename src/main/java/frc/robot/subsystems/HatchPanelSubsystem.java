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
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DeactivateIntakeCommand;
import frc.robot.commands.SenseHatchPanelCommand;

/**
 * Add your docs here.
 */
public class HatchPanelSubsystem extends Subsystem {
  private DoubleSolenoid intake, deploy;
  private DigitalInput leftSwitch, rightSwitch;

  public HatchPanelSubsystem() {
    intake = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID[0], RobotMap.INTAKE_SOLENOID[1]);
    deploy = new DoubleSolenoid(RobotMap.DEPLOY_SOLENOID[0], RobotMap.DEPLOY_SOLENOID[1]);
    leftSwitch = new DigitalInput(RobotMap.LEFT_HATCH_PANEL_LIMIT);
    rightSwitch = new DigitalInput(RobotMap.RIGHT_HATCH_PANEL_LIMIT);
  }

  public boolean getLeftSwitch() {
    return leftSwitch.get();
  }

  public boolean getRightSwitch() {
    return rightSwitch.get();
  }

  public void activateIntake() {
    intake.set(Value.kReverse);
  }
  public void deactivateIntake() {
    intake.set(Value.kForward);
  }
  public void activateDeploy() {
    deploy.set(Value.kReverse);
  }
  public void deactivateDeploy() {
    deploy.set(Value.kForward);
  }


  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new SenseHatchPanelCommand());
  }

}
