package com.hank.PizzSystem.hank_PizzaSystem.security;


import com.hank.PizzSystem.hank_PizzaSystem.entity.Privilege;
import com.hank.PizzSystem.hank_PizzaSystem.entity.Role;
import com.hank.PizzSystem.hank_PizzaSystem.entity.Staff;
import com.hank.PizzSystem.hank_PizzaSystem.repository.PrivilegeRepository;
import com.hank.PizzSystem.hank_PizzaSystem.repository.RoleRepository;
import com.hank.PizzSystem.hank_PizzaSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  boolean alreadySetup = false;

  @Autowired
  private StaffRepository staffRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PrivilegeRepository privilegeRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {

    if (alreadySetup) {
      return;
    }

    Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
    Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

    List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
    createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

    Role adminRole = roleRepository.findByName("ROLE_ADMIN");
    if (staffRepository.findByEmail("test@test.com") == null) {
      Staff user = new Staff();
      user.setFirstName("Test");
      user.setLastName("Test");
      user.setPassword(passwordEncoder.encode("test"));
      user.setEmail("test@test.com");
      user.setRoles(Arrays.asList(adminRole));
      user.setEnabled(true);
      staffRepository.save(user);




    }

    alreadySetup = true;
  }

  private Privilege createPrivilegeIfNotFound(String name) {

    Privilege privilege = privilegeRepository.findByName(name);
    if (privilege == null) {
      privilege = new Privilege(name);
      privilegeRepository.save(privilege);
    }
    return privilege;
  }

  private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name);
      role.setPrivileges(privileges);
      roleRepository.save(role);
    }
    return role;
  }

}

