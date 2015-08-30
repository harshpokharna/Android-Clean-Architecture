package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.view;

import com.example.harshpokharna.nytmostpopulararticles.app.pattern.mvp.View;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
public interface ArticleDetailsView extends View {

    public void displayErrorMessage();

    public void displayArticle(Article article);
}
