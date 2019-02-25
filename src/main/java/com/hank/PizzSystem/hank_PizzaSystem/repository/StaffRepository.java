package com.hank.PizzSystem.hank_PizzaSystem.repository;


import com.hank.PizzSystem.hank_PizzaSystem.entity.Staff;
import com.hank.PizzSystem.hank_PizzaSystem.model.StaffDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
  Staff findByEmail(String email);

//  List<Staff> findByLastName(String lastName);
  List<Staff> findByLastName(String lastName);


}
