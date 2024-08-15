package com.example.test.controller;

import java.util.stream.Collectors;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.dto.MemberSignupDTO;
import com.example.test.model.Member;
import com.example.test.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/members")
@RequiredArgsConstructor
//@CrossOrigin(origins="http://localhost:5173") // 허용할 도메인
public class MemberRestController {
  private final MemberService memberService;
  
  @PostMapping
  public ResponseEntity<?> signup(
      @Valid @RequestBody MemberSignupDTO signupMember, // 보낸 데이터 담기
      BindingResult bindingResult
      ){
    log.info("받은 데이터: {}", signupMember);
    
    if(bindingResult.hasErrors()) {
      List<String> fieldErrors = bindingResult.getFieldErrors()
          .stream()
          .map(error -> error.getField() + ": " + error.getDefaultMessage())
          .collect(Collectors.toList());
      
      return ResponseEntity.badRequest().body(fieldErrors);
    }
    
    Member member = new Member(signupMember);
    if(memberService.checkDuplicateUsername(member)) memberService.saveMember(member);
    else return ResponseEntity.badRequest().body("동일한 이메일로 가입한 회원이 있어요.");
    
    return ResponseEntity.ok("ok");
  }
}
