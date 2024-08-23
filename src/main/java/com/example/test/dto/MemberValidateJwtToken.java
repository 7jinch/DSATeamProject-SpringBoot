package com.example.test.dto;

public class MemberValidateJwtToken {
  private String email;
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  @Override
  public String toString() {
    return "MemberCheckJwtToken [email=" + email + "]";
  }
}
