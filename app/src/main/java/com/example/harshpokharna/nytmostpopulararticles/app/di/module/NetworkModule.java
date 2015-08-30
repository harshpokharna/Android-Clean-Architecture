package com.example.harshpokharna.nytmostpopulararticles.app.di.module;

import com.example.harshpokharna.nytmostpopulararticles.app.config.AppConstants;
import com.example.harshpokharna.nytmostpopulararticles.app.di.scope.ApplicationScope;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient()
    {
        return new OkHttpClient();
    }

    @Provides
    @ApplicationScope
    public RestAdapter provideRestAdapter(OkHttpClient okHttpClient, Gson gson)
    {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setEndpoint(AppConstants.END_POINT)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addQueryParam("api-key", AppConstants.NYT_API_KEY);
                    }
                })
                .build();
        return restAdapter;
    }
}
