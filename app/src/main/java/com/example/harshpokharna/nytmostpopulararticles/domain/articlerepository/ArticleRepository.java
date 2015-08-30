package com.example.harshpokharna.nytmostpopulararticles.domain.articlerepository;

import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import java.util.List;

import rx.Observable;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
public interface ArticleRepository {

    public Observable<List<Article>> getArticles();

    public Observable<List<Article>> getArticlesFromNetwork();
}
