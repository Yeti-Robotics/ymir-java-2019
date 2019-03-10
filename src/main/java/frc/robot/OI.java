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
import frc.robot.commands.disk.OpenIntakeCommand;
import frc.robot.commands.disk.ListenForDiskCommandGroup;
import frc.robot.commands.disk.DeployInCommand;
import frc.robot.commands.disk.DeployOutCommand;
import frc.robot.commands.disk.CloseIntakeCommand;
import frc.robot.commands.disk.CloseIntakeDeployInCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.drivetrain.DriveForDistancePIDCommand;
import frc.robot.commands.drivetrain.ResetDriveEncoderCommand;
import frc.robot.commands.elevator.MoveElevatorCommand;
import frc.robot.commands.elevator.MoveElevatorDownCommand;
import frc.robot.commands.elevator.MoveElevatorUpCommand;
import frc.robot.commands.groups.DeployDiskLevel1CommandGroup;
import frc.robot.commands.groups.DeployHatchPanelCommandGroup;
import frc.robot.commands.rollerbar.IntakeBallCommand;
import frc.robot.commands.rollerbar.LaunchBallCommand;
import frc.robot.commands.shifting.DriveTrainHighShiftCommand;
import frc.robot.commands.shifting.DriveTrainLowShiftCommand;
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
    setJoystickButtonWhenPressedCommand(driverStationJoy, 11, new DriveTrainHighShiftCommand());

    // Right joystick buttons
    setJoystickButtonWhenPressedCommand(driverStationJoy, 12, new DriveTrainLowShiftCommand());

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

    // setJoystickButtonWhileHeldCommand(driverStationJoy, 1, new IntakeBallCommand());
    // setJoystickButtonWhileHeldCommand(driverStationJoy, 2, new LaunchBallCommand());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 1, new MoveElevatorCommand(RobotMap.ELEVATOR_REST_LEVEL));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 2, new MoveElevatorCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_1));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 3, new MoveElevatorCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_2));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 4, new MoveElevatorCommand(RobotMap.ELEVATOR_HATCH_PANEL_LEVEL_3));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 5, new DeployHatchPanelCommandGroup());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 6, new ListenForDiskCommandGroup());
    setJoystickButtonWhenPressedCommand(driverStationJoy, 7, new MoveElevatorCommand(RobotMap.ELEVATOR_BALL_ROCKET_LEVEL_1));
    setJoystickButtonWhenPressedCommand(driverStationJoy, 8, new MoveElevatorCommand(RobotMap.ELEVATOR_REST_LEVEL));
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 3, new ResetDriveEncoderCommand());
    // setJoystickButtonWhileHeldCommand(driverStationJoy, 4, new MoveElevatorUpCommand());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 5, new DeployHatchPanelCommandGroup());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 6, new ListenForDiskCommandGroup());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 6, new MoveElevatorCommand(60000));
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 7, new CloseIntakeDeployInCommandGroup());
    // setJoystickButtonWhileHeldCommand(driverStationJoy, 8, new MoveElevatorDownCommand());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 3, new
    // LineFollowCommand());
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 5, new
    // ResetEncodersCommand());
    // // setJoystickButtonWhenPressedCommand(secondaryJoy, 4, new
    // CorrectAzimuthCommand());
    // setJoystickButtonWhenPressedCommand(driverStationJoy, 7, new DriveForDistanceCommand(120, 0.5, 0.5));
    // setJoystickButtonWhenPressedCommand(secondaryJoy, 8, new
    // TurnToTargetCommandGroup());
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
