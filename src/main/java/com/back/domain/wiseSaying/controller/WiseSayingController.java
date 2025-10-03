package com.back.domain.wiseSaying.controller;


import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class WiseSayingController {


    private final WiseSayingService wiseSayingService;


    // [ Create ]
    @GetMapping("/wiseSaying/write")
    public String write(
            String content,
            String author
    ){
        //null 이거나 공백(띄어쓰기, 띄어쓰기는 null아님)
        if(content == null || content.trim().length() == 0){
            throw new RuntimeException("작가를 입력해주세요.");
        }
        if(author == null || author.trim().length() == 0){
            throw new RuntimeException("작가를 입력해주세요.");
        }

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        return " %d번 명언이 등록되었습니다 ".formatted(wiseSaying.getId());
    }

//[ Read ]
    // 명언 목록 데이터
    @GetMapping("/wiseSayings")
    @ResponseBody
    public String list(){

        String wiseSayings = wiseSayingService.findAll().stream()
                .map(w -> "<li>%s / %s / %s</li>".formatted(w.getId(), w.getContent(), w.getAuthor()))
                .collect(Collectors.joining("\n"));

        return """
                <ul>
                    %s
                </ul>
                
                """.formatted(wiseSayings);

    }

    // 상세 보기
    @GetMapping("/wiseSayings/{id}")
    @ResponseBody
    public String detail(
            @PathVariable
            int id
    ){
        WiseSaying wiseSaying = wiseSayingService.findById(id).get();

        return """
                <h1>번호 : %s</h1>
                <div>명언 : %s</div>
                <div>작가 : %s</div>
                """.formatted(wiseSaying.getId(), wiseSaying.getContent(), wiseSaying.getAuthor());

    }

    // JSON 형식으로 반환 및 JSON형식으로 브라우저 표시
//    @GetMapping("/wiseSaying/list")
//    @ResponseBody
//    public List<WiseSaying> list(){
//
//        return wiseSayingList;
//
//    }

//[ Delete ]
    @GetMapping("/wiseSayings/delete/{id}")
    @ResponseBody
    public String delete(
            @PathVariable int id
    ){
        WiseSaying wiseSaying = wiseSayingService.findById(id).get();

        //wiseSayingList.remove(wiseSaying);
        wiseSayingService.delete(wiseSaying);

        return "%d 번 명언이 삭제되었습니다.".formatted(id);
    }


//[ Update ]
    @GetMapping("/wiseSayings/modify/{id}")
    @ResponseBody
    public String modify(
            @PathVariable int id,
            @RequestParam(defaultValue = "기본값") String content,
            @RequestParam(defaultValue = "기본값") String author
    ){
      WiseSaying wiseSaying = wiseSayingService.findById(id).get();
      wiseSayingService.modify(wiseSaying, content, author);

        return "%d 번 명언이 수정되었습니다".formatted(wiseSaying.getId());
    }



}
