package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * Created by therat0981 on 10/1/17.
 */
@TeleOp(name = "TeleOp")
@Disabled
public class MainTeleop extends OpMode
{
    Robot robot = new Robot();

    @Override
    public void init()
    {
        robot.init(hardwareMap);
    }

    public void loop()
    {

        //Driver Code
        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;


        float rpwr = (float) Math.pow((yval + xval), 3);
        float lpwr = (float) Math.pow((yval - xval), 3);


        //turtle mode
        if (gamepad1.right_bumper || gamepad1.left_bumper)
        {
            lpwr = lpwr/2.0f;
            rpwr = rpwr/2.0f;
        }

        robot.driveTrain.setLeftPower(lpwr);
        robot.driveTrain.setRightPower(rpwr);

        telemetry.addData("",robot.driveTrain.display());

        //Operator Code
        //claw
        //elevator

    }



    public void stop()
    {

    }


}
