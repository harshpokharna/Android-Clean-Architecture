package com.example.harshpokharna.nytmostpopulararticles.app.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.harshpokharna.nytmostpopulararticles.R;

/**
 * Created by Harsh.Pokharna on 8/26/2015.
 */
public class FragmentUtils {

    public static void changeFragment(FragmentManager fragmentManager, Fragment fragment) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_activity_main, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }
}
