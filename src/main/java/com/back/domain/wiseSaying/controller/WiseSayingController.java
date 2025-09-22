package com.back.domain.wiseSaying.controller;


import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@ResponseBody
public class WiseSayingController {

    private List<WiseSaying> wiseSayingList = new ArrayList<>(){
        {
            add(new WiseSaying(1,"명언1", "작가1"));
            add(new WiseSaying(2,"명언2", "작가2"));
            add(new WiseSaying(3,"명언3", "작가3"));
            add(new WiseSaying(4,"명언4", "작가4"));
            add(new WiseSaying(5,"명언5", "작가5"));
        }
    };
    private int lastId = 5; //

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


        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayingList.add(wiseSaying);

        return " %d번 명언이 등록되었습니다 ".formatted(wiseSaying.getId());
    }

//[ Read ]
    // 명언 목록 데이터
    @GetMapping("/wiseSayings")
    @ResponseBody
    public String list(){

        String wiseSayings = wiseSayingList.stream()
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
        WiseSaying wiseSaying = findById(id);

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
        WiseSaying wiseSaying = findById(id);

        wiseSayingList.remove(wiseSaying);

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
      WiseSaying wiseSaying = findById(id);
      wiseSaying.setContent(content);
      wiseSaying.setAuthor(author);

        return "%d 번 명언이 수정되었습니다".formatted(wiseSaying.getId());
    }

    private WiseSaying findById(int id){
        Optional<WiseSaying> wiseSaying = wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();

        if(wiseSaying.isEmpty()){
            throw new RuntimeException("%d 번 명언은 존재하지 않습니다.".formatted(id));
        }
        return wiseSaying.get();
    }

}
