package com.object.domain.article.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class ArticleEntity {
    private int id;
    private String title;
    private String content;
    private LocalDate regDate;

    public ArticleEntity (String author, String content, LocalDate regDate) {
        this.title = author;
        this.content = content;
        this.regDate = regDate;
    }

    public boolean isNew() {
        return id == 0;
    }
}