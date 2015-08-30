package com.example.harshpokharna.nytmostpopulararticles.data.article.source.api.response;

import com.example.harshpokharna.nytmostpopulararticles.domain.model.Article;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
public class GetArticlesResponse {

    @SerializedName("copyright")
    private String copyright;

    @SerializedName("results")
    private List<Result> results;

    public String getCopyright() {
        return copyright;
    }

    public List<Article> getArticles() {

        List<Article> articleList = new ArrayList<>();

        for (Result result : results) {
            Article article = new Article();
            article.setTitle(result.title);
            article.setArticleAbstract(result.articleAbstract);
            article.setImageUrl(result.media.get(0).mediaMetadata.get(0).url);
            article.setPublishedDate(result.publishedDate);

            articleList.add(article);
        }

        return articleList;
    }

    static class Result {

        @SerializedName("title")
        String title;

        @SerializedName("abstract")
        String articleAbstract;

        @SerializedName("published_date")
        String publishedDate;

        @SerializedName("media")
        List<Media> media;

        static class Media {

            @SerializedName("media-metadata")
            List<MediaMetadata> mediaMetadata;

            static class MediaMetadata {

                @SerializedName("url")
                String url;
            }
        }
    }
}
