package com.example.test.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MemberSignupDTO {
  @NotBlank(message = "이메일은 필수로 입력해 주세요.")
  @Pattern(regexp = "^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$", message = "이메일의 형식에 맞춰서 입력해 주세요.")
  private String email;
  
  @NotBlank(message = "비밀번호는 필수로 입력해 주세요.")
  @Size(min = 6, max = 12, message = "비밀번호는 6자에서 12자 사이로 입력해 주세요.")
  private String password;
  
  @NotBlank(message = "이름은 필수로 입력해 주세요.")
  @Size(min=6, max=12, message = "이름은 6자에서 12자 사이로 입력해 주세요.")
  @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "이름은 한글, 영문, 숫자만 포함할 수 있어요.")
  private String name;
  
  @NotNull(message = "생년월일은 필수로 입력해 주세요.")
  @Past(message = "오늘 이전의 날짜만 선택할 수 있어요.")
  private LocalDate birth;
  
  @NotBlank(message = "전화번호는 필수로 입력해 주세요.")
  @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호의 형식에 맞춰서 입력해 주세요. (예시. 000-0000-0000)")
  private String phone_number;
  
  @NotBlank(message = "성별은 필수로 입력해 주세요.")
  private String gender;

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public LocalDate getBirth() {
      return birth;
  }

  public void setBirth(LocalDate birth) {
      this.birth = birth;
  }

  public String getPhone_number() {
      return phone_number;
  }

  public void setPhone_number(String phone_number) {
      this.phone_number = phone_number;
  }

  public String getGender() {
      return gender;
  }

  public void setGender(String gender) {
      this.gender = gender;
  }

  @Override
  public String toString() {
      return "MemberRequestDto{" +
              "eamil='" + email + '\'' +
              ", password='" + password + '\'' +
              ", name='" + name + '\'' +
              ", birth='" + birth + '\'' +
              ", phone_number='" + phone_number + '\'' +
              ", gender='" + gender + '\'' +
              '}';
  }
}
