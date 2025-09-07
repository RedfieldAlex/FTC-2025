package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.roadrunner.*;
import org.firstinspires.ftc.teamcode.subsystems.*;

public class Auto extends LinearOpMode {

RobotContainer robotContainer;
Arm arm;

    @Override
    public void runOpMode() {

        Pose2d myPose = new Pose2d(10, -5, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, myPose);

        robotContainer = new RobotContainer(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                drive.actionBuilder(myPose)
                        .strafeTo(new Vector2d(3,4))
                        .afterTime(1, arm.armPos1000())
                        .build()




        );

    }


}
