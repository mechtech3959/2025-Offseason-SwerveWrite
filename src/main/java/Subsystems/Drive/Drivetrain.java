package Subsystems.Drive;

import Subsystems.SwerveModules.SwerveModule;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.Odometry3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    SwerveModule frontLeft;
    SwerveModule frontRight;
    SwerveModule backLeft;
    SwerveModule backRight;
    SwerveDriveKinematics kinematics;
    SwerveDriveOdometry3d odometry3d;
    private final Translation2d FLpose;
public Drivetrain(){
    
}    
}
