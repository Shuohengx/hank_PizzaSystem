package com.hank.PizzSystem.hank_PizzaSystem.model;

import javax.validation.constraints.NotNull;

public class StaffDto {
  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  private String password;

  @NotNull
  private String matchingPassword;

  @NotNull
  private String email;


  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  private Integer role;

  public Integer getRole() {
    return role;
  }

  public void setRole(final Integer role) {
    this.role = role;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(final String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("StaffDto [firstName=").append(firstName).append(", lastName=").append(lastName)
        .append(", password=").append(password).append(", matchingPassword=").append(matchingPassword)
        .append(", email=").append(email).append(", role=").append(role).append("]");
    return builder.toString();
  }
}
