package com.it.spot.spotit;


import android.app.DownloadManager;
import android.util.Base64;

import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rubby on 10/26/2017.
 */

public class Global
{
    public static String api_prefix = "http://www.servsne.com/shopit/index.php?route=";
    public static String api_prefix_oauth = "http://www.servsne.com/shopit/api/rest/";
    public static String get_access_token_url = "feed/rest_api/gettoken&grant_type=client_credentials";
    public static String login_url = "login";
    public static String signup_url = "register";
    public static String categories_url = "categories";
    public static String slideshow_url = "slideshows";
    public static String getproducts_url = "products/category/";

    public static String client_id = "shopping_oauth_client";
    public static String client_secret = "shopping_oauth_secret";
    public static String credentialBase64 = "";
    public static String Authorization = "";
    public static String access_token = "";
    public static long expires_in ;
    public static String token_type ="";

    public static JSONObject logedInUserData;
    public static JSONArray categries;
    public static JSONArray slideShows;

    public static ArrayList<NavigationMenuItem> navigationListArray;

    public void Global()
    {

    }

    public static void createCredentalBase64()
    {
        String credentials = Global.client_id+":"+Global.client_secret;
        Global.credentialBase64 = Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT).replace("\n","");
    }
}
