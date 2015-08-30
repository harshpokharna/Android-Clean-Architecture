package com.example.harshpokharna.nytmostpopulararticles.domain.usecase;

import com.example.harshpokharna.nytmostpopulararticles.domain.articlerepository.ArticleRepository;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;
import com.example.harshpokharna.nytmostpopulararticles.domain.usecase.core.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
public class GetArticlesFromNetworkUseCase implements UseCase<List<Article>> {

    private ArticleRepository articleRepository;

    @Inject
    public GetArticlesFromNetworkUseCase(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Observable<List<Article>> execute() {
        return articleRepository.getArticlesFromNetwork();
    }
}
