package com.object.domain.article.controller;

import com.object.AppContext;
import com.object.domain.article.entity.ArticleEntity;
import com.object.domain.article.service.ArticleService;
import com.object.global.Rq;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
    private final Scanner scanner;
    private final ArticleService articleService;

    public ArticleController() {
        this.scanner = AppContext.scanner;
        this.articleService = AppContext.articleService;
    }

    public void actionWrite() {
        System.out.print("제목 : ");
        String content = scanner.nextLine();

        System.out.print("내용 : ");
        String author = scanner.nextLine();
        LocalDate regDate = LocalDate.now();
        ArticleEntity articleEntity = articleService.write(content, author, regDate);

        System.out.println("=> 게시글이 등록되었습니다.");
    }


    public void actionSort(Rq rq) {
        String sortType = rq.getParam(0, "id"); // ← index 0에서 파라미터 가져옴
        List<ArticleEntity> articles = new ArrayList<>(articleService.findForList());

        switch (sortType) {
            case "title":
                articles.sort(Comparator.comparing(ArticleEntity::getTitle));
                break;
            case "regDate":
                articles.sort(Comparator.comparing(ArticleEntity::getRegDate));
                break;
            case "id":
            default:
                articles.sort(Comparator.comparingInt(ArticleEntity::getId));
                break;
        }

        System.out.println("번호 / 제목 / 등록일");
        System.out.println("----------------------");
        for (ArticleEntity article : articles) {
            System.out.printf("%d / %s / %s\n",
                    article.getId(),
                    article.getTitle(),
                    article.getRegDate() != null ? article.getRegDate() : "날짜 없음");
        }
    }


    public void actionView(Rq rq) {
        int id = rq.getParamAsInt(1, -1); // ← 인덱스 기반으로 수정
        if (id == -1) {
            System.out.println("잘못된 게시글 번호입니다.");
            return;
        }

        ArticleEntity articleEntity = articleService.findById(id);
        if (articleEntity == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.println("번호: " + articleEntity.getId());
        System.out.println("제목: " + articleEntity.getTitle());
        System.out.println("내용: " + articleEntity.getContent());
        System.out.println("등록일: " + articleEntity.getRegDate());
    }

}
