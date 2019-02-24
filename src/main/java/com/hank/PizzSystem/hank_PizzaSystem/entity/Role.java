package com.hank.PizzSystem.hank_PizzaSystem.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  @ManyToMany(mappedBy= "roles")
  private Collection<Staff> staffs;

  @ManyToMany
  @JoinTable(
      name = "roles_privileges",
      joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
  private Collection<Privilege> privileges;

  public Role() {
  }

  public Role(String name) {
    this.name = name;
  }

  public Collection<Staff> getStaffs() {
    return staffs;
  }

  public void setStaffs(Collection<Staff> staffs) {
    this.staffs = staffs;
  }

  public Collection<Privilege> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(Collection<Privilege> privileges) {
    this.privileges = privileges;
  }
}
