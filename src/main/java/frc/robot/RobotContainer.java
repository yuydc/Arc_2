package frc.robot;

import frc.robot.subsystems.Arm;

public class RobotContainer {
    private final Arm arm;

    public RobotContainer() {
        arm = new Arm(1, 2); // ID's of each falcon 500 motors
    }
    public Arm getArm() {
        return arm;
    }
}
