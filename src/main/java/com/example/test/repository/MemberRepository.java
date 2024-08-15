package com.example.test.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.test.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);

}
