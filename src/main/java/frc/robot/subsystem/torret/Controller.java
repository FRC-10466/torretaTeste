package frc.robot.subsystem.torret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Controller {

    CommandXboxController xbox1 = new CommandXboxController(0);

    double botaoShooter = xbox1.getRightTriggerAxis();

    double Velocidade_torreta() {
        return MathUtil.applyDeadband(xbox1.getLeftX(), 0.2);
    }

    double Velocidade_shooter() {
        return MathUtil.applyDeadband(xbox1.getRightTriggerAxis(), 0.1);
    }

}
