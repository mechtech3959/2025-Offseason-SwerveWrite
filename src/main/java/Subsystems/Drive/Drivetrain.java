package Subsystems.Drive;

import Subsystems.SwerveModules.SwerveModule;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.Odometry3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry3d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    SwerveModule frontLeft;
    SwerveModule frontRight;
    SwerveModule backLeft;
    SwerveModule backRight;
    SwerveDriveKinematics kinematics;
    SwerveDriveOdometry3d odometry3d;
    private final Translation2d FLpose;
    private final Translation2d FRpose;
    private final Translation2d BLpose;
    private final Translation2d BRpose;
    public  SwerveModule[] driveArray = new SwerveModule[]{frontLeft,frontRight,backLeft,backRight};

    public Drivetrain() {
        FLpose = new Translation2d(12.25, 12.25);
        FRpose = new Translation2d(-12.25, 12.25);
        BLpose = new Translation2d(12.25, -12.25);
        BRpose = new Translation2d(-12.25, -12.25);
        frontLeft = new SwerveModule(7, 8, 12, "rio");
        frontRight = new SwerveModule(1, 3, 13, "rio");
        backLeft = new SwerveModule(5, 6, 14, "rio");
        backRight = new SwerveModule(2, 4, 15, "rio");
        kinematics = new SwerveDriveKinematics(FLpose,FRpose,BLpose,BRpose);
    }
// i think this is robot centric......
    public void driveXY(double vel, Rotation2d theta){
            driveArray[0].setState(new SwerveModuleState(vel,theta));
            driveArray[1].setState(new SwerveModuleState(vel,theta));
            driveArray[2].setState(new SwerveModuleState(vel,theta));
            driveArray[3].setState(new SwerveModuleState(vel,theta));

        }


    }
