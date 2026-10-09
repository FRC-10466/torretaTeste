package frc.robot.subsystem.torret;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Controller {

    CommandXboxController xbox1 = new CommandXboxController(0); 

    double botaoShooter = xbox1.getRightTriggerAxis();
    boolean botaocorrigit = xbox1.a().getAsBoolean();

    double Velocidade_torreta() {
        return MathUtil.applyDeadband(xbox1.getLeftX(), 0.2);
    }

    double Velocidade_shooter() {
        return MathUtil.applyDeadband(xbox1.getRightTriggerAxis(), 0.1);
    }

    boolean ligado = botaocorrigit;
    boolean ultimo_botao = false;
    
    public void botaooo(SparkMax motor) {
        if(ligado && ultimo_botao) {
            ligado = !ligado;
            if(ligado = true) {
                motor.set(1);
            } else {
                motor.set(0);
            }

        } 
        ligado = ultimo_botao;
    }
    

}
