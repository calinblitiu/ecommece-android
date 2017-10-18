package com.it.spot.spotit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rubby on 10/18/2017.
 */

public class NavigationMenuItemAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<NavigationMenuItem> mDataSource;

    public NavigationMenuItemAdapter(Context context, ArrayList<NavigationMenuItem> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
//        return 0;
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
//        return null;
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
        View rowView = mInflater.inflate(R.layout.navigation_menu_item,parent,false);

        TextView navigation_item_txt = (TextView)rowView.findViewById(R.id.navigation_item_name_textview);
        NavigationMenuItem navigationItem = (NavigationMenuItem)getItem(position);
        navigation_item_txt.setText(navigationItem.item_name);

        return rowView;
    }

}
