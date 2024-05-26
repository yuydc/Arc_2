package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;

public class Arm{
    private final TalonFX leftMotor;
    private final TalonFX rightMotor;

    private static final double GEAR_RATIO = 10.0;
    private static final double TICKS_PER_REVOLUTION = 2048.0; 

    public Arm(int leftMotorID, int rightMotorID) {
        leftMotor = new TalonFX(leftMotorID);
        rightMotor = new TalonFX(rightMotorID);

        TalonFXConfiguration config = new TalonFXConfiguration();

        config.Slot0.kP = 1.01;  //I gave random values
        config.Slot0.kI = 1.03;
        config.Slot0.kD = 1.02;
        config.Slot0.kS = 1.05; 
        config.Slot0.kV = 1.12; 
        leftMotor.getConfigurator().apply(config);
        rightMotor.getConfigurator().apply(config);


    }

    public void setAngle(double angle) {
        double targetRotations = angle / 360.0 * GEAR_RATIO;
        double targetPositionTicks = targetRotations * TICKS_PER_REVOLUTION;

        PositionVoltage positionCommand = new PositionVoltage(targetPositionTicks);
        leftMotor.setControl(positionCommand);
        rightMotor.setControl(positionCommand);
    }

    public double getAngle() {
        double currentPositionTicks = leftMotor.getPosition().getValue();
        double currentRotations = currentPositionTicks / TICKS_PER_REVOLUTION;
        double currentAngle = currentRotations * 360.0 / GEAR_RATIO;
        return currentAngle;
    }
}
