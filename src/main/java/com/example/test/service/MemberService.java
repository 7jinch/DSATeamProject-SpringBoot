package com.example.test.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.test.dto.MemberFindPasswordDTO;
import com.example.test.dto.MemberLoginDTO;
import com.example.test.dto.MemberResetPasswordDTO;
import com.example.test.model.Member;
import com.example.test.repository.MemberRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  
  // 중복 이메일 검사
  public Boolean checkDuplicateUsername(Member member) {
    Optional<Member> findmember = memberRepository.findByEmail(member.getEmail());

    if(!findmember.isPresent()) return true;
    else return false;
  }

  // 회원 저장
  @Transactional
  public void saveMember(Member member){
    memberRepository.save(member);
  }
  
  // 로그인할 때 회원 검사
  public Boolean findMemberByEmail(MemberLoginDTO memberLoginDTO) {
    String email = memberLoginDTO.getEmail();
    Optional<Member> findmember = memberRepository.findByEmail(email);
    
    // 계정이 존재하고 비밀번호도 일치하면
    if(findmember.isPresent() && findmember.get().getPassword().equals(memberLoginDTO.getPassword())) {
      return true;
    }
    else return false;
  }
  
  // 이메일로 회원 찾기
  public Member findMemberByEmail(String email) {
    Optional<Member> findmember = memberRepository.findByEmail(email);
    return findmember.get();
  }
  
  // 비밀번호 재설정할 때 질문이랑 답변이 일치하는지 검사
  public Boolean findMemberByEmail(MemberFindPasswordDTO findPasswordDTO) {
    String email = findPasswordDTO.getEmail();
    Optional<Member> findmember = memberRepository.findByEmail(email);
    
    // 계정이 존재하고 질문과 답변이 일치하면
    if(findmember.isPresent() && findmember.get().getQuestion().equals(findPasswordDTO.getQuestion()) && findmember.get().getAnswer().equals(findPasswordDTO.getAnswer())) {
      return true;
    }
    else return false;
  }
  
  // 비밀번호 재설정
  @Transactional
  public String resetPassword(MemberResetPasswordDTO resetPasswordData) {
    Optional<Member> findmember = memberRepository.findByEmail(resetPasswordData.getEmail());
//    findmember.get().setPassword(resetPasswordData.getPassword());
//    memberRepository.save(findmember);
    
    if (findmember.isPresent()) {
        Member member = findmember.get();
        // 이전 비밀번호와 일치
        if(member.getPassword().equals(resetPasswordData.getPassword())) return "동일";
        else {
          member.setPassword(resetPasswordData.getPassword());
          memberRepository.save(member);
          return "완료";
        }
    } else return "실패";
  }

  public Member findMemberById(@Valid Long member_id) {
    Optional<Member> findMember = memberRepository.findById(member_id);
    
    if(findMember.isPresent()) return findMember.get();
    else return null;
  }
}
