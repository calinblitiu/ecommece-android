package com.it.spot.spotit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rubby on 10/16/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button login_btn;
    TextView signup_btn;
    EditText  email_edittext;
    EditText password_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        this.login_btn = (Button)findViewById(R.id.login_login_btn);
        this.login_btn.setOnClickListener(this);

        this.signup_btn = (TextView) findViewById(R.id.login_signup_btn);
        this.signup_btn.setOnClickListener(this);

        this.email_edittext = (EditText)findViewById(R.id.login_email);
        this.password_edittext = (EditText)findViewById(R.id.login_password);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_signup_btn)
        {
            Intent signup_intent = new Intent(LoginActivity.this, SignupActivity.class);
            this.startActivity(signup_intent);
        }
        if(v.getId() == R.id.login_login_btn)
        {
            if(this.checkEmailAndPassword())
            {
                Intent shoppinglist_intent = new Intent(this, ShoppingListActivity.class);
                startActivity(shoppinglist_intent);
            }
        }
    }

    public Boolean checkEmailAndPassword()
    {
        String email = this.email_edittext.getText().toString();
        String password = this.password_edittext.getText().toString();

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

        return true;
    }
}
