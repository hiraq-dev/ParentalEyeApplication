package com.example.qaiser.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

    public static class login{
        public static String pass;
        public static boolean myflag;
        public static String mypass;
        public static String passs;
    }
EditText tlogin;
    Button blogin;
    DataBaseHelper db;

    public void loginnn()
    {
        if (login.myflag==true) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        loginnn();
        setContentView(R.layout.activity_login);

        tlogin=(EditText) findViewById(R.id.editText3);
        blogin=(Button) findViewById(R.id.button2);
        db = new DataBaseHelper(this);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login.pass=tlogin.getText().toString();
                login.mypass=registerActivity.registration.Rpass;
                Cursor rst = db.registeredData();
                StringBuffer buffer = new StringBuffer();
                while (rst.moveToNext()) {
                buffer.append(rst.getString(0));
                }
                login.passs=buffer.toString();
               // StringBuffer buffer = new StringBuffer();

            //  Toast.makeText(LoginActivity.this, login.passs, Toast.LENGTH_SHORT).show();


                if (login.passs.equals(login.pass))
                {
                    login.myflag=true;

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                      }
                else
                {
                    Toast.makeText(LoginActivity.this, "Incorrect Password Try Again", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
