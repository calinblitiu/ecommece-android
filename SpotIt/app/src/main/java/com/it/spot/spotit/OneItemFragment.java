package com.it.spot.spotit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class OneItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    JSONObject data;
    ImageView one_item_image_top;
    TextView one_item_description;
    public OneItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_one_item, container, false);
        this.one_item_image_top = (ImageView)rootView.findViewById(R.id.one_item_image_top);
        this.one_item_description = (TextView)rootView.findViewById(R.id.one_item_description);
        try {
            Picasso.with(getActivity()).load(data.getString("image")).into(this.one_item_image_top);

            this.one_item_description.setText(Html.fromHtml(data.getString("description")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }


}
