package com.back.domain.member.repository;

import com.back.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 딱히 Bean  등록 안해도 자동 스캔해주긴 하는데 가독성 위해서 작성
public interface MemberRepository extends JpaRepository<Member, Long> {
    //반드시 Member 엔티티 안에 username 필드가 존재해야한다.
    //매개변수로 엔티티 하나를 조회할 수 있음
    Optional<Member> findByUsername(String username);
}
