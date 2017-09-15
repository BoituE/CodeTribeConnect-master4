package com.example.android.codetribeconnect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<String> mItemNames, mItemSuurname, mItemStatus;

    public MyItemRecyclerViewAdapter(List<String> itemName, List<String> itemSurname, List<String> itemStatus) {
        mItemNames = itemName;
        mItemSuurname = itemSurname;
        mItemStatus = itemStatus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_other_user_profiles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mItemNames.get(position);
        holder.mIdView.setText(mItemNames.get(position));
        holder.mContentView.setText(mItemSuurname.get(position));
        holder.mStatus.setText(mItemStatus.get(position));

    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStatus;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.activeUserName);
            mContentView = (TextView) view.findViewById(R.id.activeUserSurname);
            mStatus = (TextView) view.findViewById(R.id.activeUserStatus);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
