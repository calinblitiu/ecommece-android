package com.it.spot.spotit;

import org.json.JSONObject;

/**
 * Created by rubby on 10/18/2017.
 */

public class NavigationMenuItem {
    String item_name;
    JSONObject category_data;

    public NavigationMenuItem(String item_name) {
        this.item_name = item_name;
    }

    public void setCategoryData(JSONObject data)
    {
        this.category_data = data;
    }
    public JSONObject getCategoryData()
    {
        return this.category_data;
    }
}
