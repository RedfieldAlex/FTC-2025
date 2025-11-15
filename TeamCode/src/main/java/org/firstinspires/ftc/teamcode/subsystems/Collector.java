package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Collector {

    private DcMotor collector;

    private final OpMode opMode;

    public Collector(OpMode opMode) {
        this.opMode = opMode;
    }

    public void initCollector() {
        collector = opMode.hardwareMap.get(DcMotor.class, "collector");
        collector.setDirection(DcMotor.Direction.REVERSE);
        collector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void telemetryCollector() {
        opMode.telemetry.addLine("=== Collector Telemetry ===");
        opMode.telemetry.addData("Collector Power", collector.getPower());
        opMode.telemetry.update();
    }

    // button mapping

    public void collectorFunc() {
        if (opMode.gamepad1.left_bumper) {
            intake();
        }
        if (opMode.gamepad1.right_bumper) {
            reverseIntake();
        } else {
            collectorOff();
        }
    }

    // collector section

    public void intake() {
        collector.setPower(1);
    }

    public void reverseIntake() {
        collector.setPower(-1);
    }

    public void collectorOff() {
        collector.setPower(0);
    }

}
