package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.di;

import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ScreenScope;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter.MostEmailedPresenter;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter.MostEmailedPresenterImpl;
import com.example.harshpokharna.nytmostpopulararticles.domain.usecase.GetArticlesFromNetworkUseCase;
import com.example.harshpokharna.nytmostpopulararticles.domain.usecase.GetArticlesUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
@Module
public class MostEmailedModule {

    @Provides
    @ScreenScope
    public MostEmailedPresenter provideMostEmailedPresenter(GetArticlesUseCase getArticlesUseCase, GetArticlesFromNetworkUseCase getArticlesFromNetworkUseCase)
    {
        return new MostEmailedPresenterImpl(getArticlesUseCase, getArticlesFromNetworkUseCase);
    }
}
