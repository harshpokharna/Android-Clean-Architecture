package com.example.harshpokharna.nytmostpopulararticles.data.article.source.api;

import com.example.harshpokharna.nytmostpopulararticles.data.article.source.api.response.GetArticlesResponse;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
public interface ArticleApi {

    @GET("/{category}/{section}/{days}.json")
    Observable<GetArticlesResponse> getArticles(@Path("category") String category, @Path("section") String section, @Path("days") int days);
}
