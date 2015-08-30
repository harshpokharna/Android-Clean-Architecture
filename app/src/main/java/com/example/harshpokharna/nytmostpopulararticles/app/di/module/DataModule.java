package com.example.harshpokharna.nytmostpopulararticles.app.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ApplicationScope;
import com.example.harshpokharna.nytmostpopulararticles.data.article.repository.ArticleRepositoryImpl;
import com.example.harshpokharna.nytmostpopulararticles.data.article.source.api.ArticleApi;
import com.example.harshpokharna.nytmostpopulararticles.data.article.source.cache.ArticleCache;
import com.example.harshpokharna.nytmostpopulararticles.data.article.source.cache.impl.SharedPrefsCacheImpl;
import com.example.harshpokharna.nytmostpopulararticles.domain.articlerepository.ArticleRepository;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
@Module
public class DataModule {

    @Provides
    @ApplicationScope
    public ArticleApi provideArticleApi(RestAdapter restAdapter) {
        return restAdapter.create(ArticleApi.class);
    }

    @Provides
    @ApplicationScope
    public ArticleCache provideArticleCache(Context context, Gson gson) {
        return new SharedPrefsCacheImpl(context, gson);
    }

    @Provides
    @ApplicationScope
    public ArticleRepository provideArticleRepository(ArticleApi articleApi, ArticleCache articleCache) {
        return new ArticleRepositoryImpl(articleApi, articleCache);
    }
}
