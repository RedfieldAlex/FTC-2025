package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@Autonomous (name = "Auto", group = "Robot", preselectTeleOp = "Teleop")
public class Auto extends LinearOpMode {

    Arm arm;
    Drivetrain drivetrain;

    @Override
    public void runOpMode() {

        arm = new Arm(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);


        waitForStart();

        Actions.runBlocking(new SequentialAction(






        ));
    }



}
