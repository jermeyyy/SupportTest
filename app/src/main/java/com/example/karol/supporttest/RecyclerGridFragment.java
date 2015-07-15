package com.example.karol.supporttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tonicartos.superslim.LayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karol on 2015-06-09.
 */
public class RecyclerGridFragment extends Fragment {

    private ArrayList<String> mList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] values = new String[]{"TestTestTestTestTest", "Test", "Test",
                "Test", "Test", "TestTestTestTestTest", "TestTestTestTestTestTestTestTestTestTestTestTest", "Test", "Test",
                "Test", "TestTestTestTestTestTestTestTestTestTestTestTest", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "TestTestTestTestTestTestTestTestTestTestTest", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test"};

        mList = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            mList.add(values[i]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.recycler_view_fragment, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LayoutManager(getActivity()));
//        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(), mList));
        recyclerView.setAdapter(new StickyHeaderGridAdapter(getActivity(), LayoutManager.LayoutParams.HEADER_INLINE | LayoutManager.LayoutParams.HEADER_STICKY));
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

            public final View mView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public String getValueAt(int position) {
            return mValues.get(position);
        }

        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.grid_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mBoundString = mValues.get(position);
            holder.mTextView.setText(mValues.get(position));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CollapsingViewActivity.class);

                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
