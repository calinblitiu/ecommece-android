package com.it.spot.spotit;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rubby on 10/20/2017.
 */

public class ShoppingGridAdater extends BaseAdapter {

    Context context;
    ArrayList<ShoppingGridItem> items;
    TextView product_name;
    ImageView product_image;

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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        ShoppingGridItem temp = items.get(position);
        gridView = inflater.inflate(R.layout.shopping_grid_item,parent, false);
        this.product_name = (TextView)gridView.findViewById(R.id.product_name);
        this.product_image = (ImageView)gridView.findViewById(R.id.product_image);
        try {
            if(temp.data !=null) {
                product_name.setText(temp.data.getString("name"));
                Picasso.with(this.context).load(temp.data.getString("image")).fit().into(this.product_image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gridView;
    }
}
