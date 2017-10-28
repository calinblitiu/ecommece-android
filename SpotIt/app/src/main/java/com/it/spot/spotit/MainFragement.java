package com.it.spot.spotit;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;


/**
 * A simple {@link Fragment} subclass.
 */



public class MainFragement extends Fragment {

    BannerSlider banner;
    List<Banner> banners;
    GridView shoppingGridView;
    ShoppingListActivity superActivity;
    ArrayList<ShoppingGridItem> items;
    public MainFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_main, container, false);

        this.superActivity = (ShoppingListActivity)this.getActivity();
        this.superActivity.showSearchInput();

        this.banner = (BannerSlider) rootview.findViewById(R.id.banner_slider);
        this.banners = new ArrayList<>();

        loadSlideShow();
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

    public void loadSlideShow()
    {
        NetWorkTrans netWorkTrans = new NetWorkTrans(new AsyncResponse() {
            @Override
            public void processFinishWithSuccess(String method, String output) throws JSONException {
                JSONObject outputObj = new JSONObject(output);
                if(outputObj.getInt("success") == 1)
                {
                    Global.slideShows = outputObj.getJSONArray("data");
                    JSONObject tempslideshows0 = Global.slideShows.getJSONObject(0);
                    JSONArray tempslideshows = tempslideshows0.getJSONArray("banners");

                    for (int i = 0;i< tempslideshows.length();i++)
                    {
                        JSONObject temp = tempslideshows.getJSONObject(i);
                        banners.add(new RemoteBanner(temp.getString("image")).setScaleType(ImageView.ScaleType.FIT_XY));

                    }

                    banner.setBanners(banners);


                }
                else if(outputObj.getInt("success") == 0)
                {

                }

            }

            @Override
            public void processFinishWithError(String method) {

            }
        });

        HashMap<String, Object> params;
        params = new HashMap<>();
        params.put("method","get");
        params.put("url", Global.api_prefix_oauth+Global.slideshow_url);
        params.put("is_get_access_token","no");
        netWorkTrans.execute(params);
    }

}
