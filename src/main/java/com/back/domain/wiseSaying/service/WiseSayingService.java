package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository;


    public WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(content, author);
        wiseSayingRepository.save(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }



    public void delete(WiseSaying wiseSaying) {
        wiseSayingRepository.remove(wiseSaying);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.update(content, author);
    }

    public Optional<WiseSaying> findById(int id){
        Optional<WiseSaying> wiseSaying = wiseSayingRepository.findById(id);
        if(wiseSaying.isEmpty()){
            throw new RuntimeException("%d 번 명언은 존재하지 않습니다.".formatted(id));
        }
        return wiseSaying;
    }

    public long count() {
        return wiseSayingRepository.count();
    }
}
