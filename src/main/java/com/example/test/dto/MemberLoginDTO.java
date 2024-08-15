package com.example.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberLoginDTO {
  @NotBlank(message = "이메일은 필수로 입력해 주세요.")
  @Email
  private String email;
  
  @NotBlank(message = "비밀번호는 필수로 입력해 주세요.")
  private String password;
  
  public String getEmail() {
    return email;
  }
  
  public void setEamil(String email) {
      this.email = email;
  }
  
  public String getPassword() {
      return password;
  }
  
  public void setPassword(String password) {
      this.password = password;
  }
}
