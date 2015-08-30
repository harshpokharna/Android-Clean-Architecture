package com.example.harshpokharna.nytmostpopulararticles.data.article.source.cache.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.harshpokharna.nytmostpopulararticles.data.article.source.cache.ArticleCache;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
public class SharedPrefsCacheImpl implements ArticleCache {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static final String FILE_NAME = "my_shared_prefs";
    private static final String ARTICLE_KEY = "articles";
    private static final String TAG = "article_cache";


    public SharedPrefsCacheImpl(Context context, Gson gson) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    @Override
    public Observable<List<Article>> getArticlesFromCache() {

        return Observable.create(new Observable.OnSubscribe<List<Article>>() {
            @Override
            public void call(Subscriber<? super List<Article>> subscriber) {
                List<Article> articles;
                try {
                    String articleList = sharedPreferences.getString(ARTICLE_KEY, null);
                    Type type = new TypeToken<List<Article>>() {

                    }.getType();

                    articles = gson.fromJson(articleList, type);

                    if (articles == null) {
                        articles = new ArrayList<>();
                    }

                } catch (Exception e) {
                    articles = new ArrayList<Article>();
                }
                subscriber.onNext(articles);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public void putArticlesInCache(final List<Article> articleList) {

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

                synchronized (TAG) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(ARTICLE_KEY, gson.toJson(articleList));
                    editor.commit();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
