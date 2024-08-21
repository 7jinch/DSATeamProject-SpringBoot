package com.example.test.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.test.dto.MemberLoginDTO;
import com.example.test.model.Member;
import com.example.test.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  
  public Boolean checkDuplicateUsername(Member member) {
    Optional<Member> findmember = memberRepository.findByEmail(member.getEmail());

    if(!findmember.isPresent()) return true;
    else return false;
  }

  @Transactional
  public void saveMember(Member member){
    memberRepository.save(member);
  }
  
  public Boolean findMemberByEmail(MemberLoginDTO memberLoginDTO) {
    String email = memberLoginDTO.getEmail();
    Optional<Member> findmember = memberRepository.findByEmail(email);
    
    // 계정이 존재하고 비밀번호도 일치하면
    if(findmember.isPresent() && findmember.get().getPassword().equals(memberLoginDTO.getPassword())) {
      return true;
    }
    else return false;
  }
  
  public Member findMemberByEmail(String email) {
    Optional<Member> findmember = memberRepository.findByEmail(email);
    return findmember.get();
  }
}
