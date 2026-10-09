package frc.robot.subsystem.torret;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;

public class slimelight {

private final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

private final NetworkTableEntry tx = limelight.getEntry("tx");
//private final NetworkTableEntry ty = limelight.getEntry("ty");
private final NetworkTableEntry ligado = limelight.getEntry("tv");


    slimelight() {
    }

    void SlimeNaPratica() {
        double erroHorizontal = tx.getDouble(0.0);
        boolean Achou = ligado.getDouble(0.0) == 1.0;
        
        SmartDashboard.putNumber("TX", erroHorizontal);
        SmartDashboard.putBoolean("achou", Achou);
        
    }

    public void SeguirApril() {
        double kp = 0.5;
        double erro = (0 - tx.getDouble(0));
        double correcao = kp * erro;
    }
}
