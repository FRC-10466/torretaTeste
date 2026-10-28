package frc.robot.subsystem.torret;

public class torretContantes {

    public final double ALTURA_APRITAG = 0.94;//centra do qrcode
    public final double ALTURA_CAMERA = 0.65;//altura da limelight
    public final double ANGULO_CAMERA = 6;//inclinação da limeliht
    public final double AJUSTE_DISTANCIA = 0;// diferença da limeliht com o shooter e da apriltag com o caixote
    public final double ALTURA_ALVO = 1.12; //altura da borda da caixa
    public final double RAIO_BOLA = 0.075;
    public final double ALTURA_DA_BOLA_AO_SAIR = 0.55;
    public final double ANGULO_QUE_A_BOLA_SAI = Math.toRadians(155);
    public final double DIAMETRO_DA_RODA = 0.10;
    public final double EFICIENCIA_SHOOTER = 0.55;
    
    public final double GRAVIDADE = 9.81;
    
    public final double RPM_MAXIMO = 5000;
    public final double TOLERANCIA_RPM = 100;

    public final double SHOOTER_KP = 0.0005;
    public final double SHOOTER_KV = 12.0/5676.0;

    public final double REDUCAO_TORRETA = 200.0/18.0;
     
}
