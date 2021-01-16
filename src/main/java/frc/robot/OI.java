/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.SetRobotDeployStateCommand;
import frc.robot.commands.climber.ToggleClimberCommand;
import frc.robot.commands.disk.CloseIntakeDeployInCommandGroup;
import frc.robot.commands.disk.IntakeDiskCommandGroup;
import frc.robot.commands.disk.ToggleClawCommand;
import frc.robot.commands.drivetrain.SetDriveModeCommand;
import frc.robot.commands.drivetrain.ToggleShiftingCommand;
import frc.robot.commands.groups.AutoMoveElevatorDownCommandGroup;
import frc.robot.commands.groups.AutoMoveElevatorUpCommandGroup;
import frc.robot.commands.groups.DeployHatchPanelCommandGroup;
import frc.robot.commands.rollerbar.IntakeBallCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;
import frc.robot.commands.wrist.ManualMoveWristDownCommand;
import frc.robot.commands.wrist.ManualMoveWristUpCommand;
import frc.robot.subsystems.DrivetrainSubsystem.DriveMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick leftJoy, rightJoy, secondaryJoy, driverStationJoy;

  public OI() {
    /*
    leftJoy = new Joystick(0);
    rightJoy = new Joystick(1);
    secondaryJoy = new Joystick(2);

    setJoystickButtonWhenPressedCommand(leftJoy, 1, new ToggleShiftingCommand());

    setJoystickButtonWhenPressedCommand(rightJoy, 1, new ToggleClawCommand());

    setJoystickButtonWhileHeldCommand(secondaryJoy, 1, new SetDriveModeCommand(DriveMode.TANK));
    setJoystickButtonWhileHeldCommand(secondaryJoy, 2, new SetDriveModeCommand(DriveMode.CHEEZY));
    setJoystickButtonWhileHeldCommand(secondaryJoy, 4, new ManualMoveWristDownCommand());
    setJoystickButtonWhileHeldCommand(secondaryJoy, 6, new ManualMoveWristUpCommand());
    setJoystickButtonWhenPressedCommand(secondaryJoy, 7, new AutoMoveElevatorDownCommandGroup());
    setJoystickButtonWhenPressedCommand(secondaryJoy, 8, new AutoMoveElevatorUpCommandGroup());
    setJoystickButtonWhenPressedCommand(secondaryJoy, 9, new SetRobotDeployStateCommand(Robot.DeployState.HATCH_PANEL));
    setJoystickButtonWhenPressedCommand(secondaryJoy, 10, new SetRobotDeployStateCommand(Robot.DeployState.BALL));
*/
    driverStationJoy = new Joystick(0);

    setJoystickButtonWhenPressedCommand(driverStationJoy, 11, new ToggleShiftingCommand());
    setJoystickButtonWhileHeldCommand(driverStationJoy, 1, new SetDriveModeCommand(DriveMode.TANK));
    setJoystickButtonWhileHeldCommand(driverStationJoy, 2, new SetDriveModeCommand(DriveMode.CHEEZY));

  }

  public double getLeftY() {
    if(driverStationJoy.getRawAxis(1) >= .1 || driverStationJoy.getRawAxis(1) <= -.1){
      return driverStationJoy.getRawAxis(1);
    }else{
      return 0;
    }
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftX() {
    return driverStationJoy.getX();
  }

  // Gets the Y direction of the right drive joystick
  public double getRightY() {

    if(driverStationJoy.getRawAxis(3) >= .1 || driverStationJoy.getRawAxis(3) <= -.1){
      return driverStationJoy.getRawAxis(3);
    }else{
      return 0;
    }
  }

  // Gets the X direction of the right drive joystick
  public double getRightX() {
    return driverStationJoy.getX();
  }


  public double getSecondaryY(){
    return secondaryJoy.getY();
  }

  private void setJoystickButtonWhenPressedCommand(GenericHID joystick, int button, Command command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

  private void setJoystickButtonWhileHeldCommand(GenericHID joystick, int button, Command command) {
    new JoystickButton(joystick, button).whileHeld(command);
  }

};
