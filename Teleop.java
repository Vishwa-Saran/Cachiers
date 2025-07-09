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

@TeleOp (name = "Demo TeleOP")
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

        waitForStart();
        while (opModeIsActive()){
            double movement = -gamepad1.left_stick_x;
            double turning = gamepad1.right_stick_y;

            double left = movement + turning;
            double right = movement - turning;

            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0){
                left /= max;
                right /= max;
            }
            robot.left.setPower(left);
            robot.right.setPower(right);


            robot.arm.setPower(1);

            robot.bucket.setPower(1);

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
                liftIsUp = true;
                pressingA2 = true;
            }
            else if (gamepad2.a && !pressingA2 && liftIsUp){
                robot.arm.setTargetPosition(armDown);
                liftIsUp = false;
                pressingA2 = true;
            }
            else if (!gamepad2.a && pressingA2){
                pressingA2 = false;
            }


            if (gamepad2.left_bumper && !pressingLeftBumper && !basketArmIsUp){
                robot.bucket.setTargetPosition(basketUp);
                basketArmIsUp = true;
                pressingLeftBumper = true;
            }
            else if (gamepad2.left_bumper && !pressingLeftBumper && basketArmIsUp){
                robot.bucket.setTargetPosition(basketDown);
                basketArmIsUp = false;
                pressingLeftBumper = true;
            }
            else if (!gamepad2.left_bumper && pressingLeftBumper){
                pressingLeftBumper = false;
            }


            if (gamepad2.right_stick_x > .1){

            }

        }
        if (gamepad2.right_trigger > .1){

        }
    }
}
