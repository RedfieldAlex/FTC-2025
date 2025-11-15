package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {

    private DcMotor leftFlywheel;
    private DcMotor rightFlywheel;

    private Servo leftTrigger;
    private Servo rightTrigger;

    private final OpMode opMode;

    boolean leftBumperPressedLast = false;
    boolean rightBumperPressedLast = false;

    boolean leftFlywheelToggled = false;
    boolean rightFlywheelToggled = false;


    public Shooter(OpMode opMode) {
        this.opMode = opMode;
    }

    public void initShooter(){
        leftFlywheel = opMode.hardwareMap.get(DcMotor.class, "left_fly");
        rightFlywheel = opMode.hardwareMap.get(DcMotor.class, "right_fly");
        leftFlywheel.setDirection(DcMotor.Direction.REVERSE);
        rightFlywheel.setDirection(DcMotor.Direction.FORWARD);
        leftFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftTrigger = opMode.hardwareMap.get(Servo.class, "left_servo");
        rightTrigger = opMode.hardwareMap.get(Servo.class, "right_servo");
        leftTrigger.setDirection(Servo.Direction.REVERSE);
        rightTrigger.setDirection(Servo.Direction.FORWARD);
    }

    public void telemetryShooter() {
        opMode.telemetry.addLine("=== Shooter Telemetry ===");
        opMode.telemetry.addData("Left Flywheel Power", leftFlywheel.getPower());
        opMode.telemetry.addData("Right Flywheel Power", rightFlywheel.getPower());
        opMode.telemetry.addData("Left Flywheel Toggled", leftFlywheelToggled);
        opMode.telemetry.addData("Right Flywheel Toggled", rightFlywheelToggled);

        opMode.telemetry.addData("Left Trigger Position", leftTrigger.getPosition());
        opMode.telemetry.addData("Right Trigger Position", rightTrigger.getPosition());
        
        opMode.telemetry.update();
    }

    // button mapping

    public void shooterFunc() {
        // flywheels
        boolean leftBumperPressed = opMode.gamepad2.left_bumper;
        boolean rightBumperPressed = opMode.gamepad2.right_bumper;

        if (leftBumperPressed && !leftBumperPressedLast) {  // Detect rising edge
            leftFlywheelToggled = !leftFlywheelToggled;           // Flip state
        }
        if (rightBumperPressed && !rightBumperPressedLast) {
            rightFlywheelToggled = !rightFlywheelToggled;
        }

        leftBumperPressedLast = leftBumperPressed;
        rightBumperPressedLast = rightBumperPressed;

        // left flywheel (toggle)
        if (leftFlywheelToggled) {
            leftFlywheelOn();
        } else {
            flywheelsOff();
        }

        // right flywheel (toggle)
        if (rightFlywheelToggled) {
            rightFlywheelOn();
        }
        else {
            flywheelsOff();
        }

        // triggers (hold)
        if (opMode.gamepad2.x) {
            leftTriggerFire();
        }
        if (opMode.gamepad2.b) {
            rightTriggerFire();
        } else {
            triggersOff();
        }
    }

    // flywheel section

    public void leftFlywheelOn() {
        leftFlywheel.setPower(0.75);
    }
    
    public void rightFlywheelOn() {
        rightFlywheel.setPower(0.75);
    }

    public void flywheelsOff() {
        leftFlywheel.setPower(0);
        rightFlywheel.setPower(0);
    }

    // trigger section

    public void leftTriggerFire() {
        leftTrigger.setPosition(1); // 1 means forwards for left servo
    }

    public void rightTriggerFire() {
        rightTrigger.setPosition(1); // 1 means forwards for right servo
    }

    public void triggersOff() {
        leftTrigger.setPosition(0.5); // 0.5 means stop for both servos. 0 means backwards for both
        rightTrigger.setPosition(0.5);
    }

}