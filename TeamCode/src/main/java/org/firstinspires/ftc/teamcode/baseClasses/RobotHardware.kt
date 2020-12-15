package org.firstinspires.ftc.teamcode.baseClasses

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference
import org.firstinspires.ftc.robotcore.external.navigation.Orientation

class RobotHardware(hardwareMap: HardwareMap) {
    val base: Base = Base(hardwareMap)

    /// I couldn't get `hardwareMap.get(BNO055IMU::class, "imu")` to work in Kotlin, so I wrote
    /// a helper class `ImuBridge` in Java to fetch the IMU for me from an environment where
    /// `hardwareMap.get(BNO055IMU::class, "imu")` works
    /**
     * The IMU sensor on the robot. Used for getting the robot's orientation among other things
     */
    private val imu: BNO055IMU = ImuBridge.get(hardwareMap)
//    private lateinit var imu: BNO055IMU


    fun getOrientation(): Orientation {
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }


    init {
        initImu()
    }


    private fun initImu() {
        val imuParams = BNO055IMU.Parameters()
        imuParams.angleUnit = BNO055IMU.AngleUnit.DEGREES
        imu.initialize(imuParams)
    }


    class Base(hardwareMap: HardwareMap) {
        val leftFront: DcMotor = hardwareMap.dcMotor.get("leftFrontMotor")
        val leftBack: DcMotor = hardwareMap.dcMotor.get("leftBackMotor")
        val rightFront: DcMotor = hardwareMap.dcMotor.get("rightFrontMotor")
        val rightBack: DcMotor = hardwareMap.dcMotor.get("rightBackMotor")

//        lateinit var leftFront: DcMotor
//        lateinit var leftBack: DcMotor
//        lateinit var rightFront: DcMotor
//        lateinit var rightBack: DcMotor

        val motors: Array<DcMotor>
            get() = arrayOf(leftFront, leftBack, rightFront, rightBack)
    }
}