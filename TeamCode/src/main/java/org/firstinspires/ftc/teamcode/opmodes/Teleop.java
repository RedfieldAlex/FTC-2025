package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer;

@TeleOp (name = "Teleop", group = "Robot")
public class Teleop extends LinearOpMode {

    RobotContainer robotContainer;

    @Override
    public void runOpMode() {

        robotContainer = new RobotContainer(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            robotContainer.collectorFunc(gamepad2);
            robotContainer.driveFunc(gamepad1);
            robotContainer.shooterFunc(gamepad2);

        }

    }

}
