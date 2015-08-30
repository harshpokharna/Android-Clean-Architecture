package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter;

import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import java.util.List;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
public class ArticleListStateClass {

    public static final int STATE_LOADING = 0;
    public static final int STATE_ERROR = 1;
    public static final int STATE_NO_ARTICLES = 2;
    public static final int STATE_DISPLAYING_ARTICLES = 3;

    private int currentState;
    private List<Article> articleList;

    public ArticleListStateClass(int currentState, List<Article> articleList) {
        this.currentState = currentState;
        this.articleList = articleList;
    }

    public int getCurrentState() {
        return currentState;
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
