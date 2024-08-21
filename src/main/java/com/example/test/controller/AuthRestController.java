package com.example.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.dto.MemberLoginDTO;
import com.example.test.model.Member;
import com.example.test.service.JwtService;
import com.example.test.service.JwtServiceImpl;
import com.example.test.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthRestController {
  private final MemberService memberService;
  
  @PostMapping("login")
  public ResponseEntity<?> signup(
      @Valid @RequestBody MemberLoginDTO loginMember,
      BindingResult bindingResult,
      HttpServletResponse response
      ){
    log.info("받은 데이터: {}", loginMember);
    
    if(bindingResult.hasErrors()) {
      List<String> fieldErrors = bindingResult.getFieldErrors()
          .stream()
          .map(error -> error.getField() + ": " + error.getDefaultMessage())
          .collect(Collectors.toList());
      
      return ResponseEntity.badRequest().body(fieldErrors);
    }
    
    Boolean bool = memberService.findMemberByEmail(loginMember);
    if(bool) {
      // JwtServiceImpl 인터페이스를 생성하고 JwtService를 구현하기
      JwtService jwtService = new JwtServiceImpl();
      
      // jwt 생성: id는 키, loginMember.getEmail()은 값
      String jwt = jwtService.getToken("id", loginMember.getEmail());
      
      // jwt를 담는 쿠키를 생성: 쿠키의 이름은 token
      Cookie cookie = new Cookie("ourpreciousmember", jwt);
      cookie.setHttpOnly(true);
      cookie.setPath("/"); // 사이트의 모든 경로에서 유효함
      
      response.addCookie(cookie); // 생성한 쿠키를 응답에 추가
      
      Member member = memberService.findMemberByEmail(loginMember.getEmail());
      Map<String, String> memberMap = new HashMap<>();
      memberMap.put("name", member.getName());
      memberMap.put("email", member.getEmail());
      
//      return ResponseEntity.ok().build();
      return new ResponseEntity<>(memberMap, HttpStatus.OK);
    }
    else return ResponseEntity.badRequest().body("입력하신 이메일 또는 비밀번호를 확인해 주세요."); 
  }
}
