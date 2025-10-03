package com.back.domain.wiseSaying.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public void update(String content, String author){
        this.content = content;
        this.author = author;
    }

    public WiseSaying(String content, String author){
        this.content = content;
        this.author = author;
    }

    public boolean isNew(){
        return this.id == 0;
    }

}
