package org.firstinspires.ftc.teamcode.Cachiers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Hardware {

    public DcMotor right, left, bucket, arm;
    public Servo claw1, claw2, wheelie;

    public static double maxSpeed = 1;

    private static Hardware myInstance = null;

    public static Hardware getInstance() {

        if (myInstance == null) {
            myInstance = new Hardware();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {
        //initialize motors
        right = hwMap.get(DcMotor.class, "cm0");
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setDirection(DcMotor.Direction.FORWARD);
        right.setPower(0);

        left = hwMap.get(DcMotor.class, "cm1");
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setDirection(DcMotor.Direction.REVERSE);
        left.setPower(0);

        bucket = hwMap.get(DcMotor.class, "cm2");
        bucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucket.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bucket.setDirection(DcMotor.Direction.FORWARD);
        bucket.setPower(0);

        arm = hwMap.get(DcMotor.class, "cm2");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setDirection(DcMotor.Direction.FORWARD);
        arm.setPower(0);
        //initialize servo for claw
        claw1 = hwMap.get(Servo.class, "cs1");
        claw2 = hwMap.get(Servo.class, "cs2");
        wheelie = hwMap.get(Servo.class, "cs0");

    }

    public void setPower(double motor1, double motor2) {
        right.setPower(Range.clip(motor1, -maxSpeed, maxSpeed));
        left.setPower(Range.clip(motor2, -maxSpeed, maxSpeed));
    }


    }
