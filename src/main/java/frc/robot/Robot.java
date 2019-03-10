/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// this is a test comment

package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.Contour;
import frc.robot.controls.JeVois;
import frc.robot.controls.VisionProcessor;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HatchPanelSubsystem;
import frc.robot.subsystems.RollerBarSubsystem;
import frc.robot.subsystems.ShiftGearsSubsystem;
import frc.robot.subsystems.WristSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static OI oi;
  public static NetworkTable networkTable;

  // Instantiates the subsystems
  public static DrivetrainSubsystem drivetrainSubsystem;
  public static ShiftGearsSubsystem shiftGearsSubsystem;
  public static HatchPanelSubsystem hatchPanelSubsystem;
  public static ElevatorSubsystem elevatorSubsystem;
  public static WristSubsystem wristSubsystem;
  public static RollerBarSubsystem rollerBarSubsystem;
  public static boolean runVisionThread = false;
  public static JeVois jevois;
  public static List<Contour[]> contourList = new ArrayList<>();
  public static Contour[] latestContours = {
    new Contour("0", "0", "0", "0", "0"), new Contour("0", "0", "0", "0", "0")
  };
  static Object imgLock = new Object();
  public static String[] autoModes = {"Test 1"};

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    hatchPanelSubsystem = new HatchPanelSubsystem();
    drivetrainSubsystem = new DrivetrainSubsystem();
    shiftGearsSubsystem = new ShiftGearsSubsystem();
    elevatorSubsystem = new ElevatorSubsystem();
    wristSubsystem = new WristSubsystem();
    rollerBarSubsystem = new RollerBarSubsystem();
    jevois = new JeVois();
    oi = new OI();
    networkTable = NetworkTableInstance.getDefault().getTable("SmartDashboard");
    // UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(0);
    // cam.setVideoMode(VideoMode.PixelFormat.kMJPEG, 200, 150, 30);
    // cam.setBrightness(50);
    
    UsbCamera jevoisView = CameraServer.getInstance().startAutomaticCapture(0);
    jevoisView.setVideoMode(VideoMode.PixelFormat.kYUYV, 320, 240, 30);


    new Timer().scheduleAtFixedRate(new TimerTask(){
      long lastLoop = System.currentTimeMillis();
    
      @Override
      public void run() {
          Contour[] contours = jevois.parseStream();
          if (contours != null) {
            contourList.add(contours);
            latestContours = contours;
            // System.out.println(Arrays.toString(latestContours));
            if (contourList.size() > 10){
              contourList.remove(0);
            }
            lastLoop = System.currentTimeMillis();
          }
          if (System.currentTimeMillis() - lastLoop > 3000) {
            latestContours = new Contour[] {
              new Contour("0", "0", "0", "0", "0"), new Contour("0", "0", "0", "0", "0")
            };
          }
      }
    }, 20L, 20L);
    
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);


    networkTable.getEntry(RobotMap.NETWORK_TABLES_AUTO_MODES).setStringArray(autoModes);
  }

  public static void enableVision() {
    // synchronized (imgLock) {
      runVisionThread = true;
    // }
  }

  public static void disableVision() {
    // synchronized (imgLock) {
      runVisionThread = false;
    // }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // System.out.println(jevois.getLeftDistance() + "," + jevois.getRightDistance());
    // discRackSubsystem.printEncoders();
    // elevatorSubsystem.printEncoders();
    // wristSubsystem.printEncoders();
    SmartDashboard.putNumber("elevator speed", Robot.elevatorSubsystem.getPIDController().get());
    SmartDashboard.putNumber("Elevator height", elevatorSubsystem.getElevatorEncoder());
    SmartDashboard.putNumber("Left Encoder Distance", drivetrainSubsystem.getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder Distance", drivetrainSubsystem.getRightEncoderDistance());
    SmartDashboard.putNumber("Avg Encoder Distance", drivetrainSubsystem.getAvgEncoderDistance());
    SmartDashboard.putNumber("Left Encoder Rate", drivetrainSubsystem.getLeftEncoderRate());
    SmartDashboard.putNumber("Right Encoder Rate", drivetrainSubsystem.getRightEncoderRate());
    SmartDashboard.putBoolean("Left Line Follow Voltage", drivetrainSubsystem.getLeftLineFollower());
    SmartDashboard.putBoolean("Center Line Follow Voltage", drivetrainSubsystem.getCenterLineFollower());
    SmartDashboard.putBoolean("Right Line Follow Voltage", drivetrainSubsystem.getRightLineFollower());
    SmartDashboard.putNumber("Elevator raw value", elevatorSubsystem.elevator1Talon.getSelectedSensorPosition());
    
    if (latestContours != null) {

    // System.out.println("left: " + VisionProcessor.getLeftDistance(latestContours[0], latestContours[1]) + ", right: " + VisionProcessor.getRightDistance(latestContours[0], latestContours[1]));
    // System.out.println(VisionProcessor.boundRect(latestContours[0], latestContours[1]).width);
    System.out.println(VisionProcessor.getAverageDistance(latestContours[0], latestContours[1]));
    }

    networkTable.getEntry(RobotMap.NETWORK_TABLES_GYRO).setDouble(drivetrainSubsystem.getAngle());
    networkTable.getEntry(RobotMap.NETWORK_TABLES_ARM_ENCODER).setDouble(wristSubsystem.getWristEncoderValue());
    networkTable.getEntry(RobotMap.NETWORK_TABLES_ELEVATOR_ENCODER).setDouble(elevatorSubsystem.getElevatorEncoder());
    networkTable.getEntry(RobotMap.NETWORK_TABLES_VISION_TARGET_FOUND).setBoolean(latestContours[0].w > 0);
    networkTable.getEntry(RobotMap.NETWORK_TABLES_LINEFOLLOWER_LEFT).setBoolean(drivetrainSubsystem.getLeftLineFollower());
    networkTable.getEntry(RobotMap.NETWORK_TABLES_LINEFOLLOWER_CENTER).setBoolean(drivetrainSubsystem.getCenterLineFollower());
    networkTable.getEntry(RobotMap.NETWORK_TABLES_LINEFOLLOWER_RIGHT).setBoolean(drivetrainSubsystem.getRightLineFollower());
    networkTable.getEntry(RobotMap.NETWORK_TABLES_ARM_BALL).setBoolean(rollerBarSubsystem.getBeamBreakSensor());

   
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    elevatorSubsystem.disable();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage(); // gets game data
    System.out.println("Char: " + gameData.charAt(0));
    System.out.println(m_chooser.getSelected());

    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();


    }

    String chosenAutoMode = networkTable.getEntry(RobotMap.NETWORK_TABLES_AUTO__CUREENTLY_SELECTED).getString("Default Auto");
    switch (chosenAutoMode) {

      default:
        break;
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.-
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
