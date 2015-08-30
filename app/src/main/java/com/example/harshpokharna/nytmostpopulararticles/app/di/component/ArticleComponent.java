package com.example.harshpokharna.nytmostpopulararticles.app.di.component;

import com.example.harshpokharna.nytmostpopulararticles.app.di.module.ArticleModule;
import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ArticleScope;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.di.ArticleDetailsComponent;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.di.ArticleDetailsModule;

import dagger.Subcomponent;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
@ArticleScope
@Subcomponent(modules = ArticleModule.class)
public interface ArticleComponent {

    ArticleDetailsComponent plus(ArticleDetailsModule module);
}
