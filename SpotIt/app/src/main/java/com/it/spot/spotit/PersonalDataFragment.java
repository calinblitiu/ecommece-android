package com.it.spot.spotit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class PersonalDataFragment extends Fragment {

    ShoppingListActivity superActivity;

    public PersonalDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_personal_data, container, false);
        this.superActivity = (ShoppingListActivity)this.getActivity();
        this.superActivity.hideSearchInput();
        this.superActivity.setProfileNavKindText("Personal data");
        return rootView;
    }


}
