package com.example.harshpokharna.nytmostpopulararticles.app.pattern.mvp;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public interface Presenter<V extends View> {

    void attachView(V view);

    void detachView();

    boolean isViewAttached();

    void destroy();
}
