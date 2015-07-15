package com.example.karol.supporttest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by karol on 2015-07-15.
 */
public class GridItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    GridItemViewHolder(View view) {
        super(view);

        mTextView = (TextView) view.findViewById(android.R.id.text1);
    }

    public void bindItem(String text) {
        if (mTextView != null)
            mTextView.setText(text);
    }

    @Override
    public String toString() {
        return mTextView.getText().toString();
    }
}
