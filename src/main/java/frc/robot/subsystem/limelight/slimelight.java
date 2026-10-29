package frc.robot.subsystem.limelight;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystem.torret.Contantss;

public class slimelight {

    public final Debouncer antiPiscada = new Debouncer(0.5,DebounceType.kFalling);

    private NetworkTable tabela = NetworkTableInstance.getDefault().getTable("limelight-back");
     Contantss contantes = new Contantss();

    
    public double valor_ty() {
        return tabela.getEntry("ty").getDouble(0);
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

        if(!temAlvo() || Math.abs(tx) < contantes.tolerancia) {
            return 0.0;
        }

        final double correcao = contantes.kp * tx + Math.copySign(contantes.ks,tx);

        return MathUtil.clamp(correcao, -contantes.velocidade_max, contantes.velocidade_max);
        
    }
}
