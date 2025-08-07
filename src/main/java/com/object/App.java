package com.object;

import com.object.domain.article.controller.ArticleController;
import com.object.domain.system.controller.SystemController;
import com.object.global.Rq;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App() {
        this.scanner = AppContext.scanner;
    }

    public void run () {
        SystemController systemController = AppContext.systemController;
        ArticleController articleController = new ArticleController();

        while (true) {
            System.out.print("명령어 :  ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "write" -> articleController.actionWrite();
                case "detail" -> articleController.actionView(rq);
                case "sort" -> articleController.actionSort(rq);
                case "exit" -> {
                    systemController.actionExit();
                    return;
                }
            }
        }
    }
}