package com.it.spot.spotit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rubby on 10/22/2017.
 */

public class MyCartListItemAdater extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<MyCartListItem> mDataSource;

    public MyCartListItemAdater(Context context, ArrayList<MyCartListItem> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.mDataSource.size();
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
        View rowView = mInflater.inflate(R.layout.mycart_list_item,parent,false);

        return rowView;

    }
}
