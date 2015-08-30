package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.di;

import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ScreenScope;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.presenter.ArticleDetailsPresenter;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.presenter.ArticleDetailsPresenterImpl;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
@Module
public class ArticleDetailsModule {

    @Provides
    @ScreenScope
    public ArticleDetailsPresenter provideArticlePresenter(Article article)
    {
        return new ArticleDetailsPresenterImpl(article);
    }

}
