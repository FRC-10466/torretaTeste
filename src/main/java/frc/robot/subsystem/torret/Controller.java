package frc.robot.subsystem.torret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Controller {

    CommandXboxController xbox1 = new CommandXboxController(0); 

    double Velocidade_torreta() {
        return MathUtil.applyDeadband(xbox1.getLeftX(), 0.2);
    }

    double Velocidade_shooter() {
        return MathUtil.applyDeadband(xbox1.getRightTriggerAxis(), 0.1);
    }

    boolean ligado = false;
    boolean ultimo_botao = false;

    boolean botaoA () {
        boolean Atual_botao = xbox1.a().getAsBoolean();

        if (Atual_botao && !ultimo_botao) {
            ligado = !ligado;
        }
        ultimo_botao = Atual_botao;
        return ligado;
    }    
    

}
