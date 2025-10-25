package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
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

    public double getLeftShooterPower() {
        return leftShooter.getPower();
    }

    public void fireArtifactTeleop() {
        leftShooter.setPower(1.0);
        rightShooter.setPower(1.0);
    }

    public void shooterZeroPower() {
        leftShooter.setPower(0);
        rightShooter.setPower(0);
    }

    public Action collectArtifactAuto() {
        return new CollectArtifactAuto();
    }

    public class CollectArtifactAuto implements Action {

        private boolean initialized = false;
        public ElapsedTime runtime = new ElapsedTime();
        public final double SHOOT_TIME = 3.0;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            if (!initialized) {
                leftShooter.setPower(0.8);
                rightShooter.setPower(0.8);
                runtime.reset();
                initialized = true;
            }

            double getLeftShooterPower = leftShooter.getPower();
            double getRightShooterPower = rightShooter.getPower();

            packet.put("Left Shooter Power", getLeftShooterPower); //send to FTC dashboard
            packet.put("Right Shooter Power", getRightShooterPower); //send to FTC dashboard

            if (runtime.seconds() < SHOOT_TIME) {
                return true;
            } else {
                shooterZeroPower();
                return false;
            }

        }
    }

}