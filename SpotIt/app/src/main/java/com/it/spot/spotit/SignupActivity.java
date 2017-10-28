package com.it.spot.spotit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by rubby on 10/16/2017.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    TextView login_btn;
    Button signup_btn;
    EditText email_edittext;
    EditText password_edittext;
    EditText repassword_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

//        this.login_btn = (TextView)findViewById(R.id.signup_login_btn);
//        this.login_btn.setOnClickListener(this);

        this.signup_btn = (Button)findViewById(R.id.singup_signup_btn);
        this.signup_btn.setOnClickListener(this);

        this.email_edittext = (EditText)findViewById(R.id.signup_email_edittext);
        this.password_edittext = (EditText)findViewById(R.id.signup_password_edittext);
        this.repassword_edittext = (EditText)findViewById(R.id.signup_repassword_edittext);

    }

    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.signup_login_btn)
//        {
//            this.finish();
//        }

        if(v.getId() == R.id.singup_signup_btn)
        {
            if (this.checkEmailAndPassword())
            {
                NetWorkTrans netWorkTrans = new NetWorkTrans(new com.it.spot.spotit.AsyncResponse() {
                    @Override
                    public void processFinishWithSuccess(String method, String output) throws JSONException {
                        JSONObject outputObj = new JSONObject(output);
                        if(outputObj.getInt("success") == 1)
                        {
//                            Global.logedInUserData = outputObj.getJSONObject("data");
//                            Intent shoppinglist_intent = new Intent(SignupActivity.this, ShoppingListActivity.class);
//                            startActivity(shoppinglist_intent);
                            finish();
                        }
                        else if(outputObj.getInt("success") == 0)
                        {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignupActivity.this);
                            alertDialogBuilder.setMessage("Please Input Login Info Correctly!");

                            alertDialogBuilder.setNegativeButton("Close",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // finish();
                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void processFinishWithError(String method) {

                    }
                });

                HashMap<String, Object> params;
                params = new HashMap<>();
                params.put("method","post");
                params.put("is_get_access_token","no");
                params.put("url", Global.api_prefix_oauth+Global.signup_url);
                params.put("email",email_edittext.getText().toString());
                params.put("password", password_edittext.getText().toString());
                params.put("firstname", "Demo");
                params.put("lastname", "Demo");
                params.put("confirm", password_edittext.getText().toString());
                params.put("telephone", "1-555-555-5555");
                params.put("fax", "1-555-555-5555");
                params.put("company", "Demo");
                params.put("City", "Berlin");
                params.put("address_1", "Demo");
                params.put("address_2", "Demo");
                params.put("country_id", "81");
                params.put("postcode", "5555");
                params.put("zone_id", "1234");
                params.put("tax_id", "1");
                params.put("agree", "1");

                netWorkTrans.execute(params);
            }
        }
    }

    public Boolean checkEmailAndPassword()
    {
        String email = this.email_edittext.getText().toString();
        String password = this.password_edittext.getText().toString();
        String repassword = this.repassword_edittext.getText().toString();

        if(email.equals(""))
        {
            Toast.makeText(this, "Please Input Email Address!",Toast.LENGTH_LONG).show();
            return  false;
        }

        if(password.equals(""))
        {
            Toast.makeText(this, "Please Input Password!",Toast.LENGTH_LONG).show();
            return  false;
        }

        if (repassword.equals(""))
        {
            Toast.makeText(this, "Please Input Re-Password!",Toast.LENGTH_LONG).show();
            return  false;
        }

        if(!password.equals(repassword))
        {
            Toast.makeText(this, "Please Input Password and Re-Password Same!",Toast.LENGTH_LONG).show();
            return  false;
        }

        return true;
    }
}
