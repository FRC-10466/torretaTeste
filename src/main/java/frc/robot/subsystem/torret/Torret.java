package frc.robot.subsystem.torret;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import com.revrobotics.PersistMode;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystem.limelight.slimelight;
import frc.robot.subsystem.leds.torretLeds;


public class Torret {

    RelativeEncoder shooter_encoder;
    RelativeEncoder encoder_torret;

    Controller controle = new Controller();
    slimelight limelight = new slimelight();
    torretLeds led = new torretLeds();
    torretContantes constantes = new torretContantes();
    torretCalculos albert = new torretCalculos();

    private final SparkMax motor_shooter = new SparkMax(6, MotorType.kBrushless);
    private final SparkMax motor_torret = new SparkMax(7, MotorType.kBrushless);

    private final SparkClosedLoopController shooterPID = motor_shooter.getClosedLoopController();

    private final Debouncer estavel = new Debouncer(0.1);

    private final SparkMaxConfig shooter_config = new SparkMaxConfig();
    private final SparkMaxConfig torret_config = new SparkMaxConfig();

    public Torret() {
        shooter_config.smartCurrentLimit(40).idleMode(IdleMode.kCoast).inverted(false).voltageCompensation(12);
        torret_config.smartCurrentLimit(40).idleMode(IdleMode.kBrake).inverted(false).voltageCompensation(12);

        shooter_encoder = motor_shooter.getEncoder();
        encoder_torret = motor_torret.getEncoder();

        shooter_config.encoder.velocityConversionFactor(1.0);
        torret_config.encoder.positionConversionFactor(360.0 / constantes.REDUCAO_TORRETA);

        shooter_config.closedLoop.pid(constantes.SHOOTER_KP, 0, 0);
        shooter_config.closedLoop.feedForward.kV(constantes.SHOOTER_KV);

        torret_config.softLimit.reverseSoftLimit(0.1).reverseSoftLimitEnabled(false).forwardSoftLimit(6).forwardSoftLimitEnabled(false);

        motor_torret.configure(torret_config, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        motor_shooter.configure(shooter_config,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    boolean voltou = false;
    void voltarZero() {
        if (voltou == false && encoder_torret.getPosition() >= 0.2) {
            motor_torret.set(-0.1);
        } else {
            voltou = true;
            motor_torret.set(0);
        }
    }

    void proucurandoApriltag() {
        if(voltou && encoder_torret.getPosition() <= 5.8) {
            motor_torret.set(0.08);
        } else {
            motor_torret.set(0);
        }
    }

    public void resetar() {
        controle.resetar();
        estavel.calculate(false);
    }

    public void control() {
        SmartDashboard.putBoolean("tem alvo", limelight.temAlvo());
        SmartDashboard.putBoolean("auto ligado", controle.ligado);
        SmartDashboard.putNumber("ty", limelight.valor_ty());
        SmartDashboard.putNumber("tx", limelight.valor_tx());
        SmartDashboard.putNumber("limite torreta", encoder_torret.getPosition());

        boolean temAlvo = limelight.antiPiscada.calculate(limelight.temAlvo());
        double tx = limelight.valor_tx();
        double rpmAlvo = albert.RPMmotor();
        boolean rpmValido = limelight.temAlvo() && Double.isFinite(rpmAlvo) && rpmAlvo > 0 && rpmAlvo < constantes.RPM_MAXIMO;
        boolean mirado = limelight.temAlvo() && Math.abs(tx) < 3;
        boolean velocidadeCerta = rpmValido && Math.abs(rpmAlvo - shooter_encoder.getVelocity()) < constantes.TOLERANCIA_RPM;
        boolean pronto = estavel.calculate(mirado && velocidadeCerta);

        if (pronto) {
            led.setarCor(led.VERDE);
        } else if(temAlvo) {
            led.setarCor(led.AMARELO);
        } else {
            led.setarCor(led.VERMELHO);
        }
        
        if (controle.gatilhoDireitoApertado()) {
            if(temAlvo){
                voltou = false;
                motor_torret.set(limelight.corrigir()); 
                if (rpmValido) {
                    shooterPID.setSetpoint(rpmAlvo, ControlType.kVelocity);
                } else {
                    motor_shooter.set(0);
                }
            } else if (!voltou) {
                motor_shooter.set(0);
                voltarZero();
            } else {
                motor_shooter.set(0);
                proucurandoApriltag();
            }
        }else {
            voltou = false;
            double speed_torreta = (controle.Velocidade_torreta() * 0.08);
            motor_torret.set(speed_torreta);
            double speed_shooter = (controle.Velocidade_shooter());
            motor_shooter.set(speed_shooter);
        }

    }
}