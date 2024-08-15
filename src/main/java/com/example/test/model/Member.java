package com.example.test.model;

import java.time.LocalDate;
import com.example.test.dto.MemberLoginDTO;
import com.example.test.dto.MemberSignupDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long member_id;
  
  @Column(length=100, nullable=false)
  private String email;
  
  @Column(length=12, nullable=false)
  private String password;
  
  @Column(length=12, nullable=false)
  private String name;

  @Column(nullable=false)
  private LocalDate birth;
  
  @Column(length=13, nullable=false)
  private String phone_number;
  
  @Column(nullable=false)
  private String gender;

  public Member(MemberSignupDTO signupMember) {
    this.email = signupMember.getEmail();
    this.password = signupMember.getPassword();
    this.name = signupMember.getName();
    this.birth = signupMember.getBirth();
    this.phone_number = signupMember.getPhone_number();
    this.gender = signupMember.getGender();
  }
  
  public Member(MemberLoginDTO loginMember) {
    this.email = loginMember.getEmail();
    this.password = loginMember.getPassword();
  }
}
