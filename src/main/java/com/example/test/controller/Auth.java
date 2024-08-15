package com.example.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.dto.MemberLoginDTO;
import com.example.test.model.Member;
import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class Auth {
  private final MemberService memberService;
  
  @PostMapping("login")
  public ResponseEntity<String> signup(@RequestBody MemberLoginDTO loginMember){
    log.info("받은 데이터: {}", loginMember);
    Member member = new Member(loginMember);
    memberService.saveMember(member);

    return ResponseEntity.ok("ok");
  }
}
