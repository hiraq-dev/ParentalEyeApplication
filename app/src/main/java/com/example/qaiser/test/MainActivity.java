package com.example.qaiser.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static class myGlobal{
        public static String text;
        public static String msg;
        public static String number;
        public static String IDTEXT;
        public static String log;
        public static int count=0;

    }
    Context context;
    public final String TAG = "CRAR"; // CheckRunningApplicationReceiver

    Button button1,button2,button3;
    EditText editText1,editText2,editText3;
    Spinner spinner1;
    // ArrayList<String> history=new ArrayList<String>();
    DataBaseHelper myDB;


    int i=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



    private void initialize() {

        // Start receiver with the name StartupReceiver_Manual_Start
        // Check AndroidManifest.xml file
        getBaseContext().getApplicationContext().sendBroadcast(
                new Intent("StartupReceiver_Manual_Start"));
    }



    @Override
    public void onBackPressed() {
        LoginActivity.login.myflag=false;
        super.onBackPressed();
    }

    public void getlist() {
        final PackageManager pm = this.getPackageManager();

//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        final ArrayList<String> packagenames = new ArrayList<String>();
       packagenames.add("Select Application Name");
        if(ViwAllActivity.Global3.flag==true) {
          //  packagenames.add("");
            packagenames.clear();
            packagenames.add(ViwAllActivity.Global3.appname1);
            editText1.setText(ViwAllActivity.Global3.contact1);
            editText2.setText(ViwAllActivity.Global3.message1);
        }


        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));

            String newString = packageInfo.processName.replace("com.", "");
            String newString1 = newString.replace("sec.", "");
            String newString2 = newString1.replace("android.", "");
            String newString3 = newString2.replace("app.", "");
            String newString4 = newString3.replace("widgetapp.", "");
            String newString5 = newString4.replace("google.", "");
            String newString6 = newString5.replace("process.", "");
            String newString7 = newString6.replace("service.", "");

            packagenames.add(newString7);


        }

             final Spinner spinnerview = (Spinner) findViewById(R.id.spinner);

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, packagenames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerview.setAdapter(adapter);
          //  spinner2.setAdapter(adapter);
            spinnerview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    myGlobal.text = parent.getItemAtPosition(pos).toString();
                    // Toast.makeText(getApplicationContext(),"You Selected "+ myGlobal.text,Toast.LENGTH_LONG).show();
                }

                public void onNothingSelected(AdapterView<?> parent) {
                  //  packagenames.add("Select Application Name");
                }
            });







    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);




        if (LoginActivity.login.myflag==true)
        {

        setContentView(R.layout.activity_main);
        myDB = new DataBaseHelper(this);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2=(EditText) findViewById(R.id.editText2);
      //  editText3=(EditText) findViewById(R.id.editText3);
      //  button2=(Button) findViewById(R.id.button2);
        button1 = (Button) findViewById(R.id.button);
       // button3 = (Button) findViewById(R.id.button3);



        getlist();
        addData();
    //    ViewAll();
      //  updateData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
        else
        {

            Startup();
        }
    }


    public void Startup() {
        try {


            if (registerActivity.registration.Rflag==true && LoginActivity.login.myflag == false) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            } else if (LoginActivity.login.myflag == true) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), registerActivity.class);
                startActivity(intent);
            }
        } catch (Throwable t) {
            Log.i(this.TAG, "Throwable caught: "
                    + t.getMessage(), t);
        }

    }



    public void addData() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ViwAllActivity.Global3.flag==true)
                {
                     ViwAllActivity.Global3.flag=false;

                    if(spinner1.getSelectedItem().toString() != "Select Application Name" && editText1.getText().length()!= 0 && editText2.getText().length()!=0)
                    {
                        boolean isUpdate = myDB.UpdateData(ViwAllActivity.Global3.id1, spinner1.getSelectedItem().toString(), editText1.getText().toString(), editText2.getText().toString());

                        if (isUpdate == true) {
                            initialize();
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Please Input Values Properly", Toast.LENGTH_SHORT).show();
                    }



                }


                else {
                    if (spinner1.getSelectedItem().toString()!=("Select Application Name") && editText1.getText().length()!= 0 && editText2.getText().length()!=0)
                    {

                        boolean isInserted = myDB.insertData(spinner1.getSelectedItem().toString(), editText1.getText().toString(), editText2.getText().toString());


                        if (isInserted == true) {
                            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            initialize();
                            myGlobal.count++;
                        } else {
                            Toast.makeText(MainActivity.this, "Not Saved, Try Again", Toast.LENGTH_SHORT).show();
                        }



                    }

                    else{
                        Toast.makeText(MainActivity.this, "Please Input Values Properly", Toast.LENGTH_SHORT).show();
                    }


                }
                if (CameraService2.Global.counter == 1)
                    CameraService2.Global.counter = 0;

                myGlobal.number = editText1.getText().toString();
                myGlobal.msg = editText2.getText().toString();



            }

        });
    }

    public void ViewAll()
    {

 //   button2.setOnClickListener(new View.OnClickListener(){
        //@Override
        //public void onClick(View v) {
       Cursor res= myDB.getAllData();
            if (res.getCount()==0)
            {
                Toast.makeText(MainActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
            }
        else {


                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id: " + res.getString(0) + "\n");
                    buffer.append("appname: " + res.getString(1) + "\n");
                    buffer.append("Number: " + res.getString(2) + "\n");
                    buffer.append("MSG: " + res.getString(3) + "\n");

                    myGlobal.log = buffer.toString();
                    // buffer.append("Date: "+res.getString(4)+"\n");
                }
            }


    }

    public void updateData(){
     //   button1.setOnClickListener(new View.OnClickListener(){
     //       @Override
      //      public void onClick(View v) {
             //   myGlobal.IDTEXT=editText3.getText().toString();
                if(ViwAllActivity.Global3.flag==true)
                {
                  //  ViwAllActivity.Global3.flag=false;
                   // spinner1.setAdapter(adapter);
                    spinner1.setSelection(ViwAllActivity.Global3.appname1.indexOf(0));
                    editText1.setText(ViwAllActivity.Global3.contact1);
                    editText2.setText(ViwAllActivity.Global3.message1);

                //    boolean isUpdate = myDB.UpdateData(editText3.getText().toString(), spinner1.getSelectedItem().toString(), editText1.getText().toString(), editText2.getText().toString());
                 //   if (isUpdate==true)
                //        Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                //    else
                 //       Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }
       //     }
       // });
    }

    public void showmessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(getApplicationContext(),ViwAllActivity.class);
            startActivity(intent);
          // ViewAll();
            return true;

        }


        return super.onOptionsItemSelected(item);

    }

}