package com.example.karol.supporttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by karol on 2015-06-09.
 */
public class CollapsingViewFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.collapsing_view_fragment, container, false);
        ButterKnife.inject(this, v);


        return v;
    }

}
