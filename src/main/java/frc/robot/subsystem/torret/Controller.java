package frc.robot.subsystem.torret;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Controller {

    CommandXboxController xbox1 = new CommandXboxController(0);

    Trigger botaoShooter = xbox1.a();

    double Velocidade_torreta() {
        return xbox1.getLeftX();
    }

    boolean Velocidade_shooter() {
        return botaoShooter.getAsBoolean();
    }

}
