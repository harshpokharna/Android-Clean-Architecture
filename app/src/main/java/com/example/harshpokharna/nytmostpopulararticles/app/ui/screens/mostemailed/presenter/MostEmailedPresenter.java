package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter;

import com.example.harshpokharna.nytmostpopulararticles.app.pattern.mvp.Presenter;
import com.example.harshpokharna.nytmostpopulararticles.app.pattern.mvp.View;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.view.MostEmailedView;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public interface MostEmailedPresenter extends Presenter<MostEmailedView> {

    public void fetchArticles();

    public void fetchArticlesFromNetwork();

    public void onClick(Article article);
}
