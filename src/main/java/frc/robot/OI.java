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
import frc.robot.commands.disk.CloseIntakeDeployInCommandGroup;
import frc.robot.commands.disk.ListenForDiskCommandGroup;
import frc.robot.commands.drivetrain.LineFollowToTargetCommand;
import frc.robot.commands.drivetrain.ToggleShiftingCommand;
import frc.robot.commands.drivetrain.TurnToTargetCommandGroup;
import frc.robot.commands.elevator.MoveElevatorDownCommand;
import frc.robot.commands.elevator.MoveElevatorUpCommand;
import frc.robot.commands.elevator.ToggleDeployStatesCommand;
import frc.robot.commands.groups.AutoMoveElevatorDownCommandGroup;
import frc.robot.commands.groups.AutoMoveElevatorUpCommandGroup;
import frc.robot.commands.groups.DeployHatchPanelCommandGroup;
import frc.robot.commands.rollerbar.IntakeBallCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;
import frc.robot.commands.wrist.ManualMoveWristDownCommand;
import frc.robot.commands.wrist.ManualMoveWristUpCommand;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private Joystick leftJoy, rightJoy, secondaryJoy, driverStationJoy;

  public OI() {
    // Creates joystick objects for use
    driverStationJoy = new Joystick(RobotMap.DRIVERSTATION_JOYSTICK);
    // leftJoy = new Joystick(RobotMap.LEFT_JOYSTICK);
    // rightJoy = new Joystick(RobotMap.RIGHT_JOYSTICK);
    // secondaryJoy = new Joystick(RobotMap.SECONDARY_JOYSTICK);

    // Left joystick buttons
    setJoystickButtonWhenPressedCommand(driverStationJoy, 11, new ToggleShiftingCommand());

    // Right joystick buttons
    setJoystickButtonWhenPressedCommand(driverStationJoy, 12, new DeployHatchPanelCommandGroup());

    // Secondary Joystick Buttons
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 1, new
    // DeployDiskLevel1CommandGroup());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 2, new
    // IntakeBallCommandGroup());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 3, new
    // DeployDiskLevel2CommandGroup());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 4, new
    // DeployDiskLevel3CommandGroup());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 5, new
    // DeployBallCargoShipCommandGroup());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 6, new
    // DeployBallRocketLevel2CommandGroup());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 7, new
    // DeployBallRocketLevel3());

    setJoystickButtonWhileHeldCommand(driverStationJoy, 6, new IntakeBallCommand());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 6, new TurnToTargetCommandGroup());

    setJoystickButtonWhileHeldCommand(driverStationJoy, 7, new MoveElevatorUpCommand());
    setJoystickButtonWhileHeldCommand(driverStationJoy, 2, new MoveElevatorDownCommand());

    
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 7, new LineFollowToTargetCommand());
    // setJoystickButtonWhileHeldCommand(driverStationJoy, 7, new ManualMoveWristUpCommand());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 8, new AutoMoveElevatorUpCommandGroup());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 9, new ToggleDeployStatesCommand());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 10, new CloseIntakeDeployInCommandGroup());

    setJoystickButtonWhileHeldCommand(driverStationJoy, 1, new LaunchBallCommand());
    // setJoystickButtonWhileHeldCommand(driverStationJoy, 2, new ManualMoveWristDownCommand());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 3, new AutoMoveElevatorDownCommandGroup());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 4, new ListenForDiskCommandGroup());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 5, new CancelCommandsCommand());
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftY() {
    // return leftJoy.getY();
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
