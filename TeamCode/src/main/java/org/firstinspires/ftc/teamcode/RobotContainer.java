package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.Gamepad;


public class RobotContainer {

    private final Collector collector;
    private final Drivetrain drivetrain;
    private final Shooter shooter;

    public RobotContainer(HardwareMap hardwareMap) {
        collector = new Collector(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        shooter = new Shooter(hardwareMap);

    }

    public void collectorFunc(Gamepad gamepad2) {

        if (gamepad2.x) {
            collector.collectArtifactTeleop();
        } else {
            collector.stopCollectArtifact();
        }
    }

    public void driveFunc(Gamepad gamepad1) {

        // If you press the A button, then you reset the Yaw to be zero from the way the robot is currently pointing
        if (gamepad1.a) {
            drivetrain.resetYaw();
        }
        // If you press the left bumper, you get a drive from the point of view of the robot
        // (much like driving an RC vehicle)
        if (gamepad1.left_bumper) {
            drivetrain.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        } else {
            drivetrain.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        }

    }

    public void shooterFunc(Gamepad gamepad2) {
        if (gamepad2.a) {
            shooter.fireArtifactTeleop();
        } else {
            shooter.stopFireArtifact();
        }
    }

}
