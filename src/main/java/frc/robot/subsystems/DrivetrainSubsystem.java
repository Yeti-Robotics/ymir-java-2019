package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.UserDriveCommand;
import frc.robot.controls.CustomTalon;

public class DrivetrainSubsystem extends PIDSubsystem {

    private VictorSPX left1, left2, right1, right2;
    private CustomTalon leftTal, rightTal;
    private DifferentialDrive differentialDrive;
    private DriveMode driveMode;
    public AnalogInput lineSensorLeft, lineSensorCenter, lineSensorRight;
    public ADIS16448_IMU gyro;

    public enum DriveMode {
        TANK, ARCADE, CHEEZY;
    }

    public DrivetrainSubsystem() {
        
        super(0.25, 0, 0);
        left1 = new VictorSPX(RobotMap.LEFT_1_VICTOR);
        left2 = new VictorSPX(RobotMap.LEFT_2_VICTOR);
        right1 = new VictorSPX(RobotMap.RIGHT_1_VICTOR);
        right2 = new VictorSPX(RobotMap.RIGHT_2_VICTOR);
        leftTal = new CustomTalon(RobotMap.LEFT_DRIVE_TALON);
        rightTal = new CustomTalon(RobotMap.RIGHT_DRIVE_TALON);
        leftTal.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 30);
        leftTal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        rightTal.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 30);
        rightTal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);   
        gyro = new ADIS16448_IMU();
        gyro.calibrate();

        left1.follow(leftTal);
        left2.follow(leftTal);
        right1.follow(rightTal);
        right2.follow(rightTal);

        differentialDrive = new DifferentialDrive(leftTal, rightTal);
       

        leftTal.setInverted(true);
        left1.setInverted(true);
        left2.setInverted(true);

        lineSensorLeft = new AnalogInput(RobotMap.LEFT_LINE_FOLLOWER_PORT);
        lineSensorCenter = new AnalogInput(RobotMap.CENTER_LINE_FOLLOWER_PORT);
        lineSensorRight = new AnalogInput(RobotMap.RIGHT_LINE_FOLLOWER_PORT);

        driveMode = DriveMode.CHEEZY;
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
        return rightTal.getSelectedSensorPosition()*RobotMap.DRIVE_DISTANCE_PER_PULSE;
    }

    public double getLeftEncoderDistance() {
        return leftTal.getSelectedSensorPosition()*RobotMap.DRIVE_DISTANCE_PER_PULSE;
    }

    public double getAvgEncoderDistance() {
        return (Math.abs(getRightEncoderDistance()) + Math.abs(getLeftEncoderDistance())) / 2;
    }

    public double getLeftEncoderRate() {
        return leftTal.getSelectedSensorVelocity()*RobotMap.DRIVE_DISTANCE_PER_PULSE;
    }

    public double getRightEncoderRate() {
        return rightTal.getSelectedSensorVelocity()*RobotMap.DRIVE_DISTANCE_PER_PULSE;
    }

    @Override
    protected double returnPIDInput() {
        return getAvgEncoderDistance();
    }

    @Override
    protected void usePIDOutput(double output) {
        moveDriveTrainLeft(output);
        moveDriveTrainRight(output);
    }

};
