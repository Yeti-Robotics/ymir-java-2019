package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.UserDriveCommand;
import frc.robot.controls.CustomTalon;

public class DrivetrainSubsystem extends Subsystem {

    private VictorSPX left1, left2, right1, right2;
    private CustomTalon leftTal, rightTal;
    private DifferentialDrive differentialDrive;
    private DriveMode driveMode;
    public AnalogInput lineSensorLeft, lineSensorCenter, lineSensorRight;
    public ADXRS450_Gyro gyro;

    public enum DriveMode {
        TANK, ARCADE, CHEEZY;
    }

    public DrivetrainSubsystem() {

        left1 = new VictorSPX(RobotMap.LEFT_1_VICTOR);
        left2 = new VictorSPX(RobotMap.LEFT_2_VICTOR);
        right1 = new VictorSPX(RobotMap.RIGHT_1_VICTOR);
        right2 = new VictorSPX(RobotMap.RIGHT_2_VICTOR);
        leftTal = new CustomTalon(RobotMap.LEFT_Drive_TALON);
        rightTal = new CustomTalon(RobotMap.RIGHT_Drive_TALON);
        leftTal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rightTal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        gyro = new ADXRS450_Gyro();
        gyro.calibrate();
        SmartDashboard.putData(gyro);

        left1.follow(leftTal);
        left2.follow(leftTal);
        right1.follow(rightTal);
        right2.follow(rightTal);

        differentialDrive = new DifferentialDrive(leftTal, rightTal);
       

        // Creates encoder objects connected to their respective DIO ports
        rightTal.setInverted(true);
        left1.setInverted(true);
        left2.setInverted(true);

        lineSensorLeft = new AnalogInput(RobotMap.LEFT_LINE_FOLLOW);
        lineSensorCenter = new AnalogInput(RobotMap.CENTER_LINE_FOLLOW);
        lineSensorRight = new AnalogInput(RobotMap.RIGHT_LINE_FOLLOW);

        SmartDashboard.putNumber("Left drive distance", getLeftEncoderValue());
        SmartDashboard.putNumber("Right drive distance", getRightEncoderValue());

        driveMode = DriveMode.TANK;

    }
    

    public void resetGyro() {
        gyro.reset();
    }

    public DriveMode getDriveMode() {
        return driveMode;
    }

    public void setDriveMode(DriveMode driveMode) {
        this.driveMode = driveMode;
    }

    // Resets the encoder values
    public void resetEncoders() {
        leftTal.setSelectedSensorPosition(0);
        rightTal.setSelectedSensorPosition(0);
    }

    // Prints drive encoder values to the console
    public void printEncoders() {
        System.out.println(getRightEncoderDistance() + ", " + getLeftEncoderDistance());
    }

    public double getRightEncoderValue() {
        return rightTal.getSelectedSensorPosition();
    }

    public double getLeftEncoderValue() {
        return leftTal.getSelectedSensorPosition();
    }

    public double getAvgEncoderDistance() {
        return (Math.abs(getRightEncoderValue()) + Math.abs(getLeftEncoderValue())) / 2;
    }

    // Controls the right side of the drive train
    public void moveDriveTrainRight(double power) {
        rightTal.set(ControlMode.PercentOutput, power);

    }

    // Controls the left side of the drive train
    public void moveDriveTrainLeft(double power) {
        leftTal.set(ControlMode.PercentOutput, power);
    }

    public void tankDrive(double left, double right) {
        differentialDrive.tankDrive(-left, right);
    }

    public void arcadeDrive(double forward, double rotation) {
        differentialDrive.arcadeDrive(forward, rotation);
    }

    public void cheezyDrive(double forward, double rotation) {
        differentialDrive.curvatureDrive(forward, rotation, true);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new UserDriveCommand());
    }

    public double getRightEncoderDistance() {
        return rightTal.getSelectedSensorPosition()*RobotMap.WHEELS_DISTANCE_PER_PULSE;
    }

    public double getLeftEncoderDistance() {
        return leftTal.getSelectedSensorPosition()*RobotMap.WHEELS_DISTANCE_PER_PULSE;
    }

    public double getLeftEncoderRate() {
        return leftTal.getSelectedSensorVelocity()*RobotMap.WHEELS_DISTANCE_PER_PULSE;
    }

    public double getRightEncoderRate() {
        return rightTal.getSelectedSensorVelocity()*RobotMap.WHEELS_DISTANCE_PER_PULSE;
    }

};