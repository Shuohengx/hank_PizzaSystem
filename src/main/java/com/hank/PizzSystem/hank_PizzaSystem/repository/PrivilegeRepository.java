package com.hank.PizzSystem.hank_PizzaSystem.repository;


import com.hank.PizzSystem.hank_PizzaSystem.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
  Privilege findByName(String name);
}
