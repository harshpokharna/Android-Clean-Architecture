package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.harshpokharna.nytmostpopulararticles.R;
import com.example.harshpokharna.nytmostpopulararticles.app.application.NYTMostPopularArticles;
import com.example.harshpokharna.nytmostpopulararticles.app.config.ActiveFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.config.ActivityStates;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.common.BaseActivity;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.view.ArticleDetailsFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.view.MostEmailedFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.utils.FragmentUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public class MainActivity extends BaseActivity {

    // UI Elements
    @Bind(R.id.tb_activity_main)
    Toolbar toolbar;

    @Bind(R.id.fl_activity_main)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (NYTMostPopularArticles.getActivityState() == ActivityStates.ACTICVITY_MOST_EMAILED) {
            FragmentUtils.changeFragment(getSupportFragmentManager(), MostEmailedFragment.newInstance());
        } else if (NYTMostPopularArticles.getActivityState() == ActivityStates.ACTICVITY_ARTICLE_DETAILS) {
            FragmentUtils.changeFragment(getSupportFragmentManager(), ArticleDetailsFragment.newInstance());
        } else {
            FragmentUtils.changeFragment(getSupportFragmentManager(), MostEmailedFragment.newInstance());
        }
    }

    @Override
    public void onBackPressed() {

        int activeFragment = NYTMostPopularArticles.getActiveFragment();

        if(activeFragment == ActiveFragment.ARTICLE_LIST_FRAGMENT){
            finish();
        }else if(activeFragment == ActiveFragment.ARTICLE_DETAILS_FRAGMENT)
        {
            FragmentUtils.changeFragment(getSupportFragmentManager(), MostEmailedFragment.newInstance());
        }
    }
}
