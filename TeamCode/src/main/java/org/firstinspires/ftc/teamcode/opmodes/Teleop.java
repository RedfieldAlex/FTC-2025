package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer;

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
