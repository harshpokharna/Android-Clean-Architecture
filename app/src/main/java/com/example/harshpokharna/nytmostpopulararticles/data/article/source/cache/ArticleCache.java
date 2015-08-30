package com.example.harshpokharna.nytmostpopulararticles.data.article.source.cache;

import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import java.util.List;

import rx.Observable;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
public interface ArticleCache {

    public Observable<List<Article>> getArticlesFromCache();

    public void putArticlesInCache(List<Article> articleList);
}
