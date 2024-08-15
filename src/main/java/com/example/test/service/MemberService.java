package com.example.test.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

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
}
