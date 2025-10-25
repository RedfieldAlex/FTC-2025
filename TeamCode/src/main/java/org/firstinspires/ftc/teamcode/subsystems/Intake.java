package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    DcMotor intake;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "Intake");
        intake.setDirection(DcMotor.Direction.FORWARD);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public double getIntakePower() {
        return intake.getPower();
    }

    public void collectArtifactTeleop() {
        intake.setPower(1.0);
    }

    public void releaseArtifactTeleop() {
        intake.setPower(-1.0);
    }

    public void intakeZeroPower() {
        intake.setPower(0);
    }

    public class collectArtifactAuto implements Action {

        private boolean initialized = false;
        public ElapsedTime runtime = new ElapsedTime();
        public final double COLLECT_TIME = 3.0;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            if (!initialized) {
                intake.setPower(0.8);
                runtime.reset();
                initialized = true;
            }

            double getIntakePower = intake.getPower();

            packet.put("Intake Power", getIntakePower); //send to FTC dashboard

            if (runtime.seconds() < COLLECT_TIME) {
                return true;
            } else {
                intakeZeroPower();
                return false;
            }

        }
    }

}
