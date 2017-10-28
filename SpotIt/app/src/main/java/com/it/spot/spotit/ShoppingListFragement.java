package com.it.spot.spotit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.it.spot.spotit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragement extends Fragment {


    GridView shoppingGridView;
    ArrayList<ShoppingGridItem> items;
    ShoppingListActivity superActivity;
    TextView category_name_banner;
    NavigationMenuItem navigationMenuItem;

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
        this.category_name_banner = (TextView)rootview.findViewById(R.id.category_name_banner);

        int itemPosition = getArguments().getInt("position");
        this.navigationMenuItem = Global.navigationListArray.get(itemPosition);
        this.category_name_banner.setText(this.navigationMenuItem.item_name);

        this.items = new ArrayList<ShoppingGridItem>();
        try {
            loadProducts();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.shoppingGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OneItemFragment oneItemFragment = new OneItemFragment();
                oneItemFragment.data = items.get(position).data;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, oneItemFragment).commit();
            }
        });

        return rootview;
    }


    public void loadProducts() throws JSONException {
        NetWorkTrans netWorkTrans = new NetWorkTrans(new AsyncResponse() {
            @Override
            public void processFinishWithSuccess(String method, String output) throws JSONException {
                JSONObject outputObj = new JSONObject(output);
                if(outputObj.getInt("success") == 1)
                {
                    JSONArray tempproducts = outputObj.getJSONArray("data");
                    for (int i=0;i<tempproducts.length();i++)
                    {
                        JSONObject tempproduct = tempproducts.getJSONObject(i);
                        ShoppingGridItem item= new ShoppingGridItem();
                        item.data = tempproduct;
                        items.add(item);
                    }
                    ShoppingGridAdater shoppingGridAdater = new ShoppingGridAdater(getActivity(),items);
                    shoppingGridView.setAdapter(shoppingGridAdater);
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
        params.put("url", Global.api_prefix_oauth+Global.getproducts_url+this.navigationMenuItem.category_data.getString("category_id"));
        params.put("is_get_access_token","no");
        netWorkTrans.execute(params);
    }




}
