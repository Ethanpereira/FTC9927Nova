package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;

/**
 * Created by Sumanth on 11/9/17.
 */


@Autonomous(name = "SetMoveDist")
@Disabled
public class DriveSetMoveDistTest extends LinearOpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap,this, this.gyro);

        waitForStart();

        if(opModeIsActive())
        {
            robot.driveTrain.setMoveDist(24);
//            robot.driveTrain.setMoveDist(-24);
            telemetry.addData("dt",robot.driveTrain.display());
            telemetry.update();
        }

    }
}
