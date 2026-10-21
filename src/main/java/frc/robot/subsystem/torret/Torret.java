package frc.robot.subsystem.torret;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import com.revrobotics.PersistMode;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystem.limelight.slimelight;
import frc.robot.subsystem.leds.torretLeds;


public class Torret {

    RelativeEncoder shooter_encoder;
    RelativeEncoder encoder_torret;

    controller controle = new controller();
    slimelight limelight = new slimelight();
    torretLeds led = new torretLeds();
    torretContantes valor = new torretContantes();

    private final SparkMax motor_shooter = new SparkMax(6, MotorType.kBrushless);
    private final SparkMax motor_torret = new SparkMax(7, MotorType.kBrushless);

    private final SparkMaxConfig shooter_config = new SparkMaxConfig();
    private final SparkMaxConfig torret_config = new SparkMaxConfig();

    public Torret() {
        shooter_config.smartCurrentLimit(40).idleMode(IdleMode.kCoast).inverted(false).voltageCompensation(12);
        torret_config.smartCurrentLimit(40).idleMode(IdleMode.kBrake).inverted(false).voltageCompensation(12);

        shooter_encoder = motor_shooter.getEncoder();
        encoder_torret = motor_torret.getEncoder();

        torret_config.softLimit.reverseSoftLimit(0.1).reverseSoftLimitEnabled(true).forwardSoftLimit(6).forwardSoftLimitEnabled(true);

        motor_torret.configure(torret_config, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        motor_shooter.configure(shooter_config,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void resetar() {
        controle.resetar();
    }
    boolean voltou = false;
    void voltarZero() {
        if (voltou == false && encoder_torret.getPosition() <= 0.2) {
            motor_torret.set(-0.05);
        } voltou = true;
    }

    void proucurandoApriltag() {
        if(voltou && encoder_torret.getPosition() >= 5.8) {
            motor_torret.set(0.02);
        }
    }

    public void control() {
        SmartDashboard.putBoolean("tem alvo", limelight.temAlvo());
        SmartDashboard.putBoolean("auto ligado", controle.ligado);
        SmartDashboard.putNumber("ty", limelight.valor_ty());
        SmartDashboard.putNumber("tx", limelight.valor_tx());
        
        double speed_shooter = (controle.Velocidade_shooter());
        motor_shooter.set(speed_shooter);

        boolean temAlvo = limelight.antiPiscada.calculate(limelight.temAlvo());

        led.setarCor(limelight.temAlvo() ? led.VERDE : led.VERMELHO);
        
        if (controle.botaoA()) {
            if(temAlvo){
                led.setarCor(led.VERDE);
                motor_torret.set(limelight.corrigir());  
            } else {
                voltarZero();
                proucurandoApriltag();
            }
        } 
    
         if (controle.botaoA() && temAlvo) {
            motor_torret.set(limelight.corrigir());
        }else {
            double speed_torreta = (controle.Velocidade_torreta() * 0.08);
            motor_torret.set(speed_torreta);
        }
    }
}