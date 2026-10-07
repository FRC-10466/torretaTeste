package frc.robot.subsystem.torret;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;


public class Torret {

    RelativeEncoder shooter_encoder;
    RelativeEncoder encoder_torret;

    Controller controle = new Controller();

    private final SparkMax motor_shooter = new SparkMax(1, MotorType.kBrushless);
    private final SparkMax motor_torret = new SparkMax(2, MotorType.kBrushless);

    private final SparkMaxConfig shooter_config = new SparkMaxConfig();
    private final SparkMaxConfig torret_config = new SparkMaxConfig();

    public Torret() {
        shooter_config.smartCurrentLimit(40).idleMode(IdleMode.kCoast).inverted(false).voltageCompensation(12);
        torret_config.smartCurrentLimit(40).idleMode(IdleMode.kBrake).inverted(false);

        shooter_encoder = motor_shooter.getEncoder();
        encoder_torret = motor_torret.getEncoder();

        torret_config.softLimit.reverseSoftLimit(0.1).reverseSoftLimitEnabled(true).forwardSoftLimit(6).forwardSoftLimitEnabled(true);

        motor_torret.configure(torret_config, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        motor_shooter.configure(shooter_config,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void control() {
        double speed_torreta = (controle.Velocidade_torreta() * 0.05);
        motor_torret.set(speed_torreta);

        double speed_shooter = (controle.Velocidade_shooter());
        motor_shooter.set(speed_shooter);
    }
}