package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.MainTeleop;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;
import org.firstinspires.ftc.teamcode.Util.*;


/**
 * Created by Ethan Pereira on 11/16/2017.
 */
@Autonomous(name = "BlueGlyphy")
public class BlueGlyphy extends LinearOpMode {

    Robot robot = new Robot();
    Gyro gyro = new Gyro();
    ElapsedTime timer = new ElapsedTime();
    RobotConstants constant = new RobotConstants();


    @Override
    public void runOpMode() throws InterruptedException {


        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, this, gyro);

        double heading;

        waitForStart();
        if (opModeIsActive()){

//            robot.jewelArm.armDown();

            sleep(1000);

//

//            if(String.valueOf(robot.jewelArm.getColor()) == "RED"){
//
//                robot.driveTrain.setMoveDist(4);
//                dist-=4;
//
//            }
//
//
//            else if(String.valueOf(robot.jewelArm.getColor()) == "BLUE"){
//
//                robot.driveTrain.setMoveDist(-4);
//
//                dist+=4;
//
//            }
            robot.jewelArm.armMid();

            robot.driveTrain.setMoveDist(17);

            VisionUtil visionUtil = new VisionUtil();

            RelicRecoveryVuMark reading = visionUtil.readGraph(hardwareMap);

            sleep(2000);

            telemetry.addData("vumark 1", reading);
            telemetry.update();

            robot.driveTrain.setMoveDist(-68);


            switch (reading){
                case RIGHT:{
                    robot.driveTrain.setMoveDist(-24);
                    robot.driveTrain.rotateDeg(87.5);

                    placeBlock();

                    robot.driveTrain.rotateDeg(190);

                    break;
                }
                case CENTER:{

                    robot.driveTrain.setMoveDist(-10);
                    robot.driveTrain.rotateDeg(87.5);

                    placeBlock();

                    robot.driveTrain.rotateDeg(180);


                    break;
                }
                case LEFT:{
                    robot.driveTrain.rotateDeg(87.5);

                    placeBlock();
                    robot.driveTrain.rotateDeg(170);

                    break;
                }
                default:{

                    robot.driveTrain.rotateDeg(87.5);
                    placeBlock();

                    robot.driveTrain.rotateDeg(170);

                    break;

                }
            }


            timer.startTime();
            int startLeftEnc = robot.driveTrain.getLeftCurrentPosition();
            while(robot.bumper.isPressed() && opModeIsActive() && Math.abs(robot.driveTrain.getLeftCurrentPosition() - startLeftEnc) < (constant.getTICKS_PER_INCH() * 62.5)){

                if (timer.milliseconds() <= 3000) {

                    robot.wheels.intakeRight();
                    robot.wheels.intakeLeft();


                }
                else{

                    robot.wheels.intakeLeft();
                    robot.wheels.setRightWheels(0);

                }
                robot.driveTrain.setLeftPower(1);
                robot.driveTrain.setRightPower(1);



            }

            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);

            sleep(1000);

            int leftTarget = robot.driveTrain.getLeftCurrentPosition() - startLeftEnc;


            switch (reading){
                case RIGHT:{
                    robot.driveTrain.rotateDeg(-185);

                    break;
                }
                case CENTER:{
                    robot.driveTrain.rotateDeg(-180);
                    break;
                }
                case LEFT:{
                    robot.driveTrain.rotateDeg(-175);
                    break;
                }
                default:{
                    robot.driveTrain.rotateDeg(-175);

                    break;

                }
            }


            robot.driveTrain.setLeftPower(0);
            robot.driveTrain.setRightPower(0);
            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);

            robot.bar4.setPower(1);
            sleep(1200);
            robot.bar4.setPower(0);

            robot.driveTrain.setMoveDistEnc(leftTarget- (5 * constant.getTICKS_PER_INCH()));
            placeBlock();
            robot.bar4.setPower(0);
            robot.driveTrain.setMoveDist(-10);
            robot.driveTrain.rotateDeg(180);
            robot.driveTrain.setMoveDist(-35);
            robot.driveTrain.setMoveDist(10);

        }


    }

    public void placeBlock(){

        robot.driveTrain.setMoveDist(4);
        robot.wheels.setRightWheels(-1);
        robot.wheels.setLeftWheelPwr(-1);
        sleep(750);
        robot.wheels.stopLeft();
        robot.wheels.stopRight();
        robot.driveTrain.setMoveDist(-8);
    }
}
