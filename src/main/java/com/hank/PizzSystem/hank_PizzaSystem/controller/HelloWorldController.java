package com.hank.PizzSystem.hank_PizzaSystem.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
  @RequestMapping("/hello")
  @ApiOperation(value="hello world", notes="")
  public String hello(String name) {
    return "Hello " + name;
  }

  @RequestMapping("/admin")
  @ApiOperation(value="hello admin", notes="")
  public String helloAdmin(String name) {
    return "Hello Admin " + name;
  }

  @RequestMapping("/pizza")
  @ApiOperation(value="Order pizza", notes="")
  public String orderPizza(String name) {
    return "order pizza :" + name ;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @RequestMapping("/preauth")
  public String preAuthAdmin() {
    return "admin";
  }
}
