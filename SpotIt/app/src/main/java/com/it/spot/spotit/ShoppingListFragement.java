package com.it.spot.spotit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.it.spot.spotit.R;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragement extends Fragment {


    GridView shoppingGridView;
    ArrayList<ShoppingGridItem> items;
    ShoppingListActivity superActivity;

    public ShoppingListFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_shopping_list, container, false);

        this.superActivity = (ShoppingListActivity)this.getActivity();
        this.superActivity.showSearchInput();

        this.shoppingGridView = (GridView)rootview.findViewById(R.id.search_grid_main);
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

//        this.shoppingGridView.setOnItemClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                OneItemFragment shopping_fragement = new OneItemFragment();
////                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
////                fragmentManager.beginTransaction().replace(R.id.flContent, shopping_fragement).commit();
//            }
//        });
        this.shoppingGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OneItemFragment shopping_fragement = new OneItemFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, shopping_fragement).commit();
            }
        });

        return rootview;
    }



}
