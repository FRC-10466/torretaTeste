package frc.robot.subsystem.testePid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.MedianFilter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.AnalogInput;




public class testePID {
    private final Ultrasonic sensor = new Ultrasonic(0, 1);
    private final SparkMax motor = new SparkMax(3, MotorType.kBrushless);
    private final RelativeEncoder encoder = motor.getEncoder();
    private final MedianFilter mediana = new MedianFilter(5);
    private final SparkMaxConfig config = new SparkMaxConfig();

    //Malha interna
    private final PIDController PidAngulo = new PIDController(0.5, 0, 0);
    //Malhar externa
    private final PIDController PidPosicao = new PIDController(0.007, 0, 0);

    private final double Reducao = 5.0; 
    private final double PotenciaMax = 0.10;
    private final double LimiteRotacoes = 0.095;
    private final double LimiteAngulo = 10.0 / 360.0 * Reducao;

    private final double DistanciaMin = 10.0;
    private final double DistanciaMax = 45.0;

    private double distanciaCm = 0;
    private boolean sensorValido = false;
    private double anguloAlvo = 0;
    private double kG = 0.0;
    

    public final double Laranja = 2.25;
    public final double Amarelo = 6.75;
    public final double Verde = 11.25;
    public final double Vermelho = 15.75;
    public final double AzulEscuro = 20.25;
    public final double AzulClaro = 24.75;

    public testePID() {
    

    config.smartCurrentLimit(15).idleMode(IdleMode.kBrake).inverted(false);
    config.softLimit.forwardSoftLimit(LimiteRotacoes).forwardSoftLimitEnabled(true);
    config.softLimit.reverseSoftLimit(-LimiteRotacoes).reverseSoftLimitEnabled(true);
    motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);    

    encoder.setPosition(0);

    SmartDashboard.putData("PID/angulo", PidAngulo);   
    SmartDashboard.putData("PID/posicao", PidPosicao);
    SmartDashboard.putNumber("PID/kG", kG);
    }

    
    public void atualizarSensor() {
        double leitura = sensor.getRangeMM() / 10.0;
        if (sensor.isRangeValid() && leitura != ultimaLeitura) {
            distanciaCm = mediana.calculate(leitura); // só entra no filtro quando o sensor mediu de novo
            ultimaLeitura = leitura;
            leituraNova = true;
            System.out.println(leitura);
        }
        SmartDashboard.putBoolean("PID/sensor_valido", sensor.isRangeValid());
        SmartDashboard.putNumber("PID/distancia_cm", distanciaCm);
        SmartDashboard.putNumber("PID/encoder_rot", encoder.getPosition());
    }

    public void segurarAngulo(double alvoRot){
        kG = SmartDashboard.getNumber("PID/kG", kG);
        double potencia = MathUtil.clamp(PidAngulo.calculate(encoder.getPosition(), alvoRot) + kG, -PotenciaMax, PotenciaMax);
        motor.set(potencia);

        SmartDashboard.putNumber("PID/angulo_alvo", alvoRot);
        SmartDashboard.putNumber("PID/potencia", potencia);
    }


    
    
    public void irPara(double alvo) {
        if (!sensorValido) {
            parar();
            return;
        }
        
        anguloAlvo = MathUtil.clamp(PidPosicao.calculate(distanciaCm, alvo), -LimiteAngulo, LimiteAngulo);

        segurarAngulo(anguloAlvo);
        
       SmartDashboard.putNumber("PID/alvo_cm", alvo);
    }


    
    public void parar(){
        PidAngulo.reset();
        PidPosicao.reset();
        anguloAlvo = 0;
        segurarAngulo(0);
    }

}

    




