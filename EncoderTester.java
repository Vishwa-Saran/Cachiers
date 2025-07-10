package org.firstinspires.ftc.teamcode.Cachiers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "EncoderTester")
public class EncoderTester extends LinearOpMode {
    Hardware robot = Hardware.getInstance();

    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();
        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
boolean pressingA = false;
boolean pressingB = false;
 int position = 0;
 int positionBucket = 0;
 boolean pressingX = false;
 boolean pressingY = false;
 waitForStart();
        while(opModeIsActive()){
            robot.arm.setTargetPosition(position);
            robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.arm.setPower(1);

            if(gamepad1.a && !pressingA){
                position+=10;
                pressingA = true;
            } else if (!gamepad1.a){
                pressingA = false;
            }
            if(gamepad1.b && !pressingB){
                position-=10;
                pressingB = true;
            } else if (!gamepad1.b){
                pressingB = false;
            }

            robot.bucket.setTargetPosition(positionBucket);
            robot.bucket.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.bucket.setPower(1);

            if(gamepad1.x && !pressingX){
                positionBucket+=10;
                pressingX = true;
            } else if (!gamepad1.x){
                pressingX = false;
            }
            if(gamepad1.y && !pressingY){
                positionBucket-=10;
                pressingY = true;
            } else if (!gamepad1.y){
                pressingY = false;
            }
            telemetry.addData("armPostion", position);
            telemetry.addData("bucketPosition", positionBucket);
            telemetry.update();

        }
    }
}
