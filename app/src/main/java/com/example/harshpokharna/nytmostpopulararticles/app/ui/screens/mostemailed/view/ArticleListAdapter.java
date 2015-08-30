package com.example.harshpokharna.nytmostpopulararticles.app.ui.screens.mostemailed.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshpokharna.nytmostpopulararticles.R;
import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Harsh.Pokharna on 8/28/2015.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder> {

    private List<Article> articleList;
    private LayoutInflater layoutInflater;
    private Context context;
    private ClickCommunicator clickCommunicator;

    public ArticleListAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ArticleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_article, parent, false);
        ArticleListViewHolder viewHolder = new ArticleListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleListViewHolder holder, int position) {

        Article currentArticle = articleList.get(position);

        holder.articleTitle.setText(currentArticle.getTitle());
        holder.articleAbstract.setText(currentArticle.getArticleAbstract());

        Picasso.with(context)
                .load(currentArticle.getImageUrl())
                .placeholder(R.drawable.abc_ratingbar_full_material)
                .error(R.drawable.abc_btn_check_material)
                .fit()
                .centerInside()
                .into(holder.articleThumbnail);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setClickCommunicator(ClickCommunicator clickCommunicator)
    {
        this.clickCommunicator = clickCommunicator;
    }

    public class ArticleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.iv_list_item_article)
        ImageView articleThumbnail;

        @Bind(R.id.tv_title_list_item_article)
        TextView articleTitle;

        @Bind(R.id.tv_abstract_list_item_article)
        TextView articleAbstract;

        public ArticleListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(clickCommunicator != null)
            {
                clickCommunicator.onArticleListClicked(articleList.get(getAdapterPosition()));
            }
        }
    }
}
