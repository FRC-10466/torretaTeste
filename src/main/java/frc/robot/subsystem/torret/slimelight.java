package frc.robot.subsystem.torret;

import edu.wpi.first.networktables.NetworkTableInstance;

public class slimelight {

private double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
private double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

    private final double kp = 0.01;

    public double SeguirApril() {
        final double erro = 0 - tx;
        final double correcao = kp * erro;
        final double velocidade = Math.tanh(correcao);
        
        if(tv == 0.0)  {
            return 0.0;
        }else {
        return velocidade;
        }
    }
}
