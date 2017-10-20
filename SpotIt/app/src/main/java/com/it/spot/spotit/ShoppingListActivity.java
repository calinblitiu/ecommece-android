package com.it.spot.spotit;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by rubby on 10/16/2017.
 */

public class ShoppingListActivity extends AppCompatActivity
{
    ListView navigationListView;
    ArrayList<NavigationMenuItem> navigationListArray;
    ImageView menuIconImageView;
    DrawerLayout navigationMenuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shoppinglist);

        MainFragement main_fragement = new MainFragement();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, main_fragement).commit();

        this.navigationListArray = new ArrayList<NavigationMenuItem>();

        NavigationMenuItem test_item1 = new NavigationMenuItem("woman");
        NavigationMenuItem test_item2 = new NavigationMenuItem("man");
        NavigationMenuItem test_item3 = new NavigationMenuItem("child");
        NavigationMenuItem test_item4 = new NavigationMenuItem("decor");
        NavigationMenuItem test_item5 = new NavigationMenuItem("household appliance");
        NavigationMenuItem test_item6 = new NavigationMenuItem("Furniture");
        NavigationMenuItem test_item7 = new NavigationMenuItem("Electrical applicances");
        NavigationMenuItem test_item8 = new NavigationMenuItem("Offers");

        this.navigationListArray.add(test_item1);
        this.navigationListArray.add(test_item2);
        this.navigationListArray.add(test_item3);
        this.navigationListArray.add(test_item4);
        this.navigationListArray.add(test_item5);
        this.navigationListArray.add(test_item6);
        this.navigationListArray.add(test_item7);
        this.navigationListArray.add(test_item8);

        this.navigationListView = (ListView)findViewById(R.id.navigation_listview);
        NavigationMenuItemAdapter navigationMenuItemAdapter = new NavigationMenuItemAdapter(this, navigationListArray);
        navigationListView.setAdapter(navigationMenuItemAdapter);

        this.navigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                    ShoppingListFragement shopping_fragement = new ShoppingListFragement();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.flContent, shopping_fragement).commit();

            }

        });

        //this.navigationMenuView = (DrawerLayout) findViewById(R.id.navigation_menu);

        this.menuIconImageView = (ImageView)findViewById(R.id.menu_icon_image);
        this.menuIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // navigationMenuView.openDrawer(navigationMenuView);
            }
        });
    }
}
