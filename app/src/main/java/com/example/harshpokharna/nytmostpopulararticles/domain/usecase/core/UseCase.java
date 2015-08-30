package com.example.harshpokharna.nytmostpopulararticles.domain.usecase.core;

import rx.Observable;

/**
 * Created by Harsh.Pokharna on 8/27/2015.
 */
public interface UseCase<T> {

    public Observable<T> execute();
}
