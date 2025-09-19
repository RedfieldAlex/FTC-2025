package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Collector {

    DcMotor leftCollector;
    DcMotor rightCollector;

    public Collector(HardwareMap hardwareMap) {
        leftCollector = hardwareMap.get(DcMotor.class, "Left_Shooter");
        rightCollector = hardwareMap.get(DcMotor.class, "Right_Shooter");
        leftCollector.setDirection(DcMotor.Direction.REVERSE);
        rightCollector.setDirection(DcMotorSimple.Direction.FORWARD);
        leftCollector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightCollector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void collectArtifactTeleop() {
        leftCollector.setPower(1.0);
        rightCollector.setPower(1.0);
    }

    public void stopCollectArtifact() {
        leftCollector.setPower(0);
        rightCollector.setPower(0);
    }


    public Action collectArtifactAuto() {
        return new CollectArtifactAuto();
    }

    public class CollectArtifactAuto implements Action {

        private boolean initialized = false;
        public ElapsedTime runtime = new ElapsedTime();
        public final double COLLECT_TIME = 3.0;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            if (!initialized) {
                leftCollector.setPower(0.8);
                rightCollector.setPower(0.8);
                runtime.reset();
                initialized = true;
            }

            double leftCollectorPower = leftCollector.getPower();
            double rightCollectorPower = rightCollector.getPower();

            packet.put("Left Collector Power", leftCollectorPower);
            packet.put("Right Collector Power", rightCollectorPower);


            if (runtime.seconds() < COLLECT_TIME) {
                return true;
            } else {
                leftCollector.setPower(0);
                rightCollector.setPower(0);
                return false;
            }
        }
    }

}
