package frc.robot.subsystem.controler;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

public class Controller {

    private final XboxController xbox1 = new XboxController(0);
    private final XboxController xbox2 = new XboxController(1);
    boolean ligado1 = false; 
    boolean ligado2 = false;
    boolean ultimoEstado2 = false;

    public double Velocidade_torreta() {
        return MathUtil.applyDeadband(xbox1.getLeftX(), 0.2);
    }

    public double Velocidade_shooter() {
        return MathUtil.applyDeadband(xbox1.getLeftTriggerAxis(), 0.1);
    }
    
    public boolean gatilhoDireitoApertado() {
        boolean a = xbox1.getRightTriggerAxis() > 0.2;
        if (a && !ultimoEstado2) {
            ligado2 = !ligado2;
        }
        ultimoEstado2 = a;
        return ligado2;
    }

    public void resetar() {
        ligado1 = false;
        ligado2 = false;
        xbox1.getAButtonPressed();
    }

    public boolean botaoA () {
        if (xbox1.getAButtonPressed()) {
            ligado1 = !ligado1;
        }
        return ligado1;
    } 
    
    public double andarReto() {
        return MathUtil.applyDeadband(-xbox2.getLeftY(), 0.1);
    }

    public double virar() {
        return MathUtil.applyDeadband(xbox2.getRightX(), 0.1);
    }

    public boolean turboApertado() {
        return xbox2.getAButton();
    }
    

}
