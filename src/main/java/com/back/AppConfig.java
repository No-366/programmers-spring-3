package com.back;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { // Bean 을 만드는 옛날 버전 스프링 방식

//    @Bean
//    public PersonRepository personRepository(){
//
//        System.out.println("AppConfig.personService 호출됨!");
//        return new PersonRepository(1);
//    }
//
//    @Bean
//    public PersonRepository personRepositoryV2(){
//
//        System.out.println("AppConfig.personService 버전 2가 호출됨!");
//        return new PersonRepository(2); // 이름 없는 객체가 빈에는 메서드 이름으로 등록되는건지?
//    }

//    @Bean
//    public ApplicationRunner myApplicationRunner(){
//
//
////        return new ApplicationRunner() {
////
////            @Override
////            public void run(ApplicationArguments args) throws Exception {
////                System.out.println("myApplicationRunner 실행");
////            }
////        };
//        return (args) -> {
//            System.out.println("myApplicationRunner 실행");
//            work1();
//            work2();
//        };
//    }
//
//    private void work1(){
//        System.out.println("work1");
//    }
//    private void work2(){
//        System.out.println("work2");
//    }


}