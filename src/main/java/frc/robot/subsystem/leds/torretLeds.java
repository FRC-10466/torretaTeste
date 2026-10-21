package frc.robot.subsystem.leds;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class torretLeds extends SubsystemBase {

    private final Spark blinkin = new Spark(1);

    public final double VERMELHO = 0.61;
    public final double VERDE = 0.77;

    public void setarCor(double cor) {
        blinkin.set(cor);
    }

}
