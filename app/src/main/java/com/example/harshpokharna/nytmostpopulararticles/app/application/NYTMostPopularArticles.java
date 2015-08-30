package com.example.harshpokharna.nytmostpopulararticles.app.application;

import android.app.Application;
import android.util.Log;

import com.example.harshpokharna.nytmostpopulararticles.app.di.component.AppComponent;
import com.example.harshpokharna.nytmostpopulararticles.app.di.component.ArticleComponent;
import com.example.harshpokharna.nytmostpopulararticles.app.di.component.DaggerAppComponent;
import com.example.harshpokharna.nytmostpopulararticles.app.di.module.AppModule;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter.ArticleListStateClass;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public class NYTMostPopularArticles extends Application {

    private static AppComponent appComponent;
    private static ArticleComponent articleComponent;

    private static HashMap<String, Object> articleListCurrentState;
    private static int activityState;
    private static int activeFragment;

    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            throw new IllegalStateException("app component is null");
        }
        return appComponent;
    }

    public static HashMap<String, Object> getArticleListCurrentState() {
        return articleListCurrentState;
    }

    public static void setArticleListCurrentState(HashMap<String, Object> articleListCurrentState) {
        NYTMostPopularArticles.articleListCurrentState = articleListCurrentState;
    }

    public static ArticleComponent getArticleComponent() {
        return articleComponent;
    }

    public static void setArticleComponent(ArticleComponent articleComponent) {
        NYTMostPopularArticles.articleComponent = articleComponent;
    }

    public static int getActivityState() {
        return activityState;
    }

    public static void setActivityState(int activityState) {
        NYTMostPopularArticles.activityState = activityState;
    }

    public static int getActiveFragment() {
        return activeFragment;
    }

    public static void setActiveFragment(int activeFragment) {
        NYTMostPopularArticles.activeFragment = activeFragment;
    }
}
