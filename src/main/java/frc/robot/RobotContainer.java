// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import Subsystems.Drive.Drivetrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;


public class RobotContainer {

  public XboxController controller = new XboxController(0);
  double angleRadians;

  Drivetrain drivetrain = new Drivetrain();
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    drivetrain.driveXY(0, angleRadians);
  }
  public void  Periodic(){
    angleRadians = Math.atan2(controller.getLeftY(),controller.getLeftX());
  }
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
