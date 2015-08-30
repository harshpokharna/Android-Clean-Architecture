package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.view;

import com.example.harshpokharna.nytmostpopulararticles.app.pattern.mvp.View;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import java.util.List;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public interface MostEmailedView extends View {

    public void showLoading();

    public void hideLoading();

    public void displayErrorMessage();

    public void displayNoArticlesMessage();

    public void displayArticles(List<Article> articles);

    public void goToDetailsFragment();
}
