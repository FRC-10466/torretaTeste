package frc.robot.subsystem.torret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

public class Controller {

    private final XboxController xbox1 = new XboxController(0);
    public boolean ligado = false; 

    double Velocidade_torreta() {
        return MathUtil.applyDeadband(xbox1.getLeftX(), 0.2);
    }

    double Velocidade_shooter() {
        return MathUtil.applyDeadband(xbox1.getLeftTriggerAxis(), 0.1);
    }
    
    boolean gatilhoDireitoApertado() {
        return xbox1.getRightTriggerAxis() > 0.2;

    }

    public void resetar() {
        ligado = false;
        xbox1.getAButtonPressed();
    }

    public boolean botaoA () {
        if (xbox1.getAButtonPressed()) {
            ligado = !ligado;
        }
        return ligado;
    }    
    

}
