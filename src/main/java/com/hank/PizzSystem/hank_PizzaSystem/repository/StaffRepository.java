package com.hank.PizzSystem.hank_PizzaSystem.repository;


import com.hank.PizzSystem.hank_PizzaSystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
  Staff findByEmail(String email);
}
