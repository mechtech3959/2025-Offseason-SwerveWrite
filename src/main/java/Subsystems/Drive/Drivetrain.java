package Subsystems.Drive;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.Pigeon2;

import Subsystems.SwerveModules.SwerveModule;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.Odometry3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry3d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    SwerveModule frontLeft;
    SwerveModule frontRight;
    SwerveModule backLeft;
    SwerveModule backRight;
    Pigeon2 gyro;
    SwerveDriveKinematics kinematics;
    SwerveDriveOdometry3d odometry3d;
    private final Translation2d FLpose;
    private final Translation2d FRpose;
    private final Translation2d BLpose;
    private final Translation2d BRpose;
    private final CANBus canBus = new CANBus("rio");
    public SwerveModule[] driveArray = new SwerveModule[] { frontLeft, frontRight, backLeft, backRight };
    public SwerveModulePosition[] modulePositions;

    public Drivetrain() {
        gyro = new Pigeon2(32, canBus);
        FLpose = new Translation2d(12.25, 12.25);
        FRpose = new Translation2d(-12.25, 12.25);
        BLpose = new Translation2d(12.25, -12.25);
        BRpose = new Translation2d(-12.25, -12.25);
        modulePositions = new SwerveModulePosition[] { frontLeft.getPosition(), frontRight.getPosition(),
                backLeft.getPosition(), backRight.getPosition() };
        frontLeft = new SwerveModule(7, 8, 12, canBus);
        frontRight = new SwerveModule(1, 3, 13, canBus);
        backLeft = new SwerveModule(5, 6, 14, canBus);
        backRight = new SwerveModule(2, 4, 15, canBus);
        kinematics = new SwerveDriveKinematics(FLpose, FRpose, BLpose, BRpose);
        odometry3d = new SwerveDriveOdometry3d(kinematics, gyro.getRotation3d(), modulePositions);
    }

    // i think this is robot centric...
    public void driveXY(double vel, Rotation2d theta) {
        driveArray[0].setState(new SwerveModuleState(vel, theta));
        driveArray[1].setState(new SwerveModuleState(vel, theta));
        driveArray[2].setState(new SwerveModuleState(vel, theta));
        driveArray[3].setState(new SwerveModuleState(vel, theta));

    }
    //TODO: field centric research... 
    //this def will not work but i am interests in the outcome
    public void driveField(double vel, Rotation2d theta){
        gyro.getYaw(); 
         
        driveArray[0].setState(new SwerveModuleState(vel, new Rotation2d(Units.degreesToRadians(gyro.getYaw().getValueAsDouble())-theta.getRadians())));
        driveArray[1].setState(new SwerveModuleState(vel, new Rotation2d(Units.degreesToRadians(gyro.getYaw().getValueAsDouble())-theta.getRadians())));
        driveArray[2].setState(new SwerveModuleState(vel, new Rotation2d(Units.degreesToRadians(gyro.getYaw().getValueAsDouble())-theta.getRadians())));
        driveArray[3].setState(new SwerveModuleState(vel, new Rotation2d(Units.degreesToRadians(gyro.getYaw().getValueAsDouble())-theta.getRadians())));
    }
    @Override
    public void periodic() {
        driveArray[0].updateData();
        driveArray[1].updateData();
        driveArray[2].updateData();
        driveArray[3].updateData();
        
        odometry3d.update(gyro.getRotation3d(), new SwerveModulePosition[] { frontLeft.getPosition(),
                frontRight.getPosition(), backLeft.getPosition(), backRight.getPosition() });
        
        super.periodic();
    }
}
