package com.it.spot.spotit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WishListFragment extends Fragment {

    ListView wishListListView;
    ArrayList<WishListItem> wishListList;
    WishListItemAdapter wishListItemAdater;
    ShoppingListActivity superActivity;

    public WishListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_wish_list, container, false);
        View rootview = inflater.inflate(R.layout.fragment_wish_list, container, false);
        this.superActivity = (ShoppingListActivity)this.getActivity();
        this.superActivity.hideSearchInput();
        this.superActivity.setProfileNavKindText("Wish List");

        this.wishListListView = (ListView)rootview.findViewById(R.id.wish_list_listview);

        this.wishListList = new ArrayList<WishListItem>();
        WishListItem item1 = new WishListItem();


        this.wishListList.add(item1);
        this.wishListList.add(item1);
        this.wishListList.add(item1);

        this.wishListItemAdater = new WishListItemAdapter(this.getContext(),wishListList);
        this.wishListListView.setAdapter(this.wishListItemAdater);

        return rootview;
    }

}
