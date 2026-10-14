package frc.robot.subsystem.torret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class slimelight {

private NetworkTable tabela = NetworkTableInstance.getDefault().getTable("limelight");

    private final double kp = 0.01;
    private final double tolerancia = 0.15;
    private final double velocidade_max = 0.45;

    public double SeguirApril() {
        double tx = tabela.getEntry("tx").getDouble(0);
        double tv = tabela.getEntry("tv").getDouble(0);

        final double erro = -tx;
        final double correcao = kp * erro;

        if (tv == 0.0) {
            return 0.0;
        }

        if(Math.abs(erro) < tolerancia) {
            return 0.0;
        }

        return MathUtil.clamp(correcao, -velocidade_max, velocidade_max);
        
    }
}
