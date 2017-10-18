package com.it.spot.spotit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by rubby on 10/16/2017.
 */

public class ShoppingListActivity extends AppCompatActivity
{
    ListView navigationListView;
    ArrayList<NavigationMenuItem> navigationListArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shoppinglist);

        this.navigationListArray = new ArrayList<NavigationMenuItem>();

        NavigationMenuItem test_item1 = new NavigationMenuItem("woman");
        NavigationMenuItem test_item2 = new NavigationMenuItem("woman");
        NavigationMenuItem test_item3 = new NavigationMenuItem("woman");
        this.navigationListArray.add(test_item1);
        this.navigationListArray.add(test_item2);
        this.navigationListArray.add(test_item3);

        this.navigationListView = (ListView)findViewById(R.id.navigation_listview);
        NavigationMenuItemAdapter navigationMenuItemAdapter = new NavigationMenuItemAdapter(this, navigationListArray);
        navigationListView.setAdapter(navigationMenuItemAdapter);
    }
}
