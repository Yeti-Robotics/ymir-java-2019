/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // Drivetrain Encoder Constants
  public static final double WHEEL_DIAMETER = 6; // inches
  public static final double PULSES_PER_REVOLUTION = 4096;
  public static final double DRIVETRAIN_GEAR_RATIO = (64.0 * 36.0) / (20.0 * 12.0);
  public static final double DRIVE_DISTANCE_PER_PULSE = (WHEEL_DIAMETER * Math.PI) / (PULSES_PER_REVOLUTION * DRIVETRAIN_GEAR_RATIO);

  // Wrist Encoder Constants
  public static final double WRIST_GEAR_DIAMETER = 1.7;
  public static final double WRIST_PULSES_PER_REVOLUTION = 1024;
  public static final double WRIST_DEGREES_PER_PULSE = 360 / WRIST_PULSES_PER_REVOLUTION;
  public static final int WRIST_UPPER_ENCODER_LIMIT = 7;
  public static final int WRIST_LOWER_ENCODER_LIMIT = 0;

  // Elevator Encoder Constants
  public static final double ELEVATOR_PULSES_PER_REVOLUTION = 4096;
  public static final double ELEVATOR_SPROCKET_CIRCUMFERENCE = 1.44 * Math.PI;
  public static final double ELEVATOR_HEIGHT_RATIO = 60 / 24.0;
  public static final double ELEVATOR_GEAR_RATIO = 40.0 / 1.0;
  public static final double ELEVATOR_DISTANCE_PER_PULSE = (ELEVATOR_SPROCKET_CIRCUMFERENCE * ELEVATOR_HEIGHT_RATIO) / (ELEVATOR_PULSES_PER_REVOLUTION * ELEVATOR_GEAR_RATIO);

  // Rack Encoder Constants
  public static final double RACK_PULSES_PER_REVOLUTION = 1024;
  public static final double RACK_PITCH_DIAMETER = 1; // inches
  public static final double RACK_DISTANCE_PER_PULSE = Math.PI * RACK_PITCH_DIAMETER / PULSES_PER_REVOLUTION;

  // Joysticks
  public static final int LEFT_JOYSTICK = 0;
  public static final int RIGHT_JOYSTICK = 1;
  public static final int SECONDARY_JOYSTICK = 2;
  public static final int DRIVERSTATION_JOYSTICK = 0;
  public static final int DRIVERSTATION_LEFT_Y_AXIS = 1;
  public static final int DRIVERSTATION_RIGHT_Y_AXIS = 3;

  // Wrist Constants
  // FF = (arm weight) * (distance to arm center of mass) / (motor stall torque) *
  // (number of motors) * (gear ratio) * cos(theta)
  public static final double WRIST_FEED_FORWARD = (3.5 * 13.26) / (0.3182 * 700);
  public static final double WRIST_DEPLOY_ANGLE = 0;
  public static final double WRIST_ROCKET1_ANGLE = 0;
  public static final double WRIST_INTAKE_ANGLE = 0;
  public static final int WRIST_PEAK_CURRENT_LIMIT = 30;
  public static final int WRIST_CONT_CURRENT_LIMIT = 15;
  public static final int WRIST_PEAK_CURRENT_DURATION = 200;

  // Roller Bar Constants
  public static final double ROLLER_SPEED = 1.0;

  // Elevator constants
  public static final double ELEVATOR_REST_LEVEL = 0;
  public static final double ELEVATOR_BALL_ROCKET_LEVEL_1 = ELEVATOR_REST_LEVEL;
  public static final double ELEVATOR_BALL_CARGOSHIP_LEVEL = 16;
  public static final double ELEVATOR_BALL_ROCKET_LEVEL_2 = 23.5;
  public static final double ELEVATOR_BALL_ROCKET_LEVEL_3 = 48.5;
  public static final double ELEVATOR_HATCH_PANEL_LEVEL_1 = 6;
  public static final double ELEVATOR_HATCH_PANEL_LEVEL_2 = 34;
  public static final double ELEVATOR_HATCH_PANEL_LEVEL_3 = 57.7;

  public static final double[] ELEVATOR_HATCH_PANEL_LEVELS = {ELEVATOR_HATCH_PANEL_LEVEL_1, ELEVATOR_HATCH_PANEL_LEVEL_2, ELEVATOR_HATCH_PANEL_LEVEL_3};
  public static final double[] ELEVATOR_BALL_LEVELS = {ELEVATOR_BALL_ROCKET_LEVEL_1, ELEVATOR_BALL_CARGOSHIP_LEVEL, ELEVATOR_BALL_ROCKET_LEVEL_2, ELEVATOR_BALL_ROCKET_LEVEL_3};

  public static final double ELEVATOR_MANUAL_UP_SPEED = .5;
  public static final double ELEVATOR_MANUAL_DOWN_SPEED = -.2;
  public static final double ELEVATOR_STABLE_SPEED = 0.2;
  public static final int ELEVATOR_CONT_CURRENT_LIMIT = 15;
  public static final int ELEVATOR_PEAK_CURRENT_LIMIT = 25;
  public static final int ELEVATOR_PEAK_CURRENT_DURATION = 200;

  public static final double ELEVATOR_MAX_VELOCITY = 58472.2;
  public static final double ELEVATOR_CRUISING_VELOCITY = ELEVATOR_MAX_VELOCITY / 2;
  public static final double ELEVATOR_CRUISING_ACCELERATION = ELEVATOR_MAX_VELOCITY / 2;  
  public static final double ELEVATOR_P = /*(511.5*30) / 81536*/ 0.015;
  public static final double ELEVATOR_D = /*(511.5*6000) / 81536*/ 20;
  public static final double ELEVATOR_OPEN_LOOP_RAMP = .3;
  public static final double ELEVATOR_PEAK_OUTPUT_FORWARD = .6;
  public static final double ELEVATOR_REVERSE_SOFT_LIMIT = 0;
  public static final double ELEVATOR_FORWARD_SOFT_LIMIT = 59;
  public static final double ELEVATOR_TOLERANCE = .25;


  // Rack constants
  public static final int RACK_LEFT_ENCODER_LIMIT = -100;
  public static final int RACK_RIGHT_ENCODER_LIMIT = 100;

  // Talons
  public static final int LEFT_DRIVE_TALON = 3;
  public static final int RIGHT_DRIVE_TALON = 5;
  public static final int ELEVATOR1_TALON = 0;
  public static final int ELEVATOR2_TALON = 4;
  public static final int WRIST_TALON = 1;

  // Victors
  public static final int LEFT_1_VICTOR = 0;
  public static final int LEFT_2_VICTOR = 2;
  public static final int RIGHT_1_VICTOR = 1;
  public static final int RIGHT_2_VICTOR = 5;
  public static final int ELEVATOR_VICTOR = 4;
  public static final int ROLLER_VICTOR = 3;

  // Solenoids
  public static final int[] DRIVE_TRAIN_SHIFT = { 0, 7 };
  public static final int[] DEPLOY_SOLENOID = { 2, 5 };
  public static final int[] INTAKE_SOLENOID = { 1, 6 };

  // Limit Switches
  public static final int LEFT_HATCH_PANEL_LIMIT = 1;
  public static final int RIGHT_HATCH_PANEL_LIMIT = 0;
  public static final int ELEVATOR_UPPER_LIMIT = 2;
  public static final int ELEVATOR_LOWER_LIMIT = 3;
  public static final int BALL_BEAM_BREAK_SENSOR = 5;

  // Vision Constants
  public static final int IMAGE_WIDTH = 320;
  public static final double TAPE_BOUND_WIDTH_INCH = 3.3;
  public static final double TARGET_BOUND_WIDTH_INCH = 14.6;
  public static final double FOCAL_LENGTH = (22 * 55) / TAPE_BOUND_WIDTH_INCH;
  public static final int JEVOIS_BAUD_RATE = 115200;
  public static final int FOV = 65;
  public static final double CAMERA_BUMPER_BUFFER = 10.0;
  // Line Following Constants
  public static final int LEFT_LINE_FOLLOWER_PORT = 0;
  public static final int CENTER_LINE_FOLLOWER_PORT = 1;
  public static final int RIGHT_LINE_FOLLOWER_PORT = 2;

  // Dashboard constants
  public static final String NETWORK_TABLES_GYRO = "/drive/navx/yaw";
  public static final String NETWORK_TABLES_ARM_ENCODER = "/arm/encoder";
  public static final String NETWORK_TABLES_ELEVATOR_ENCODER = "/elevator/encoder";
  public static final String NETWORK_TABLES_AUTO_MODES = "/autonomous/modes";
  public static final String NETWORK_TABLES_AUTO__CUREENTLY_SELECTED = "/currentlySelectedMode";
  public static final String NETWORK_TABLES_VISION_TARGET_FOUND = "/vision/targetFound";
  public static final String NETWORK_TABLES_LINEFOLLOWER_LEFT = "/lineFollower/left";
  public static final String NETWORK_TABLES_LINEFOLLOWER_CENTER = "/lineFollower/center";
  public static final String NETWORK_TABLES_LINEFOLLOWER_RIGHT = "/lineFollower/right";
  public static final String NETWORK_TABLES_ARM_BALL = "/arm/ball";
}
