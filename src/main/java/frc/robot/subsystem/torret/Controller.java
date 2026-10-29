package frc.robot.subsystem.torret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

public class Controller {

    private final XboxController xbox1 = new XboxController(0);
    public final XboxController xbox2 = new XboxController(1);
    public boolean ligado = false; 
    private static  boolean turboLigado = false;

    double Velocidade_torreta() {
        return MathUtil.applyDeadband(xbox1.getRightX(), 0.2);
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
    
//---------------CONTROLE 2------------------------------------

    public double andarReto() {
        return MathUtil.applyDeadband(xbox2.getLeftY(), 0.1);
    }

    public double virar() {
        return MathUtil.applyDeadband(xbox2.getRightX(), 0.1);
    }

    public boolean turboLigado() {
        if(xbox2.getAButtonPressed()){
            turboLigado = !turboLigado;
        }
        return turboLigado;
    }
    public boolean turboEstaLigado(){
        return turboLigado;
    }
    
    public void turboDesligado(){
        turboLigado = false;
        xbox2.getAButtonPressed();
    }
}
