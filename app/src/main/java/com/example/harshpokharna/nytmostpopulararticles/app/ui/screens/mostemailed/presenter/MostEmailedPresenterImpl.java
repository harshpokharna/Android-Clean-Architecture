package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter;

import android.util.Log;

import com.example.harshpokharna.nytmostpopulararticles.app.application.NYTMostPopularArticles;
import com.example.harshpokharna.nytmostpopulararticles.app.config.Keys;
import com.example.harshpokharna.nytmostpopulararticles.app.di.module.ArticleModule;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.view.MostEmailedView;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;
import com.example.harshpokharna.nytmostpopulararticles.domain.usecase.GetArticlesFromNetworkUseCase;
import com.example.harshpokharna.nytmostpopulararticles.domain.usecase.GetArticlesUseCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public class MostEmailedPresenterImpl implements MostEmailedPresenter {

    private GetArticlesUseCase getArticlesUseCase;
    private GetArticlesFromNetworkUseCase getArticlesFromNetworkUseCase;
    private MostEmailedView view;
    private HashMap<String, Object> articleListCurenntState;

    private ArticleListStateClass currentState;

    private CompositeSubscription compositeSubscription;

    public MostEmailedPresenterImpl(GetArticlesUseCase getArticlesUseCase, GetArticlesFromNetworkUseCase getArticlesFromNetworkUseCase) {
        this.getArticlesUseCase = getArticlesUseCase;
        this.getArticlesFromNetworkUseCase = getArticlesFromNetworkUseCase;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(MostEmailedView view) {
        this.view = view;

        NYTMostPopularArticles.setArticleComponent(null);

        articleListCurenntState = NYTMostPopularArticles.getArticleListCurrentState();

        if (articleListCurenntState != null) {
            if ((int) articleListCurenntState.get(Keys.CURRENT_STATE_KEY) == ArticleListStateClass.STATE_LOADING) {
                if (isViewAttached()) {
                    view.showLoading();
                }
            } else if ((int) articleListCurenntState.get(Keys.CURRENT_STATE_KEY) == ArticleListStateClass.STATE_ERROR) {
                if (isViewAttached()) {
                    view.displayErrorMessage();
                }
            } else if ((int) articleListCurenntState.get(Keys.CURRENT_STATE_KEY) == ArticleListStateClass.STATE_NO_ARTICLES) {
                if (isViewAttached()) {
                    view.displayErrorMessage();
                }
            } else if ((int) articleListCurenntState.get(Keys.CURRENT_STATE_KEY) == ArticleListStateClass.STATE_DISPLAYING_ARTICLES) {
                if (isViewAttached()) {
                    view.displayArticles((List<Article>) articleListCurenntState.get(Keys.ARTICLE_LIST_KEY));
                }
            }
        }

    }

    @Override
    public void detachView() {

        if (compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }

        articleListCurenntState = new HashMap<>();
        articleListCurenntState.put(Keys.CURRENT_STATE_KEY, currentState.getCurrentState());
        articleListCurenntState.put(Keys.ARTICLE_LIST_KEY, currentState.getArticleList());
        NYTMostPopularArticles.setArticleListCurrentState(articleListCurenntState);

        view = null;
    }

    @Override
    public boolean isViewAttached() {
        if (view == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void fetchArticles() {

        view.showLoading();
        currentState = new ArticleListStateClass(ArticleListStateClass.STATE_LOADING, null);

        Subscription fetchArticlesSubscription = getArticlesUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Article>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("APP", "onError ----- " + e.getMessage());
                        view.hideLoading();
                        view.displayErrorMessage();

                        currentState = new ArticleListStateClass(ArticleListStateClass.STATE_ERROR, null);
                    }

                    @Override
                    public void onNext(List<Article> articles) {
                        view.hideLoading();
                        if (articles.size() == 0) {
                            view.displayNoArticlesMessage();

                            currentState = new ArticleListStateClass(ArticleListStateClass.STATE_NO_ARTICLES, new ArrayList<Article>());
                        } else {
                            view.displayArticles(articles);

                            currentState = new ArticleListStateClass(ArticleListStateClass.STATE_DISPLAYING_ARTICLES, articles);
                        }
                    }
                });

        compositeSubscription.add(fetchArticlesSubscription);
    }

    @Override
    public void fetchArticlesFromNetwork() {

        Subscription fetchArticlesFromNetworkSubscription = getArticlesFromNetworkUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Article>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        view.hideLoading();
                        view.displayErrorMessage();

                        currentState = new ArticleListStateClass(ArticleListStateClass.STATE_ERROR, null);
                    }

                    @Override
                    public void onNext(List<Article> articles) {

                        if (articles.size() != 0) {
                            view.hideLoading();
                            view.displayArticles(articles);

                            currentState = new ArticleListStateClass(ArticleListStateClass.STATE_DISPLAYING_ARTICLES, articles);
                        } else {
                            view.hideLoading();
                            view.displayNoArticlesMessage();

                            currentState = new ArticleListStateClass(ArticleListStateClass.STATE_NO_ARTICLES, new ArrayList<Article>());
                        }
                    }
                });

        compositeSubscription.add(fetchArticlesFromNetworkSubscription);
    }

    @Override
    public void onClick(Article article) {
        NYTMostPopularArticles.setArticleComponent(NYTMostPopularArticles.getAppComponent().plus(new ArticleModule(article)));
        view.goToDetailsFragment();
    }
}
