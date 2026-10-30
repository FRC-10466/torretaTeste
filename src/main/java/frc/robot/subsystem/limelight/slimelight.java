package frc.robot.subsystem.limelight;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class slimelight {

    public final Debouncer antiPiscada = new Debouncer(0.5,DebounceType.kFalling);

    private final MedianFilter filtroTy = new MedianFilter(7);

    private NetworkTable tabela = NetworkTableInstance.getDefault().getTable("limelight-back");

    private final double kp = 0.001;
    private final double ks = 0.03;
    private final double tolerancia = 3;
    private final double velocidade_max = 0.45;


    public double valor_ty() {
        return filtroTy.calculate(tabela.getEntry("ty").getDouble(0));
    }

    public double valor_tx() {
        return tabela.getEntry("tx").getDouble(0);
    }

    public boolean temAlvo() {
        return tabela.getEntry("tv").getDouble(0) == 1;
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
