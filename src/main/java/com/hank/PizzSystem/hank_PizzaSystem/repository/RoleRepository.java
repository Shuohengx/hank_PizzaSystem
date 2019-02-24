package com.hank.PizzSystem.hank_PizzaSystem.repository;


import com.hank.PizzSystem.hank_PizzaSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
