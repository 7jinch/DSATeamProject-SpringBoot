package com.example.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberResetPasswordDTO {
  @NotBlank(message = "이메일은 필수로 입력해 주세요.")
  @Email
  private String email;
  
  @NotBlank(message = "질문은 필수로 선택해 주세요.")
  private String question;
  
  @NotBlank(message = "답변은 필수로 입력해 주세요.")
  @Size(message="답변은 30자 이내로 작성해 주세요.")
  private String answer;
  
  @NotBlank(message = "비밀번호는 필수로 입력해 주세요.")
  @Size(min = 6, max = 12, message = "비밀번호는 6자에서 12자 사이로 입력해 주세요.")
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
  
  public String getQuestion() {
      return question;
  }
  
  public void setQuestion(String question) {
      this.question = question;
  }
  
  public String getAnswer() {
    return answer;
  }
  
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  @Override
  public String toString() {
    return "MemberResetPasswordDTO [email=" + email + ", question=" + question + ", answer=" + answer + ", password=" + password + "]";
    
  }
}
