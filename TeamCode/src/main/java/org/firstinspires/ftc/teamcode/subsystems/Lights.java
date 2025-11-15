package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LED;

public class Lights {

    private LED light;
    private RevBlinkinLedDriver blinkinLedDriver;
    private RevColorSensorV3 colorSensorV3;


    private final OpMode opMode;

    public Lights(OpMode opMode) {
        this.opMode = opMode;
    }

    public void initLights() {
        light = opMode.hardwareMap.get(LED.class, "idk");
        blinkinLedDriver = opMode.hardwareMap.get(RevBlinkinLedDriver.class, "idk");
        colorSensorV3 = opMode.hardwareMap.get(RevColorSensorV3.class, "idk");
    }



}
