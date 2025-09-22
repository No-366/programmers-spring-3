package com.back.global.initData;

import com.back.domain.member.entity.Member;
import com.back.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    @Autowired
    @Lazy
    private BaseInitData self;

    private final MemberService memberService;

    @Bean
    ApplicationRunner initDatRunner(){
        return args -> {
            self.work1(); // 프록시 객체를 통해서 가리킴
            self.work2(); // 자기 자신 직접 가리킴
        };
    }

    @Transactional
    void work1() {

        if(memberService.count() > 0){
            return;
        }

        Member member1 = memberService.join("systemUser", "시스템");
        Member member2 = memberService.join("adminUser", "관리자");
        Member member3 = memberService.join("user1", "시스템");
        Member member4 = memberService.join("user2", "시스템");
        Member member5 = memberService.join("user3", "시스템");

    }

    @Transactional // 트랜잭션을 사용하려면 반드시 프록시 객체 (self)를 이용해야함
    void work2() {
        Member member4 = memberService.findByUsername("user2").get();

        member4.setNickname("new user2");

        System.out.println(member4.getNickname());
    }


}
