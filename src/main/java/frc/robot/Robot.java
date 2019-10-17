/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  private Joystick m_controller;
  private Compressor m_compressor;
  private DoubleSolenoid m_piston;

  @Override
  public void robotInit() {
    m_controller = new Joystick(Mappings.xboxController);
    m_compressor = new Compressor(Mappings.idPCM);
    m_piston = new DoubleSolenoid(Mappings.portSolenoidIn, Mappings.portSolenoidOut);
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    // Enable compressor when button is pressed (closed loop meaning when pressure switch is not activated)
    m_compressor.setClosedLoopControl(m_controller.getRawButton(Mappings.btnCompressor));
    
    // Extend or retract piston when appropriate button is pressed
    if(m_controller.getRawButton(Mappings.btnSolenoidIn)){
      m_piston.set(DoubleSolenoid.Value.kForward);
    }else if(m_controller.getRawButton(Mappings.btnSolenoidOut)){
      m_piston.set(DoubleSolenoid.Value.kReverse);
    }else{
      m_piston.set(DoubleSolenoid.Value.kOff);
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
