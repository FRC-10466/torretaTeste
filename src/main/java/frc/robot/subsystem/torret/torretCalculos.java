package frc.robot.subsystem.torret;

import frc.robot.subsystem.limelight.slimelight;

public class torretCalculos {

    slimelight limelight = new slimelight();
    torretContantes contantes = new torretContantes();

    double distancia() {
        double a = (contantes.ALTURA_APRITAG - contantes.ALTURA_CAMERA);
        double b = Math.tan(Math.toRadians(contantes.ANGULO_CAMERA + limelight.valor_ty()));
        return  a/b + contantes.AJUSTE_DISTANCIA;
    }

    double deltaH() {
        return contantes.ALTURA_ALVO + contantes.RAIO_BOLA - contantes.ALTURA_DA_BOLA_AO_SAIR;
    }

    double velocidadeBola() {
        double g = 9.81;
        double d = distancia();
        double base = d * Math.tan(contantes.ANGULO_QUE_A_BOLA_SAI) - deltaH();
        if (base <= 0.01 || d <= 0) {
            return Double.NaN;
        } double cossenoAngulo = Math.cos(contantes.ANGULO_QUE_A_BOLA_SAI);
        return Math.sqrt(g * d * d / ( 2 * cossenoAngulo * cossenoAngulo * base));
    }

    double RPMmotor() {
        return (60 * velocidadeBola()) / (0.55 * Math.PI * contantes.DIAMETRO_DA_RODA);
    }

}


