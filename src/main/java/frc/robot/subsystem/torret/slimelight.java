package frc.robot.subsystem.torret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class slimelight {

private NetworkTable tabela = NetworkTableInstance.getDefault().getTable("limelight");

    private final double kp = 0.005;
    private final double ks = 0.02;
    private final double tolerancia = 0.55;
    private final double velocidade_max = 0.45;

    public double valor_tx() {
        return tabela.getEntry("tx").getDouble(0);
    }

    public boolean temAlvo() {
        return tabela.getEntry("tv").getDouble(0) == 1;
    }

    public double SeguirApril() {
        double tx = tabela.getEntry("tx").getDouble(0);

        if(!temAlvo() || Math.abs(tx) < tolerancia) {
            return 0.0;
        }

        final double correcao = kp * tx + Math.copySign(ks,tx);

        return MathUtil.clamp(correcao, -velocidade_max, velocidade_max);
        
    }
}
