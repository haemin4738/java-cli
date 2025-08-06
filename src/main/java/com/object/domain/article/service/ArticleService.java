package com.object.domain.article.service;

import com.object.AppContext;
import com.object.domain.article.entity.ArticleEntity;
import com.object.domain.article.repository.ArticleRepository;

import java.time.LocalDate;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService () {
        articleRepository = AppContext.articleRepository;
    }

    public ArticleEntity write (String content, String author, LocalDate regDate) {
        ArticleEntity articleEntity = new ArticleEntity(author, content, regDate);
        articleRepository.save(articleEntity);

        return articleEntity;
    }

    public List<ArticleEntity> findForList () {
        return articleRepository.findForList();
    }

    public ArticleEntity findById(int id) {
        return articleRepository.findById(id);
    }

}
