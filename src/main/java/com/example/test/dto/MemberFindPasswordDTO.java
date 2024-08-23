package com.example.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberFindPasswordDTO {
  @NotBlank(message = "이메일은 필수로 입력해 주세요.")
  @Email
  private String email;
  
  @NotBlank(message = "질문은 필수로 선택해 주세요.")
  private String question;
  
  @NotBlank(message = "답변은 필수로 입력해 주세요.")
  @Size(message="답변은 30자 이내로 작성해 주세요.")
  private String answer;
  
  public String getEmail() {
    return email;
  }
  
  public void setEamil(String email) {
      this.email = email;
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
    return "MemberFindPasswordDTO [email=" + email + ", question=" + question + ", answer=" + answer + "]";
  }
  
  public MemberFindPasswordDTO(MemberResetPasswordDTO resetData) {
    this.email = resetData.getEmail();
    this.question = resetData.getQuestion();
    this.answer = resetData.getAnswer();
  }
}
