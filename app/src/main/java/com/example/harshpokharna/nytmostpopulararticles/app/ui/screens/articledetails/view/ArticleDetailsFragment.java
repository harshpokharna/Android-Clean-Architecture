package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshpokharna.nytmostpopulararticles.R;
import com.example.harshpokharna.nytmostpopulararticles.app.application.NYTMostPopularArticles;
import com.example.harshpokharna.nytmostpopulararticles.app.config.ActiveFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.config.ActivityStates;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.common.BaseFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.MainActivity;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.di.ArticleDetailsModule;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.presenter.ArticleDetailsPresenter;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
public class ArticleDetailsFragment extends BaseFragment implements ArticleDetailsView {

    // UI Elements
    @Bind(R.id.tv_fragment_article_details)
    TextView articleTitle;

    @Bind(R.id.tv_abstract_article_details)
    TextView articleAbstract;

    @Bind(R.id.iv_fragment_article_details)
    ImageView articleImage;

    // Static Factory
    public static ArticleDetailsFragment newInstance() {
        return new ArticleDetailsFragment();
    }

    // Presenter
    @Inject
    ArticleDetailsPresenter presenter;

    // Lifecycle Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        NYTMostPopularArticles.getArticleComponent().plus(new ArticleDetailsModule()).injectArticleDetailsPresenterIn(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        NYTMostPopularArticles.setActivityState(ActivityStates.ACTICVITY_ARTICLE_DETAILS);
        NYTMostPopularArticles.setActiveFragment(ActiveFragment.ARTICLE_DETAILS_FRAGMENT);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void displayErrorMessage() {
        articleTitle.setText("Could not load the article. Please try again.");
    }

    @Override
    public void displayArticle(Article article) {
        articleTitle.setText(article.getTitle());
        articleAbstract.setText(article.getArticleAbstract());

        Picasso.with(getActivity())
                .load(article.getImageUrl())
                .placeholder(R.drawable.abc_ratingbar_full_material)
                .fit()
                .centerInside()
                .into(articleImage);
    }
}
