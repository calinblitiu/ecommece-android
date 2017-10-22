package com.it.spot.spotit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {

    ListView myCartListView;
    ArrayList<MyCartListItem> myCartList;
    MyCartListItemAdater myCartListItemAdater;

    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_my_cart, container, false);

        this.myCartListView = (ListView)rootview.findViewById(R.id.my_cart_listview);

        this.myCartList = new ArrayList<MyCartListItem>();
        MyCartListItem item1 = new MyCartListItem();
        MyCartListItem item2 = new MyCartListItem();
        MyCartListItem item3 = new MyCartListItem();
        MyCartListItem item4 = new MyCartListItem();

        this.myCartList.add(item1);
        this.myCartList.add(item2);
        this.myCartList.add(item3);
        this.myCartList.add(item4);

        this.myCartListItemAdater =  new MyCartListItemAdater(this.getContext(),myCartList);
        this.myCartListView.setAdapter(this.myCartListItemAdater);

        return rootview;
    }

}
