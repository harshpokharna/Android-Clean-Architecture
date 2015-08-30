package com.example.harshpokharna.nytmostpopulararticles.app.di.module;

import android.app.FragmentManager;
import android.content.Context;

import com.example.harshpokharna.nytmostpopulararticles.app.application.NYTMostPopularArticles;
import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ApplicationScope;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */

@Module
public class AppModule {

    private NYTMostPopularArticles application;

    public AppModule(NYTMostPopularArticles application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @ApplicationScope
    public Gson provideGson()
    {
        return new Gson();
    }
}
