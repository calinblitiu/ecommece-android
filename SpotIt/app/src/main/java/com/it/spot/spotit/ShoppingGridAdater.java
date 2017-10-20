package com.it.spot.spotit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by rubby on 10/20/2017.
 */

public class ShoppingGridAdater extends BaseAdapter {

    Context context;
    ArrayList<ShoppingGridItem> items;

    public ShoppingGridAdater(Context context,ArrayList<ShoppingGridItem> items)
    {
        this.context = context;
        this.items  = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        gridView = inflater.inflate(R.layout.shopping_grid_item,parent, false);


        return gridView;
    }
}
