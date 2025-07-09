package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class HardwareDemo {

    public DcMotor right;
    public DcMotor left;
    public Servo clawServo;

    public static double maxSpeed = 1;

    private static HardwareDemo myInstance = null;

    public static HardwareDemo getInstance() {

        if (myInstance == null) {
            myInstance = new HardwareDemo();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {
        //initialize motors
        right = hwMap.get(DcMotor.class, "right");
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setPower(0);

        left = hwMap.get(DcMotor.class, "left");
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setDirection(DcMotor.Direction.REVERSE);
        left.setPower(0);
        //initialize servo for claw
        clawServo = hwMap.get(Servo.class, "clawServo");
    }

    public void setPower(double motor1, double motor2) {
        right.setPower(Range.clip(motor1, -maxSpeed, maxSpeed));
        left.setPower(Range.clip(motor2, -maxSpeed, maxSpeed));
    }


    }
