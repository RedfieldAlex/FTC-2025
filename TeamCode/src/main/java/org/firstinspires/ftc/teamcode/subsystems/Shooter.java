package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Shooter {

    DcMotor leftShooter;
    DcMotor rightShooter;

    public Shooter(HardwareMap hardwareMap) {
        leftShooter = hardwareMap.get(DcMotor.class, "Left_Shooter");
        rightShooter = hardwareMap.get(DcMotor.class, "Right_Shooter");
        leftShooter.setDirection(DcMotor.Direction.REVERSE);
        rightShooter.setDirection(DcMotor.Direction.FORWARD);
        leftShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public Action fireArtifact() {
        return new FireArtifact();
    }

    public class FireArtifact implements Action {

        private boolean initialized = false;
        private ElapsedTime runtime = new ElapsedTime();
        private final double SHOOT_TIME = 3.0;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                leftShooter.setPower(0.8);
                rightShooter.setPower(0.8);
                runtime.reset();
                initialized = true;
            }

            double leftShooterPower = leftShooter.getPower();
            double rightShooterPower = rightShooter.getPower();

            packet.put("Left Shooter Power", leftShooterPower);
            packet.put("Right Shooter Power", rightShooterPower);

            if () {
                return true;
            } else {
                leftShooter.setPower(0);
                rightShooter.setPower(0);
                return false;
            }

        }
    }

}