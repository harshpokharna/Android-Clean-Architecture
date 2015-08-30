package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.presenter;

import android.util.Log;

import com.example.harshpokharna.nytmostpopulararticles.app.application.NYTMostPopularArticles;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.view.ArticleDetailsView;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;
import com.google.gson.Gson;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
public class ArticleDetailsPresenterImpl implements ArticleDetailsPresenter {

    private ArticleDetailsView articleDetailsView;
    private Article article;

    public ArticleDetailsPresenterImpl(Article article) {
        this.article = article;
    }

    @Override
    public void attachView(ArticleDetailsView view) {
        this.articleDetailsView = view;

        if (article == null) {
            this.articleDetailsView.displayErrorMessage();
        } else {
            this.articleDetailsView.displayArticle(article);
        }
    }

    @Override
    public void detachView() {
        articleDetailsView = null;
    }

    @Override
    public boolean isViewAttached() {
        if (articleDetailsView == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void destroy() {

    }
}
