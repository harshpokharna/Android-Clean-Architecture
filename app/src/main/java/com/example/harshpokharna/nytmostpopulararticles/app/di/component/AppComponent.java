package com.example.harshpokharna.nytmostpopulararticles.app.di.component;

import com.example.harshpokharna.nytmostpopulararticles.app.di.module.AppModule;
import com.example.harshpokharna.nytmostpopulararticles.app.di.module.ArticleModule;
import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ApplicationScope;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.di.ArticleDetailsComponent;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.di.ArticleDetailsModule;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.di.MostEmailedComponent;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.di.MostEmailedModule;
import com.example.harshpokharna.nytmostpopulararticles.app.di.module.DataModule;
import com.example.harshpokharna.nytmostpopulararticles.app.di.module.NetworkModule;

import dagger.Component;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
@ApplicationScope
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {

    MostEmailedComponent plus(MostEmailedModule module);

    ArticleComponent plus(ArticleModule module);
}
