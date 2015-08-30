package com.example.harshpokharna.nytmostpopulararticles.app.di.module;

import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ArticleScope;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
@Module
public class ArticleModule {

    private Article article;

    public ArticleModule(Article article) {
        this.article = article;
    }

    @Provides
    @ArticleScope
    public Article provideArticle()
    {
        return article;
    }
}
