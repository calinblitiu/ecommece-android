package com.it.spot.spotit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by rubby on 10/16/2017.
 */

public class ShoppingListActivity extends AppCompatActivity
{
    ListView navigationListView;

    ImageView menuIconImageView;
    DrawerLayout navigationMenuView;
    EditText shopping_search_edittext;
    TextView profile_nav_kind_text;
    TextView checkout_badg_count;
    ImageView checkout_buskect;
    ArrayList<NavigationHistoryItem> navigationHistory;
    ImageView navigation_back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shoppinglist);

        MainFragement main_fragement = new MainFragement();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, main_fragement).commit();

        Global.navigationListArray = new ArrayList<NavigationMenuItem>();
        NavigationMenuItem profile_item = new NavigationMenuItem("My Profile");
        Global.navigationListArray.add(profile_item);

        this.loadCategories();



        ImageView menu_icon_image = (ImageView)findViewById(R.id.menu_icon_image);
        menu_icon_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.END);
            }
        });

        ImageView close_nav_imageview = (ImageView)findViewById(R.id.close_nav_imageview);
        close_nav_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        this.profile_nav_kind_text = (TextView)findViewById(R.id.profile_nav_kind_text);
        this.shopping_search_edittext = (EditText)findViewById(R.id.shopping_search_edittext);
        this.checkout_badg_count = (TextView)findViewById(R.id.checkout_badg_count);
        this.checkout_buskect = (ImageView) findViewById(R.id.checkout_buskect);

        this.navigationHistory = new ArrayList<NavigationHistoryItem>();
        NavigationHistoryItem item1= new NavigationHistoryItem("main");
        this.navigationHistory.add(item1);

        navigation_back_btn = (ImageView)findViewById(R.id.navigation_back_btn);
        navigation_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backBtnFunction();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    public void setProfileNavKindText(String kind)
    {
        this.profile_nav_kind_text.setText(kind);
    }

    public void showSearchInput()
    {
        shopping_search_edittext.setVisibility(View.VISIBLE);
        profile_nav_kind_text.setVisibility(View.GONE);
        checkout_badg_count.setVisibility(View.GONE);
        checkout_buskect.setVisibility(View.GONE);
    }

    public void hideSearchInput()
    {
        shopping_search_edittext.setVisibility(View.GONE);
        profile_nav_kind_text.setVisibility(View.VISIBLE);
        checkout_badg_count.setVisibility(View.VISIBLE);
        checkout_buskect.setVisibility(View.VISIBLE);
    }

    public  void backBtnFunction()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(this.navigationHistory.size() == 1)
        {
            this.finish();
        }
        else{
            NavigationHistoryItem temp = this.navigationHistory.get(this.navigationHistory.size()-2);
            this.navigationHistory.remove(this.navigationHistory.size()-1);


            switch (temp.navigation_kind)
            {
                case "main":
                    MainFragement main_fragement = new MainFragement();
                    fragmentManager.beginTransaction().replace(R.id.flContent, main_fragement).commit();
                    break;
                case "search":
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", temp.itemPosition);
                    ShoppingListFragement shopping_fragement = new ShoppingListFragement();
                    shopping_fragement.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.flContent, shopping_fragement).commit();
                    break;
                case "myprofile":
                    MyProfileFragment mypfrofile_fragement = new MyProfileFragment();
                    fragmentManager.beginTransaction().replace(R.id.flContent, mypfrofile_fragement).commit();
                    break;
                case "shippingaddresses":
                    ShippingAddressesFragment shippingaddresses_fragement = new ShippingAddressesFragment();
                    fragmentManager.beginTransaction().replace(R.id.flContent, shippingaddresses_fragement).commit();
                    break;
                case "personaldata":
                    PersonalDataFragment personaldata_fragement = new PersonalDataFragment();
                    fragmentManager.beginTransaction().replace(R.id.flContent, personaldata_fragement).commit();
                    break;
                case "mycart":
                    MyCartFragment mycart_fragement = new MyCartFragment();
                    fragmentManager.beginTransaction().replace(R.id.flContent, mycart_fragement).commit();
                    break;
                case "wishlist":
                    WishListFragment wishlist_fragement = new WishListFragment();
                    fragmentManager.beginTransaction().replace(R.id.flContent, wishlist_fragement).commit();
                    break;
            }
        }
    }

    public void addNavHistoryItem(NavigationHistoryItem item)
    {
        this.navigationHistory.add(item);
    }

    public void loadCategories()
    {
        NetWorkTrans netWorkTrans = new NetWorkTrans(new AsyncResponse() {
            @Override
            public void processFinishWithSuccess(String method, String output) throws JSONException {
                JSONObject outputObj = new JSONObject(output);
                if(outputObj.getInt("success") == 1)
                {
                    Global.categries = outputObj.getJSONArray("data");

                    for (int i = 0;i< Global.categries.length();i++)
                    {
                        JSONObject tempCategory = Global.categries.getJSONObject(i);
                        NavigationMenuItem temp = new NavigationMenuItem(tempCategory.getString("name"));
                        temp.category_data = tempCategory;
                        Global.navigationListArray.add(temp);
                    }

                    navigationListView = (ListView)findViewById(R.id.navigation_listview);
                    NavigationMenuItemAdapter navigationMenuItemAdapter = new NavigationMenuItemAdapter(ShoppingListActivity.this, Global.navigationListArray);
                    navigationListView.setAdapter(navigationMenuItemAdapter);

                    navigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.END);
                            if(position == 0)
                            {
                                MyProfileFragment main_fragement = new MyProfileFragment();
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.flContent, main_fragement).commit();

                                NavigationHistoryItem item1= new NavigationHistoryItem("myprofile");
                                navigationHistory.add(item1);
                            }
                            else {
                                Bundle bundle = new Bundle();
                                bundle.putInt("position", position);
                                ShoppingListFragement shopping_fragement = new ShoppingListFragement();
                                shopping_fragement.setArguments(bundle);
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.flContent, shopping_fragement).commit();


                                NavigationHistoryItem item1= new NavigationHistoryItem("search");
                                item1.itemPosition = position;
                                navigationHistory.add(item1);
                            }
                        }

                    });

                }
                else if(outputObj.getInt("success") == 0)
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShoppingListActivity.this);
                    alertDialogBuilder.setMessage(outputObj.getString("error"));

                    alertDialogBuilder.setNegativeButton("Close",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

            }

            @Override
            public void processFinishWithError(String method) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShoppingListActivity.this);
                alertDialogBuilder.setMessage("NetWork Error");
                alertDialogBuilder.setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        HashMap<String, Object> params;
        params = new HashMap<>();
        params.put("method","get");
        params.put("url", Global.api_prefix_oauth+Global.categories_url);
        params.put("is_get_access_token","no");
        netWorkTrans.execute(params);
    }


}
