package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.harshpokharna.nytmostpopulararticles.R;
import com.example.harshpokharna.nytmostpopulararticles.app.application.NYTMostPopularArticles;
import com.example.harshpokharna.nytmostpopulararticles.app.config.ActiveFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.config.ActivityStates;
import com.example.harshpokharna.nytmostpopulararticles.app.config.DisplayMessage;
import com.example.harshpokharna.nytmostpopulararticles.app.di.module.ArticleModule;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.common.BaseFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.MainActivity;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.articledetails.view.ArticleDetailsFragment;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.di.MostEmailedModule;
import com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.presenter.MostEmailedPresenter;
import com.example.harshpokharna.nytmostpopulararticles.app.utils.FragmentUtils;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public class MostEmailedFragment extends BaseFragment implements MostEmailedView, SwipeRefreshLayout.OnRefreshListener, ClickCommunicator {

    // UI Elements
    @Bind(R.id.rv_fragment_mostemailed)
    RecyclerView articleList;

    @Bind(R.id.tv_message_fragment_mostemailed)
    TextView message;

    @Bind(R.id.pb_fragment_mostemailed)
    ProgressBar progressBar;

    @Bind(R.id.srl_fragment_mostemailed)
    SwipeRefreshLayout swipeRefreshLayout;

    // Static Factory
    public static MostEmailedFragment newInstance() {
        return new MostEmailedFragment();
    }

    // Presenter
    @Inject
    MostEmailedPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        NYTMostPopularArticles.getAppComponent().plus(new MostEmailedModule())
                .injectInMostEmailedFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_most_emailed, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(this);
        initRecyclerView();
        showLoading();
    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Your Articles");

        presenter.attachView(this);
        NYTMostPopularArticles.setActivityState(ActivityStates.ACTICVITY_MOST_EMAILED);
        NYTMostPopularArticles.setActiveFragment(ActiveFragment.ARTICLE_LIST_FRAGMENT);
        presenter.fetchArticles();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
        articleList.setVisibility(View.GONE);
        message.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayErrorMessage() {
        articleList.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
        message.setVisibility(View.VISIBLE);
        message.setText(DisplayMessage.MOST_EMAILED_FRAGMENT_ERROR_MESSAGE);
    }

    @Override
    public void displayNoArticlesMessage() {
        articleList.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
        message.setVisibility(View.VISIBLE);
        message.setText(DisplayMessage.MOST_EMAILED_FRAGMENT_NO_ARTICLES_MESSAGE);
    }

    @Override
    public void displayArticles(List<Article> articles) {
        message.setVisibility(View.GONE);
        articleList.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);

        ArticleListAdapter articleListAdapter = new ArticleListAdapter(articles, getActivity());
        articleListAdapter.setClickCommunicator(this);
        articleList.setAdapter(articleListAdapter);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void goToDetailsFragment() {
        FragmentUtils.changeFragment(getActivity().getSupportFragmentManager(), ArticleDetailsFragment.newInstance());
    }

    private void initRecyclerView() {

        ArticleListAdapter articleListAdapter = new ArticleListAdapter(new ArrayList<Article>(), getActivity());
        articleListAdapter.setClickCommunicator(this);
        articleList.setAdapter(articleListAdapter);
        articleList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onRefresh() {
        presenter.fetchArticlesFromNetwork();
    }

    @Override
    public void onArticleListClicked(Article article) {
        presenter.onClick(article);
    }
}
