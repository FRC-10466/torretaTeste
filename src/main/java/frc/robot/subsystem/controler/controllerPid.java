package frc.robot.subsystem.controler;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.testePid.testePID;

public class controllerPid {
    CommandXboxController xbox2 = new CommandXboxController(1);

    private final testePID testePID = new testePID();

    public void atualizar() {
        testePID.atualizarSensor();
    }

    public void controlPid() {
    if (xbox2.getHID().getPOV() == 0) {
        testePID.irPara(testePID.Laranja);
    } else if (xbox2.getHID().getPOV() == 90) {
        testePID.irPara(testePID.Amarelo);
    } else if (xbox2.getHID().getPOV() == 180) {
        testePID.irPara(testePID.Verde);
    } else if (xbox2.getHID().getPOV() == 270) {
        testePID.irPara(testePID.Vermelho);
    } else if (xbox2.getHID().getLeftBumperButton()) {
        testePID.irPara(testePID.AzulEscuro);
    } else if (xbox2.getHID().getRightBumperButton()) {
        testePID.irPara(testePID.AzulClaro);
    } else {
        testePID.parar(); 
    }
}

}