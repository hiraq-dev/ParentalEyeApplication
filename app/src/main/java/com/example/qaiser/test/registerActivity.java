package com.example.qaiser.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends ActionBarActivity {
    public static class registration{
        public static String Rpass;
        public static boolean Rflag=false;
    }

    DataBaseHelper db;
static final String tagg="";
    EditText regpass;
    Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            db = new DataBaseHelper(this);
            regpass = (EditText) findViewById(R.id.editText4);
            regist = (Button) findViewById(R.id.button3);

            Cursor rst = db.registeredData();
            if(rst.getCount()==0)
            {
                registration.Rflag=false;
            }
            else
            {
                registration.Rflag=true;
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }



            regist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {





                    registration.Rpass = regpass.getText().toString();
                    //  ss=regpass.getText().toString();
                    boolean isInserted = db.registeradd(regpass.getText().toString());


                    if (isInserted == true) {
                        Toast.makeText(registerActivity.this, "Password Set", Toast.LENGTH_SHORT).show();
                        Cursor rst = db.registeredData();
                        if(rst.getCount()==0)
                        {
                            registration.Rflag=false;
                        }
                        else
                        {
                            registration.Rflag=true;
                        }
                    } else {
                        Toast.makeText(registerActivity.this, "Password Not Set", Toast.LENGTH_SHORT).show();
                       // registration.Rflag=false;
                    }
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        catch (Throwable t) {
            Log.i(tagg, "Throwable caught: "
                    + t.getMessage(), t);
        }

    }




}
