package com.hank.PizzSystem.hank_PizzaSystem.service;

import com.hank.PizzSystem.hank_PizzaSystem.entity.Staff;
import com.hank.PizzSystem.hank_PizzaSystem.exception.StaffAlreadyExistException;
import com.hank.PizzSystem.hank_PizzaSystem.model.StaffDto;
import com.hank.PizzSystem.hank_PizzaSystem.repository.RoleRepository;
import com.hank.PizzSystem.hank_PizzaSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class StaffService {
  @Autowired
  private StaffRepository staffRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private SessionRegistry sessionRegistry;

  public Staff registerNewStaffAccount(final StaffDto staffDto) {
    if (emailExists(staffDto.getEmail())) {
      throw new StaffAlreadyExistException("There is an account with that email adress: " + staffDto.getEmail());
    }
    final Staff staff = new Staff();

    staff.setFirstName(staffDto.getFirstName());
    staff.setLastName(staffDto.getLastName());
    staff.setPassword(passwordEncoder.encode(staffDto.getPassword()));
    staff.setEmail(staffDto.getEmail());
    staff.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
    return staffRepository.save(staff);
  }

  private boolean emailExists(final String email) {
    return staffRepository.findByEmail(email) != null;
  }


  public List<Staff> listByLastName(String lastName){
    return staffRepository.findByLastName(lastName);

  }



}
