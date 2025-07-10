package org.firstinspires.ftc.teamcode.Cachiers;

import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.armDown;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.armUp;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.basketDown;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.basketUp;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.claw1Close;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.claw1Open;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.claw2Close;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.claw2Open;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.wheelieDown;
import static org.firstinspires.ftc.teamcode.Cachiers.RobotConstants.wheelieUp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "TeleOP")
public class Teleop extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();

        boolean pressingA1 = false;
        boolean wheelieIsDown = false;
        boolean pressingRightBumper = false;
        boolean clawIsClosed = false;
        boolean pressingA2 = false;
        boolean liftIsUp = false;
        boolean basketArmIsUp = false;
        boolean pressingLeftBumper = false;
        boolean pressingLeftBumper1 = false;
        double speed = 1.0;

        waitForStart();
        while (opModeIsActive()){
            double movement = -gamepad1.left_stick_y;
            double turning = gamepad1.right_stick_x;

            double left = movement + turning;
            double right = movement - turning;

            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0){
                left /= max;
                right /= max;
            }

            if (gamepad1.left_bumper && !pressingLeftBumper1){
                speed = 0.5;
                pressingLeftBumper1 = true;
            }else if(!gamepad1.left_bumper){
                speed = 1;
                pressingLeftBumper1 = false;
            }
            robot.left.setPower(left*speed);
            robot.right.setPower(right*speed);

            if (gamepad1.a && !pressingA1 && !wheelieIsDown){
                robot.wheelie.setPosition(wheelieDown);
                wheelieIsDown = true;
                pressingA1 = true;
            }
            else if (gamepad1.a && !pressingA1 && wheelieIsDown){
                robot.wheelie.setPosition(wheelieUp);
                wheelieIsDown = false;
                pressingA1 = true;
            }
            else if (!gamepad1.a && pressingA1){
                pressingA1 = false;
            }


            if (gamepad2.right_bumper && !pressingRightBumper && !clawIsClosed){
                robot.claw1.setPosition(claw1Close);
                robot.claw2.setPosition(claw2Close);
                clawIsClosed = true;
                pressingRightBumper = true;
            }
            else if (gamepad2.right_bumper && !pressingRightBumper && clawIsClosed){
                robot.claw1.setPosition(claw1Open);
                robot.claw2.setPosition(claw2Open);
                clawIsClosed = false;
                pressingRightBumper = true;
            }
            else if (!gamepad2.right_bumper && pressingRightBumper){
                pressingRightBumper = false;
            }


            if (gamepad2.a && !pressingA2 && !liftIsUp){
                robot.arm.setTargetPosition(armUp);
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(1);
                liftIsUp = true;
                pressingA2 = true;
            }
            else if (gamepad2.a && !pressingA2 && liftIsUp){
                robot.arm.setTargetPosition(armDown);
                robot.wheelie.setPosition(wheelieUp);
                wheelieIsDown = false;
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setPower(1);
                liftIsUp = false;
                pressingA2 = true;
            }
            else if (!gamepad2.a && pressingA2){
                pressingA2 = false;
            }


            if (gamepad2.left_bumper && !pressingLeftBumper && !basketArmIsUp){
                robot.bucket.setTargetPosition(basketUp);
                robot.bucket.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.bucket.setPower(1);
                basketArmIsUp = true;
                pressingLeftBumper = true;
            }
            else if (gamepad2.left_bumper && !pressingLeftBumper && basketArmIsUp){
                robot.bucket.setTargetPosition(basketDown);
                robot.bucket.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.bucket.setPower(1);
                basketArmIsUp = false;
                pressingLeftBumper = true;
            }
            else if (!gamepad2.left_bumper && pressingLeftBumper){
                pressingLeftBumper = false;
            }

            if (robot.arm.getCurrentPosition() <= armDown && !liftIsUp){
                robot.arm.setPower(0);
            }else if(robot.arm.getCurrentPosition() >= armUp && liftIsUp){
                robot.arm.setPower(0);
            }

            if (gamepad2.right_stick_x > .1){

            }

        }
        if (gamepad2.right_trigger > .1){

        }
    }
}
