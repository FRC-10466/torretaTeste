package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.subsystem.torret.Torret;

public class Robot extends TimedRobot {

  Torret torret = new Torret();
  /** Called once at the beginning of the robot program. */
  public Robot() {
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    torret.control();
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
