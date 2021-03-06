package com.it.spot.spotit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ShippingAddressesFragment extends Fragment {

    ShoppingListActivity superActivity;
    public ShippingAddressesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_shipping_addresses, container, false);

        this.superActivity = (ShoppingListActivity)this.getActivity();
        this.superActivity.hideSearchInput();
        this.superActivity.setProfileNavKindText("Shipping Addresses");
        return rootview;
    }

}
