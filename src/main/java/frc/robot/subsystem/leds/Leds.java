package frc.robot.subsystem.leds;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;

public class Leds extends SubsystemBase {

    private final Spark blinkin = new Spark(1);
    public boolean piscando = false;
    public final double VERMELHO = 0.61;
    public final double VERDE = 0.77;
    public final double AMARELO = 0.67;
    public final double PRETO = 0.99;


    public void PiscarCor(double cor, double seconds){
        boolean CorLigado = (int) (Timer.getFPGATimestamp()/seconds)%2 ==0;
        blinkin.set(CorLigado ? cor :PRETO);

    }
    
    public void setPiscando(boolean piscar){
        piscando = piscar;
    }

    public void setarCor(double cor) {
        if(piscando){
            PiscarCor(cor, 0.2);
        }else{
            blinkin.set(cor);
        }
    }

}
