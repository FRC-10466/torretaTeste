package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystem.torret.Torret;
import frc.robot.subsystem.tracao.Traction;

public class Robot extends TimedRobot {

  private final Torret torret = new Torret();
  private final Traction tracao = new Traction();
  private final Timer timer = new Timer();

  public Robot() {}

  @Override
  public void autonomousInit() {
    timer.restart();
  }

  @Override
  public void autonomousPeriodic() {
    if (timer.get() < 2) {
      tracao.andarDireto(0.3);
    } else {
      tracao.StopTraction();
    }
  }

  @Override
  public void teleopInit() {
    torret.resetar();
  }

  @Override
  public void teleopPeriodic() {
    torret.control();
    tracao.control();
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
