package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.di;

import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ScreenScope;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.presenter.ArticleDetailsPresenter;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.view.ArticleDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
@ScreenScope
@Subcomponent(modules = ArticleDetailsModule.class)
public interface ArticleDetailsComponent {

    ArticleDetailsPresenter articleDetailsPresenter();

    public void injectArticleDetailsPresenterIn(ArticleDetailsFragment articleDetailsFragment);
}
