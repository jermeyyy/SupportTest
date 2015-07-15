package com.example.karol.supporttest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;

import java.util.ArrayList;

/**
 * Created by karol on 2015-07-15.
 */
public class StickyHeaderGridAdapter extends RecyclerView.Adapter<GridItemViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0x01;

    private static final int VIEW_TYPE_CONTENT = 0x00;

    private static final int LINEAR = 0;

    private final ArrayList<LineItem> mItems;

    private int mHeaderDisplay;

    private boolean mMarginsFixed;

    private final Context mContext;

    private int mColumns = 2;

    public StickyHeaderGridAdapter(Context context, int headerMode) {
        mContext = context;

        final String[] countryNames = new String[]{"TestTestTestTestTest", "Test", "Test",
                "Test", "Test", "TestTestTestTestTest", "TestTestTestTestTestTestTestTestTestTestTestTest", "Test", "Test",
                "Test", "TestTestTestTestTestTestTestTestTestTestTestTest", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "TestTestTestTestTestTestTestTestTestTestTest", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test", "Test",
                "Test", "Test", "Test", "Test", "Test"};
        mHeaderDisplay = headerMode;

        mItems = new ArrayList<>();

        //Insert headers into list of items.
        String lastHeader = "";
        int sectionManager = -1;
        int headerCount = 0;
        int sectionFirstPosition = 0;
        mItems.add(new LineItem("<--HEADER-->", true, sectionManager, sectionFirstPosition));

        for (int i = 0; i < countryNames.length; i++) {
//            String header = countryNames[i].substring(0, 1);
//            if (!TextUtils.equals(lastHeader, header)) {
//                // Insert new header view and update section data.
//                sectionManager = (sectionManager + 1) % 2;
//                sectionFirstPosition = i + headerCount;
//                lastHeader = header;
//                headerCount += 1;
//                mItems.add(new LineItem(header, true, sectionManager, sectionFirstPosition));
//            }
            mItems.add(new LineItem(countryNames[i], false, sectionManager, sectionFirstPosition));
        }
    }

    public boolean isItemHeader(int position) {
        return mItems.get(position).isHeader;
    }

    public String itemToString(int position) {
        return mItems.get(position).text;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_item, parent, false);
        } else {
            if(mColumns == 2) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.grid_item, parent, false);
            } else {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item2, parent, false);
            }
        }
        return new GridItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        final LineItem item = mItems.get(position);
        final View itemView = holder.itemView;

        holder.bindItem(item.text);

        final GridSLM.LayoutParams lp = GridSLM.LayoutParams.from(itemView.getLayoutParams());
        // Overrides xml attrs, could use different layouts too.
        if (item.isHeader) {
            lp.headerDisplay = mHeaderDisplay;
            if (lp.isHeaderInline() || (mMarginsFixed && !lp.isHeaderOverlay())) {
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }

            lp.headerEndMarginIsAuto = !mMarginsFixed;
            lp.headerStartMarginIsAuto = !mMarginsFixed;
            View ico1 = holder.mView.findViewById(R.id.ico1);
            View ico2 = holder.mView.findViewById(R.id.ico2);
            ico1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mColumns = 1;
                    lp.setColumnWidth(mContext.getResources().getDisplayMetrics().widthPixels);
                    itemView.setLayoutParams(lp);
                    notifyDataSetChanged();
                }
            });
            ico2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mColumns = 2;
                    lp.setColumnWidth(mContext.getResources().getDisplayMetrics().widthPixels/2);
                    itemView.setLayoutParams(lp);
                    notifyDataSetChanged();
                }
            });
        } else {
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CollapsingViewActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        lp.setSlm(item.sectionManager == LINEAR ? LinearSLM.ID : GridSLM.ID);
//        lp.setColumnWidth(mContext.getResources().getDisplayMetrics().widthPixels / 2);
        lp.setFirstPosition(item.sectionFirstPosition);
        itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).isHeader ? VIEW_TYPE_HEADER : VIEW_TYPE_CONTENT;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setHeaderDisplay(int headerDisplay) {
        mHeaderDisplay = headerDisplay;
        notifyHeaderChanges();
    }

    public void setMarginsFixed(boolean marginsFixed) {
        mMarginsFixed = marginsFixed;
        notifyHeaderChanges();
    }

    private void notifyHeaderChanges() {
        for (int i = 0; i < mItems.size(); i++) {
            LineItem item = mItems.get(i);
            if (item.isHeader) {
                notifyItemChanged(i);
            }
        }
    }

    private static class LineItem {

        public int sectionManager;

        public int sectionFirstPosition;

        public boolean isHeader;

        public String text;

        public LineItem(String text, boolean isHeader, int sectionManager,
                        int sectionFirstPosition) {
            this.isHeader = isHeader;
            this.text = text;
            this.sectionManager = sectionManager;
            this.sectionFirstPosition = sectionFirstPosition;
        }
    }
}
