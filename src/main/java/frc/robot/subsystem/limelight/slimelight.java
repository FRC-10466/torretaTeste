package frc.robot.subsystem.limelight;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystem.torret.torretContantes;

public class slimelight {

    public final Debouncer antiPiscada = new Debouncer(0.3,DebounceType.kFalling);

    torretContantes contants = new torretContantes();

    private NetworkTable tabela = NetworkTableInstance.getDefault().getTable("limelight-back");

    private final double kp = 0.001;
    private final double ks = 0.04;
    private final double tolerancia = 3;
    private final double velocidade_max = 0.45;


    public double valor_ty() {
        return tabela.getEntry("ty").getDouble(0);
    }

    public double valor_tx() {
        return tabela.getEntry("tx").getDouble(0);
    }

    public boolean temAlvo() {
        return tabela.getEntry("tv").getDouble(0) == 1;
    }

    public double distancia() {
        double a = (contants.ALTURA_APRITAG - contants.ALTURA_CAMERA);
        double b = Math.tan(Math.toRadians(contants.ANGULO_CAMERA + valor_ty()));
        return  a/b + contants.AJUSTE_DISTANCIA;
    }

    public double corrigir() {
        double velocidade_correcao = SeguirApril();
        return velocidade_correcao;
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
