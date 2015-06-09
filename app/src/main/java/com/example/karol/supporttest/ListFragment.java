package com.example.karol.supporttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by karol on 2015-06-09.
 */
public class ListFragment extends Fragment {

    @InjectView(R.id.listview)
    ListView listView;

    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] values = new String[]{"Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test"};

        mList = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            mList.add(values[i]);
        }
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.inject(this, v);

        listView.setAdapter(mAdapter);

        return v;
    }
}
