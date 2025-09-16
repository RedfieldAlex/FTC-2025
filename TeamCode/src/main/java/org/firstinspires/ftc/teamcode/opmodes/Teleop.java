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

            robotContainer.armFunc(gamepad1);
            robotContainer.driving(gamepad1);

        }

    }

}
