package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Drivetrain {

    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private final OpMode opMode;

    public Drivetrain(OpMode opMode) {
        this.opMode = opMode;
    }

    public void initDrivetrain() {
        frontLeftDrive = opMode.hardwareMap.get(DcMotor.class, "front_left");
        frontRightDrive = opMode.hardwareMap.get(DcMotor.class, "front_right");
        backLeftDrive = opMode.hardwareMap.get(DcMotor.class, "back_left");
        backRightDrive = opMode.hardwareMap.get(DcMotor.class, "back_right");
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void telemetryDrivetrain() {
        opMode.telemetry.addLine("=== Drivetrain Telemetry ===");
        opMode.telemetry.addData("Front Left Power", frontLeftDrive.getPower());
        opMode.telemetry.addData("Front Right Power", frontRightDrive.getPower());
        opMode.telemetry.addData("Back Left Power", backLeftDrive.getPower());
        opMode.telemetry.addData("Back Right Power", backRightDrive.getPower());
        opMode.telemetry.update();
    }

    public void drivetrainFunc() {
        double strafe;
        double drive;
        double turn;
        double max;

        drive = -opMode.gamepad1.left_stick_y;
        turn  =  opMode.gamepad1.right_stick_x;
        strafe = opMode.gamepad1.left_stick_x;

        // Combine drive and turn for blended motion.
        double frontLeftPower = drive + strafe + turn;
        double frontRightPower = drive - strafe - turn;
        double backLeftPower = drive - strafe + turn;
        double backRightPower = drive + strafe - turn;

        // Normalize the values so neither exceed +/- 1.0
        max = Math.max(Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
                Math.max(Math.abs(backLeftPower), Math.abs(backRightPower)));

        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        if (opMode.gamepad1.x) {
            // Scale down to half speed if the left bumper is held
            frontLeftPower *= 0.5;
            frontRightPower *= 0.5;
            backLeftPower *= 0.5;
            backRightPower *= 0.5;
        }
        else if (opMode.gamepad2.b) {
            // Scale down to one-third speed if the right bumper is held
            frontLeftPower *= 0.33;
            frontRightPower *= 0.33;
            backLeftPower *= 0.33;
            backRightPower *= 0.33;
        }

        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }

}

