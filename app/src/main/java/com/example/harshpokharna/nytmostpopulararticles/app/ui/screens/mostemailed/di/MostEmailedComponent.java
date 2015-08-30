package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.di;

import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ScreenScope;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.di.MostEmailedModule;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter.MostEmailedPresenter;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.view.MostEmailedFragment;

import dagger.Subcomponent;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
@ScreenScope
@Subcomponent(modules = MostEmailedModule.class)
public interface MostEmailedComponent {

    MostEmailedPresenter mostEmailedPresenter();

    public void injectInMostEmailedFragment(MostEmailedFragment mostEmailedFragment);
}
