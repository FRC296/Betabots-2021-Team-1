// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.playingwithfusion.CANVenom;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;


enum AutonSteps {
  CatchTube,
  GrabTube,
  MoveToScore
}

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  XboxController controller = new XboxController(0);

  CANVenom motorLeft = new CANVenom(0);
  CANVenom motorRight = new CANVenom(1);

  TalonSRX arm = new WPI_TalonSRX(2);

  Solenoid grabber = new Solenoid(0);

  com.ctre.phoenix.motorcontrol.ControlMode talonSpeed = com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
  com.ctre.phoenix.motorcontrol.ControlMode talonPosition = com.ctre.phoenix.motorcontrol.ControlMode.Position;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    motorRight.setInverted(true);

    // arm.configSelectedFeedbackCoefficient(coefficient, pidIdx, timeoutMs)
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Move arm forward
    // Close grabber
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double left = controller.getY(Hand.kLeft);
    double right = controller.getY(Hand.kRight);
    double triggerLeft = controller.getTriggerAxis(Hand.kLeft);
    double triggerRight = controller.getTriggerAxis(Hand.kRight);

    motorLeft.set(left);
    motorRight.set(right);

    // arm.set(talonSpeed, triggerRight - triggerLeft);


    if (controller.getPOV() == 270) {
      // "back position"
      arm.set(talonPosition, -100);
    } else if (controller.getPOV() == 0) {
      // "middle position"
      arm.set(talonPosition, 0);
    } else if (controller.getPOV() == 90) {
      // "ready position"
      arm.set(talonPosition, 80);
    } else {
      arm.set(talonSpeed, triggerRight - triggerLeft);
    }







    if (controller.getBumperPressed(Hand.kRight)) {
      grabber.set(true);
    } else if (controller.getBumperPressed(Hand.kLeft)) {
      grabber.set(false);
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
