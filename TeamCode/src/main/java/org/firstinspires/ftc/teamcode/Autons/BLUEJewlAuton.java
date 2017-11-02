package org.firstinspires.ftc.teamcode.Autons;

import org.firstinspires.ftc.teamcode.Subsystems.*;

import org.firstinspires.ftc.teamcode.Util.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Sumanth on 10/21/17.
 */

@Autonomous(name = "Blue Jewl Auto ")
public class BLUEJewlAuton extends LinearOpMode{

    Robot robot = new Robot();


    Gyro gyro = new Gyro();

    int dist = -45;



    @Override
    public void runOpMode() throws InterruptedException {

        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);
        waitForStart();



        robot.driveTrain.setLeftPower(-.2);
        robot.driveTrain.setRightPower(-.2);
        sleep(700);
        robot.driveTrain.setLeftPower(0);
        robot.driveTrain.setRightPower(0);        robot.jewelArm.armDown();
        if(opModeIsActive()){
//            robot.claw.close();


            if(String.valueOf(robot.jewelArm.getColor()) == "RED"){
                sleep(1000);

                robot.driveTrain.setLeftPower(.2);
                robot.driveTrain.setRightPower(.2);
                sleep(700);
                robot.driveTrain.setLeftPower(0);
                robot.driveTrain.setRightPower(0);
                dist+=2;

            }


            else if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){
                sleep(1000);
                robot.driveTrain.setLeftPower(-.2);
                robot.driveTrain.setRightPower(-.2);
                sleep(700);
                robot.driveTrain.setLeftPower(0);
                robot.driveTrain.setRightPower(0);

            }
            robot.jewelArm.armUp();
//
//            robot.driveTrain.setMoveDist(dist);
//            robot.driveTrain.rotateDeg(-90);



        }


    }


}