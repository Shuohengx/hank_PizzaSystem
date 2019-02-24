package com.hank.PizzSystem.hank_PizzaSystem.service;


import com.hank.PizzSystem.hank_PizzaSystem.entity.Privilege;
import com.hank.PizzSystem.hank_PizzaSystem.entity.Role;
import com.hank.PizzSystem.hank_PizzaSystem.entity.Staff;
import com.hank.PizzSystem.hank_PizzaSystem.repository.RoleRepository;
import com.hank.PizzSystem.hank_PizzaSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  private StaffRepository staffRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {

    Staff user = staffRepository.findByEmail(email);
    if (user == null) {
      return new org.springframework.security.core.userdetails.User(
          " ", " ", true, true, true, true,
          getAuthorities(Arrays.asList(
              roleRepository.findByName("ROLE_USER"))));
    }

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
        true, getAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<Role> roles) {

    return getGrantedAuthorities(getPrivileges(roles));
  }


  //获取所有privileges
  private List<String> getPrivileges(Collection<Role> roles) {

    List<String> privileges = new ArrayList<>();
    List<Privilege> collection = new ArrayList<>();
    for (Role role : roles) {
      collection.addAll(role.getPrivileges());
    }
    for (Privilege item : collection) {
      privileges.add(item.getName());
    }
    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
