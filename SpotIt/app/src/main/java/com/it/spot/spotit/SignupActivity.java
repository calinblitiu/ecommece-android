package com.it.spot.spotit;

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
