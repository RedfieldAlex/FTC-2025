package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    DcMotor arm;

    public Arm(HardwareMap hardwareMap) {
        arm = hardwareMap.get(DcMotor.class, "Arm");
        arm.setDirection(DcMotor.Direction.FORWARD);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public int getArmCurrentPosition() {
        return arm.getCurrentPosition();
    }

    public Action armPos1000() {
        return new ArmPos1000();
    }

    public Action armPos2000() {
        return new ArmPos2000();
    }

    public class ArmPos1000 implements Action {

        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                arm.setTargetPosition(1000);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setPower(0.8);
                initialized = true;
            }

            double pos = arm.getCurrentPosition();
            packet.put("ArmPos", pos); //send to FTC dashboard
            if (arm.isBusy()) {
                return true;
            } else {
                arm.setPower(0);
                return false;
            }
        }
    }

    public class ArmPos2000 implements Action {

        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                arm.setTargetPosition(2000);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm.setPower(0.8);
                initialized = true;
            }

            double pos = arm.getCurrentPosition();
            packet.put("liftPos", pos);
            if (arm.isBusy()) {
                return true;
            } else {
                arm.setPower(0);
                return false;
            }
        }
    }
}
