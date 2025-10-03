package com.back.domain.wiseSaying.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WiseSaying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
