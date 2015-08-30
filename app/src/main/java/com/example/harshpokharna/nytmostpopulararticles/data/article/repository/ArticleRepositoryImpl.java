package com.example.harshpokharna.nytmostpopulararticles.data.article.repository;

import com.example.harshpokharna.nytmostpopulararticles.data.article.source.api.ArticleApi;
import com.example.harshpokharna.nytmostpopulararticles.data.article.source.api.response.GetArticlesResponse;
import com.example.harshpokharna.nytmostpopulararticles.data.article.source.cache.ArticleCache;
import com.example.harshpokharna.nytmostpopulararticles.domain.articlerepository.ArticleRepository;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
public class ArticleRepositoryImpl implements ArticleRepository {

    private ArticleApi articleApi;
    private ArticleCache articleCache;

    public ArticleRepositoryImpl(ArticleApi articleApi, ArticleCache articleCache) {
        this.articleApi = articleApi;
        this.articleCache = articleCache;
    }

    @Override
    public Observable<List<Article>> getArticles() {
        return articleCache.getArticlesFromCache()
                .flatMap(new Func1<List<Article>, Observable<List<Article>>>() {
                    @Override
                    public Observable<List<Article>> call(List<Article> articles) {

                        if(articles.size() == 0)
                        {
                            return articleApi.getArticles("mostemailed", "all-sections", 7)
                                    .map(new Func1<GetArticlesResponse, List<Article>>() {
                                        @Override
                                        public List<Article> call(GetArticlesResponse getArticlesResponse) {
                                            return getArticlesResponse.getArticles();
                                        }
                                    })
                                    .doOnNext(new Action1<List<Article>>() {
                                        @Override
                                        public void call(List<Article> articles) {
                                            articleCache.putArticlesInCache(articles);
                                        }
                                    }).subscribeOn(Schedulers.newThread());

                        }else
                        {
                            return Observable.just(articles);
                        }
                    }
                });
    }

    @Override
    public Observable<List<Article>> getArticlesFromNetwork() {
        return articleApi.getArticles("mostemailed", "all-sections", 7)
                .map(new Func1<GetArticlesResponse, List<Article>>() {
                    @Override
                    public List<Article> call(GetArticlesResponse getArticlesResponse) {
                        return getArticlesResponse.getArticles();
                    }
                }).doOnNext(new Action1<List<Article>>() {
                    @Override
                    public void call(List<Article> articles) {
                        articleCache.putArticlesInCache(articles);
                    }
                }).subscribeOn(Schedulers.newThread());
    }

}
