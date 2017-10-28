package com.it.spot.spotit;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Global.createCredentalBase64();
        NetWorkTrans netWorkTrans = new NetWorkTrans(new AsyncResponse() {
            @Override
            public void processFinishWithSuccess(String method, String output) throws JSONException {
                JSONObject outputObj = new JSONObject(output);
                if(outputObj.getInt("success") == 1)
                {
                    JSONObject data = outputObj.getJSONObject("data");
                    Global.access_token = data.getString("access_token");
                    Global.expires_in = data.getLong("expires_in");
                    Global.token_type = data.getString("token_type");
                    Global.Authorization = Global.token_type+" "+Global.access_token;
                    Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
                    MainActivity.this.startActivity(loginIntent);
                    MainActivity.this.finish();
                }
                else if(outputObj.getInt("success") == 0)
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
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
        params.put("method","post");
        params.put("url", Global.api_prefix+Global.get_access_token_url);
        params.put("client_id",Global.client_id);
        params.put("client_secret", Global.client_secret);
        params.put("is_get_access_token","yes");
        netWorkTrans.execute(params);


    }
}
