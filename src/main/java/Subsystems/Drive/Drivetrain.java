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
        frontLeft = new SwerveModule(1, 2, 3, getName());
        frontRight = new SwerveModule(4, 5, 6, getName());
        backLeft = new SwerveModule(7, 8, 9, getName());
        backRight = new SwerveModule(10, 11, 12, getName());
    }
// i think this is robot centric......
    public void driveXY(double vel, double theta){
        for(SwerveModule i : driveArray){
            i.setState(new SwerveModuleState(vel,new Rotation2d(theta)));
        }


    }
}
