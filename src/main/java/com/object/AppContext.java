package com.object;

import com.object.domain.article.repository.ArticleRepository;
import com.object.domain.article.service.ArticleService;
import com.object.domain.system.controller.SystemController;
import com.object.domain.article.controller.ArticleController;

import java.util.Scanner;

public class AppContext {
    public static Scanner scanner;
    public static SystemController systemController;
    public static ArticleController articleController;
    public static ArticleService articleService;
    public static ArticleRepository articleRepository;

    public static void renew(Scanner _scanner) {
        scanner = _scanner;
        systemController = new SystemController();
        articleRepository = new ArticleRepository();
        articleService = new ArticleService();
        articleController = new ArticleController();
    }

    public static void renew() {
        renew(new Scanner(System.in));
    }
}