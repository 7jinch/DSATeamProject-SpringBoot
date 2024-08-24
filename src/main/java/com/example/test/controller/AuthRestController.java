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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.dto.MemberValidateJwtToken;
import com.example.test.dto.MemberFindPasswordDTO;
import com.example.test.dto.MemberLoginDTO;
import com.example.test.dto.MemberResetPasswordDTO;
import com.example.test.model.Member;
import com.example.test.service.JwtService;
import com.example.test.service.JwtServiceImpl;
import com.example.test.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
  private final JwtServiceImpl jwtService;
  
  // 로그인
  @PostMapping("login")
  public ResponseEntity<?> login(
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
//      JwtService jwtService = new JwtServiceImpl();
      
      // jwt 생성: id는 키, loginMember.getEmail()은 값
      String jwt = jwtService.getToken("id", loginMember.getEmail());
      
      // jwt를 담는 쿠키를 생성: 쿠키의 이름은 token
      Cookie cookie = new Cookie("ourpreciousmember", jwt);
      cookie.setHttpOnly(false); // true로 하면 클라이언트에서 접근을 못 함
      cookie.setPath("/"); // 사이트의 모든 경로에서 유효함
      
      response.addCookie(cookie); // 생성한 쿠키를 응답에 추가
      
      Member member = memberService.findMemberByEmail(loginMember.getEmail());
      Map<String, String> memberMap = new HashMap<>();
      memberMap.put("member_id", member.getMember_id().toString());
      memberMap.put("email", member.getEmail());
      memberMap.put("name", member.getName());
      memberMap.put("birth", member.getBirth().toString());
      memberMap.put("signupDate", member.getSignupDate().toString());
      memberMap.put("phoneNumber", member.getPhone_number());
      memberMap.put("gender", member.getGender());
      memberMap.put("question", member.getQuestion());
      memberMap.put("answer", member.getAnswer());
      memberMap.put("messages", member.getMessages().toString());
      
//      return ResponseEntity.ok().build();
      return new ResponseEntity<>(memberMap, HttpStatus.OK);
    }
    else return ResponseEntity.badRequest().body("입력하신 이메일 또는 비밀번호를 확인해 주세요."); 
  }
  
  // 비밀번호 재설정하기: 이메일, 질문, 답변 검사
  @PostMapping("findpassword")
  public ResponseEntity<?> findPassword(
      @Valid @RequestBody MemberFindPasswordDTO findPasswordData,
      BindingResult bindingResult) {
    log.info("받은 데이터: {}", findPasswordData);
    
    if(bindingResult.hasErrors()) {
      List<String> fieldErrors = bindingResult.getFieldErrors()
          .stream()
          .map(error -> error.getField() + ": " + error.getDefaultMessage())
          .collect(Collectors.toList());
      
      return ResponseEntity.badRequest().body(fieldErrors);
    }
    
    Boolean bool = memberService.findMemberByEmail(findPasswordData);
    if(bool) return ResponseEntity.ok().build();
    else return ResponseEntity.badRequest().body("입력하신 이메일 또는 질문과 답변을 확인해 주세요."); 
  }
  
  // 비밀번호 재설정하기
  @PostMapping("resetpassword")
  public ResponseEntity<?> resetPassword(
      @Valid @RequestBody MemberResetPasswordDTO resetPasswordData,
      BindingResult bindingResult){
    log.info("받은 데이터: {}", resetPasswordData);
    
    if(bindingResult.hasErrors()) {
      List<String> fieldErrors = bindingResult.getFieldErrors()
          .stream()
          .map(error -> error.getField() + ": " + error.getDefaultMessage())
          .collect(Collectors.toList());
      
      return ResponseEntity.badRequest().body(fieldErrors);
    }
    
    // MemberFindPasswordDTO 인스턴스 생성
    MemberFindPasswordDTO wanted = new MemberFindPasswordDTO(resetPasswordData);
    
    // 이메일, 질문, 답변 검사
    Boolean bool = memberService.findMemberByEmail(wanted);
    if(bool) {
      String result = memberService.resetPassword(resetPasswordData);
      if(result.equals("완료")) return ResponseEntity.ok().build();
      else if(result.equals("동일")) return ResponseEntity.badRequest().body("이전 비밀번호와 일치합니다.");
      else return ResponseEntity.badRequest().body("입력하신 이메일 또는 질문과 답변, 비밀번호를 확인해 주세요.");
    } else return ResponseEntity.badRequest().body("입력하신 이메일 또는 질문과 답변, 비밀번호를 확인해 주세요.");
  }

  // 쿠키 검사
  @PostMapping("validate")
  public ResponseEntity<?> validateJwtToken(
      @RequestHeader("Authorization") String jwtToken,
      @Valid @RequestBody MemberValidateJwtToken validate,
      BindingResult bindingResult){
    log.info("받은 데이터: {}", validate);
    
    if(bindingResult.hasErrors()) {
      List<String> fieldErrors = bindingResult.getFieldErrors()
          .stream()
          .map(error -> error.getField() + ": " + error.getDefaultMessage())
          .collect(Collectors.toList());
      
      return ResponseEntity.badRequest().body(fieldErrors);
    }
    
    String token = jwtToken.replace("Bearer", "");
    
    /////////////////////////////////////////
//    이메일도 검사
    /////////////////////////////////////////
    if(jwtService.validateToken(token)) {
      return ResponseEntity.ok("유효한 토큰");
    }
    
    return ResponseEntity.badRequest().body("유효하지 않은 토큰");
  }
}
