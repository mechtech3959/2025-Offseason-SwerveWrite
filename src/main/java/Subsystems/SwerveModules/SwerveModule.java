package Subsystems.SwerveModules;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.AngularVelocity;

public class SwerveModule {
   private TalonFX driveMotor;
   private TalonFX turnMotor;
   private CANcoder turnEncoder;
   private SwerveModuleState state;
   private PositionVoltage turnProfile;
   private VelocityVoltage driveProfile;
   private Rotation2d setAngle;
   private double setSpeed = 0;

   public SwerveModule(int driveID, int turnID, int encoderID, String canBus) {

      driveMotor = new TalonFX(driveID, canBus);
      turnMotor = new TalonFX(turnID, canBus);
      turnEncoder = new CANcoder(encoderID, canBus);

   }

   public void configure() {
      turnEncoder.getConfigurator().apply(new CANcoderConfiguration().withMagnetSensor(
            new MagnetSensorConfigs().withSensorDirection(SensorDirectionValue.CounterClockwise_Positive)));
      turnMotor.getConfigurator()
            .apply(new TalonFXConfiguration().withSlot0(new Slot0Configs().withKP(0.5).withKI(0).withKD(0.1))
                  .withFeedback(new FeedbackConfigs().withFusedCANcoder(turnEncoder)));

   }

   public SwerveModuleState getState() {
      return new SwerveModuleState(setSpeed, setAngle);
   }

   public void setState(SwerveModuleState State) {
      State.optimize(State.angle);
      turnMotor
            .setControl(turnProfile.withPosition(State.angle.getRadians()).withEnableFOC(true).withUseTimesync(true));
      setAngle = State.angle;
      driveMotor.setControl(driveProfile.withVelocity(State.speedMetersPerSecond));
      setSpeed = State.speedMetersPerSecond;
   }

}
