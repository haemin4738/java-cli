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

    public void actionList(Rq rq) {
        String keywordType = rq.getParam("keywordType", null);
        String keyword = rq.getParam("keyword", null);

        System.out.println("번호 / 제목 / 등록일");
        System.out.println("----------------------");

        for (ArticleEntity articleEntity : articleService.findForList()) {
            // 키워드가 모두 있을 때만 출력
            if (keywordType != null && keyword != null) {
                if (keywordType.equals("content") && articleEntity.getContent().contains(keyword)) {
                    System.out.printf("%d / %s / %s\n", articleEntity.getId(), articleEntity.getTitle(), articleEntity.getRegDate());
                } else if (keywordType.equals("author") && articleEntity.getTitle().contains(keyword)) {
                    System.out.printf("%d / %s / %s\n", articleEntity.getId(), articleEntity.getTitle(), articleEntity.getRegDate());
                }
            } else if (keywordType == null && keyword == null) {
                System.out.printf("%d / %s / %s\n", articleEntity.getId(), articleEntity.getTitle(), articleEntity.getRegDate());
            }
        }
    }

    public void actionView(Rq rq) {
        int id = rq.getParamsAsInt("id", -1);
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

    public void actionSort(Rq rq) {
        String sortType = rq.getParam("sortType", "id");
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

}
