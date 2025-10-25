package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.Gamepad;


public class RobotContainer {

    private final Arm arm;
    private final Drivetrain drivetrain;
    private final Shooter shooter;
    private final Intake intake;

    public RobotContainer(HardwareMap hardwareMap) {
        arm = new Arm(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        shooter = new Shooter(hardwareMap);
        intake = new Intake(hardwareMap);

    }

    public void armFunc(Gamepad gamepad2) {

        if (gamepad2.x) {
            Actions.runBlocking(arm.armPos1000());
        } else if (gamepad2.b) {
            Actions.runBlocking(arm.armPos2000());
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
            shooter.shooterZeroPower();
        }
    }

    public void intakeFunc(Gamepad gamepad2) {
        if (gamepad2.left_stick_y > 0) {
            intake.collectArtifactTeleop();
        } else if (gamepad2.left_stick_y < 0){
            intake.releaseArtifactTeleop();
        } else {
            intake.intakeZeroPower();
        }
    }

    public void Telemetry(Telemetry telemetry) {

        telemetry.addData("Arm Position", arm.getArmCurrentPosition());
        telemetry.addData("Intake Power", intake.getIntakePower());
        telemetry.addData("Shooter Power", shooter.getLeftShooterPower());

        telemetry.update();


    }

}
