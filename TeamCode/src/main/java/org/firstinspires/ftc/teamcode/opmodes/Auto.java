package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@Autonomous (name = "Auto", group = "Robot", preselectTeleOp = "Teleop")
public class Auto extends LinearOpMode {

    Collector collector;
    Drivetrain drivetrain;
    Shooter shooter;

    @Override
    public void runOpMode() {

        collector = new Collector(this);
        drivetrain = new Drivetrain(this);
        shooter = new Shooter(this);

        waitForStart();


    }



}
