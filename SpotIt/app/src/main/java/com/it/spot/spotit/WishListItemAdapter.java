package com.it.spot.spotit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by rubby on 10/22/2017.
 */

public class WishListItemAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<WishListItem> mDataSource;

    public WishListItemAdapter(Context context, ArrayList<WishListItem> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.mDataSource.size()+1;
    }

    @Override
    public Object getItem(int position) {

        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position == mDataSource.size())
        {
            View rowView = mInflater.inflate(R.layout.wish_list_add_all_in_cart_item,parent,false);
            return rowView;
        }

        View rowView = mInflater.inflate(R.layout.wish_list_item,parent,false);
        return rowView;
    }
}
