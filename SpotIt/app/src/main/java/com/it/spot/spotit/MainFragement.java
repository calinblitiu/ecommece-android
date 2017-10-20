package com.it.spot.spotit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;


/**
 * A simple {@link Fragment} subclass.
 */



public class MainFragement extends Fragment {

    BannerSlider banner;
    List<Banner> banners;
    GridView shoppingGridView;
    ArrayList<ShoppingGridItem> items;
    public MainFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_main, container, false);

        this.banner = (BannerSlider) rootview.findViewById(R.id.banner_slider);
        this.banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.banner_image));
        banners.add(new DrawableBanner(R.drawable.banner_image));
        banners.add(new DrawableBanner(R.drawable.banner_image));
        banner.setBanners(this.banners);

        this.shoppingGridView = (GridView)rootview.findViewById(R.id.shopping_grid_main);
        this.items = new ArrayList<ShoppingGridItem>();
        ShoppingGridItem item1= new ShoppingGridItem();
        ShoppingGridItem item2= new ShoppingGridItem();
        ShoppingGridItem item3= new ShoppingGridItem();
        ShoppingGridItem item4= new ShoppingGridItem();
        this.items.add(item1);
        this.items.add(item2);
        this.items.add(item3);
        this.items.add(item4);
        ShoppingGridAdater shoppingGridAdater = new ShoppingGridAdater(this.getActivity(),this.items);
        this.shoppingGridView.setAdapter(shoppingGridAdater);

        return  rootview;
    }

}
