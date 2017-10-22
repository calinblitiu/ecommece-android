package com.it.spot.spotit;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by rubby on 10/21/2017.
 */

public class ProfileActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        MyProfileFragment main_fragement = new MyProfileFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.profilecontent, main_fragement).commit();

        LinearLayout list_item_shipping = (LinearLayout)findViewById(R.id.list_item_shipping);
        LinearLayout list_item_personal_data = (LinearLayout)findViewById(R.id.list_item_personal_data);
        LinearLayout list_item_the_cart = (LinearLayout)findViewById(R.id.list_item_the_cart);

        list_item_shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewShippingAddresses();
            }
        });

        list_item_personal_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPersonalData();
            }
        });

        list_item_the_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMyCart();
            }
        });



    }

    public void viewShippingAddresses()
    {
        ShippingAddressesFragment main_fragement = new ShippingAddressesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.profilecontent, main_fragement).commit();
    }

    public void viewPersonalData()
    {
        PersonalDataFragment main_fragement = new PersonalDataFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.profilecontent, main_fragement).commit();
    }

    public void viewMyCart()
    {
        MyCartFragment main_fragement = new MyCartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.profilecontent, main_fragement).commit();
    }
}
