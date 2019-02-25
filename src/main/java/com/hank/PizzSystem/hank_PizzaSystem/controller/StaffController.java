package com.hank.PizzSystem.hank_PizzaSystem.controller;

import com.hank.PizzSystem.hank_PizzaSystem.entity.Staff;
import com.hank.PizzSystem.hank_PizzaSystem.service.StaffService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StaffController {


    @Autowired
    private StaffService staffService;
/*
    @RequestMapping("/staff")
    @Cacheable(value="staffCache")
    @ApiOperation(value="hello world", notes="")
    public List<Staff> getStaff(String lastName) {
        List<Staff> staffs = staffService.listByLastName(lastName);
        return staffs;
    }
*/



    @RequestMapping("/staff")
    @Cacheable(value="staffCache",key="#lastName")
    public List<Staff> getStaffs(String lastName) {
        List<Staff> staffs = staffService.listByLastName(lastName);
        System.out.println("getStaffs: execute database operation");
        return staffs;
    }




    @RequestMapping("/staffputcache")
    @CachePut(value="staffsCache",key="#lastName")
    public List<Staff> getPutStaffs(String lastName) {
        List<Staff> staffs = staffService.listByLastName(lastName);
        System.out.println("getPutStaffs: execute database operation");
        return staffs;
    }

}
