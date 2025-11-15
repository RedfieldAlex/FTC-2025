package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp (name = "Teleop", group = "Robot")
public class Teleop extends OpMode {

    Collector collector;
    Drivetrain drivetrain;
    Shooter shooter;

    @Override
    public void init() {
        collector = new Collector(this);
        collector.initCollector();

        drivetrain = new Drivetrain(this);
        drivetrain.initDrivetrain();

        shooter = new Shooter(this);
        shooter.initShooter();
    }

    @Override
    public void init_loop() {
        collector.telemetryCollector();
        drivetrain.telemetryDrivetrain();
        shooter.telemetryShooter();
    }

    @Override
    public void loop() {
        collector.collectorFunc();
        collector.telemetryCollector();

        drivetrain.drivetrainFunc();
        drivetrain.telemetryDrivetrain();

        shooter.shooterFunc();
        shooter.telemetryShooter();
    }

}
