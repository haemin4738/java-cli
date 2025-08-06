package com.object.domain.article.repository;

import com.object.domain.article.entity.ArticleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    private final List<ArticleEntity> articleList = new ArrayList<>();
    private int lastId = 0;

    public ArticleEntity save (ArticleEntity articleEntity) {
        if (articleEntity.isNew()) {
            articleEntity.setId(++lastId);
            articleList.add(articleEntity);
        }

        return articleEntity;
    }

    public List<ArticleEntity> findForList() {
        return articleList.reversed();
    }

    public int findIndexById(int id) {
        return IntStream
                .range(0, articleList.size())
                .filter(i -> articleList.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public ArticleEntity findById(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return articleList.get(index);
    }
}
