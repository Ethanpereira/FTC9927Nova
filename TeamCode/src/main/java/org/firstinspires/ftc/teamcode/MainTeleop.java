package org.firstinspires.ftc.teamcode;


import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Util.Gyro;
import org.firstinspires.ftc.teamcode.Util.PIDLoop;
import org.firstinspires.ftc.teamcode.Util.Sound;
import org.firstinspires.ftc.teamcode.Util.VisionUtil;

/**
 * Created by therat0981 on 10/1/17.
 */
@Disabled
@TeleOp(name = "TeleOpNew")

public class MainTeleop extends OpMode
{
    Robot robot = new Robot();
    Gyro gyro = new Gyro();

    @Override
    public void init()
    {
        gyro.initGyro(hardwareMap);
        robot.init(hardwareMap, gyro);
        robot.driveTrain.setDrive(DriveTrain.Drive.STOP_RESET);
        robot.driveTrain.setDrive(DriveTrain.Drive.SPEED);
    }

    public void loop()
    {
        //keeping arm up
        robot.jewelArm.armMid();

        //getting Current Angle of the Four Bar
        robot.bar4.getCurrentAngle();

//        //Driver Code
//        float yval = -gamepad1.left_stick_y;
//        float xval = gamepad1.right_stick_x;
//
//
//        float lpwr = (float) Math.pow(((yval + xval)), 3);
//        float rpwr = (float) Math.pow((yval - xval), 3);
        VisionUtil visionUtil = new VisionUtil();

        RelicRecoveryVuMark reading = visionUtil.readGraph(hardwareMap);




        float lval = -gamepad1.left_stick_y;
        float rval = -gamepad1.right_stick_y;

        float lpwr = (float) Math.pow((lval), 3);
        float rpwr = (float) Math.pow(rval, 3);




//        turtle mode
        if (gamepad1.right_trigger!=0 || gamepad1.left_trigger!=0)
        {
            lpwr = lpwr/3.0f;
            rpwr = rpwr/3.0f;
        }

//      Setting the power for dt
        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);


//      Claw and Extender for Relic
        if(gamepad2.a)
            robot.relic.clawOpen();
        if(gamepad2.b)
            robot.relic.clawClose();
        if(gamepad2.y)
            robot.relic.pullExtenderUp();
        if(gamepad2.x)
            robot.relic.putExtenderDown();

        // Arm Control
        if(Math.abs(gamepad2.left_stick_y)>0.05 ){

            robot.bar4.shouldStayTrue();
            robot.bar4.setPower(-gamepad2.left_stick_y);
            robot.bar4.setIsOnFalse();


        }
        else{

            robot.bar4.setTargetAngle();
            robot.bar4.setMoveAngle(robot.bar4.getTargetAngle());

        }


        // intake wheel
        if (robot.bumper.getState() && gamepad2.left_bumper){
            robot.wheels.setLeftWheelPwr(1);

        }
        else if(gamepad2.left_trigger != 0){

            robot.wheels.setLeftWheelPwr(-1);
            robot.wheels.setRightWheels(-1);


        }
        else{

            robot.wheels.setLeftWheelPwr(0);
            robot.wheels.setRightWheels(0);

        }

        if (robot.bumper.getState() && gamepad2.right_bumper){
            robot.wheels.setRightWheels(1);

        }
        else if(gamepad2.right_trigger != 0) {

            robot.wheels.setRightWheels(-1);
            robot.wheels.setLeftWheelPwr(-1);


        }
        else{
            robot.wheels.setRightWheels(0);
            robot.wheels.setLeftWheelPwr(0);

        }

        if(gamepad2.right_stick_y > .1 ){

            robot.wheels.setLeftWheelPwr(1);

        }
        else if(gamepad2.right_stick_y < -.1){

            robot.wheels.setLeftWheelPwr(-1);

        }
        else if(gamepad2.right_stick_x > .1 ){

            robot.wheels.setRightWheels(1);

        }
        else if(gamepad2.right_stick_x < -.1){

            robot.wheels.setRightWheels(-1);

        }



        //Zlides
        if(gamepad2.dpad_up)
        {
            robot.relic.setPower(1);
        }
        else if(gamepad2.dpad_down)
        {
            robot.relic.setPower(-1);
        }
        else
        {
            robot.relic.setPower(0);
        }

        if(-gamepad2.right_stick_y > 10){

            robot.jewelArm.armMid();

        }
        else if(-gamepad2.right_stick_y < -10){

            robot.jewelArm.armUp();

        }

        telemetry.addData("Graph: ", reading);
        telemetry.update();
    }



    public void stop()
    {

    }


}