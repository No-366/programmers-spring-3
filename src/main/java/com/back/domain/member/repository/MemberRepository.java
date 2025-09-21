package com.back.domain.member.repository;

import com.back.domain.member.entity.Member;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public interface MemberRepository extends JpaRepository<Member, Long> {

}
