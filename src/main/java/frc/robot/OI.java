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
import frc.robot.commands.CancelCommandsCommand;
import frc.robot.commands.SetRobotDeployStateCommand;
import frc.robot.commands.climber.ToggleClimberCommand;
import frc.robot.commands.disk.CloseIntakeDeployInCommandGroup;
import frc.robot.commands.disk.AutoIntakeDiskCommandGroup;
import frc.robot.commands.drivetrain.ToggleShiftingCommand;
import frc.robot.commands.ToggleDeployStatesCommand;
import frc.robot.commands.elevator.SetElevatorDeployLevelCommand;
import frc.robot.commands.groups.AutoDeployCommandGroup;
import frc.robot.commands.groups.AutoMoveElevatorDownCommandGroup;
import frc.robot.commands.groups.AutoMoveElevatorUpCommandGroup;
import frc.robot.commands.groups.DeployDiskLevel1CommandGroup;
import frc.robot.commands.rollerbar.IntakeBallCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick leftJoy, rightJoy, secondaryJoy, driverStationJoy;

  public OI() {
    // Creates joystick objects for use
    driverStationJoy = new Joystick(RobotMap.DRIVERSTATION_JOYSTICK);

    // Left joystick buttons
    setJoystickButtonWhenPressedCommand(driverStationJoy, 11, new ToggleShiftingCommand());

    // Right joystick buttons
    setJoystickButtonWhenPressedCommand(driverStationJoy, 12, new AutoDeployCommandGroup());

    // Secondary Joystick Buttons
    setJoystickButtonWhenPressedCommand(driverStationJoy, 6, new IntakeBallCommand());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 7, new SetElevatorDeployLevelCommand(0));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 8, new SetElevatorDeployLevelCommand(1));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 9, new SetElevatorDeployLevelCommand(2));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 10, new CloseIntakeDeployInCommandGroup());

    setJoystickButtonWhileHeldCommand(driverStationJoy, 1, new LaunchBallCommand());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 2, new ToggleClimberCommand());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 3, new SetRobotDeployStateCommand(Robot.DeployState.BALL));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 4, new SetRobotDeployStateCommand(Robot.DeployState.HATCH_PANEL));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 5, new CancelCommandsCommand());
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftY() {
    return driverStationJoy.getRawAxis(RobotMap.DRIVERSTATION_LEFT_Y_AXIS);
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftX() {
    return driverStationJoy.getRawAxis(0);
  }
  

  // Gets the Y direction of the right drive joystick
  public double getRightY() {
    // return rightJoy.getY();
    return driverStationJoy.getRawAxis(RobotMap.DRIVERSTATION_RIGHT_Y_AXIS);
  }

  // Gets the X direction of the right drive joystick
  public double getRightX() {
    return rightJoy.getX();
  }

  private void setJoystickButtonWhenPressedCommand(GenericHID joystick, int button, Command command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

  private void setJoystickButtonWhileHeldCommand(GenericHID joystick, int button, Command command) {
    new JoystickButton(joystick, button).whileHeld(command);
  }

};
