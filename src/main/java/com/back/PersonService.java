package com.back;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public final PersonRepository personRepository;

    //스프링은 생성자가 하나뿐이면 autowired 없이도 자동으로 주입해준다
    //대신 매개변수 이름하고 동일한 객체가 빈에 등록되어있어야함

    //final 변수에는 Autowired 애너테이션 사용하지 말자

    public PersonService (@Qualifier("personRepository") PersonRepository personRepositoryV3){
        this.personRepository = personRepositoryV3;
    }

    public int count(){
        return personRepository.count();
    }
}
