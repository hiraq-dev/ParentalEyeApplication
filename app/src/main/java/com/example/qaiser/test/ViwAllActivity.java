package com.example.qaiser.test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ViwAllActivity extends Activity {

    public static class Global3{

        public static String id1;
        public static String appname1;
        public static String contact1;
        public static String message1;
        public static String mystring;
        public static boolean flag=false;
        public static boolean check=false;

    }

    ArrayAdapter<String> adapter;
    ArrayList<String> history = new ArrayList<String>();
    String log;
    Button button4;

    DataBaseHelper db;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viw_all);


/////////////////////////////////////////////////////////////////////////////////////////////
        ListView lv = (ListView) findViewById(R.id.listView);
        Button button4=(Button) findViewById(R.id.button4);
        lv.setBackgroundColor(Color.LTGRAY);
        adapter = new ArrayAdapter<String>(ViwAllActivity.this, android.R.layout.simple_selectable_list_item,history);
        final DataBaseHelper db = new DataBaseHelper(this);
        /////////////////////////////////////////////////////////////////////////////////////




        Cursor res= db.getAllData();
        if (res.getCount()==0)
        {
            Toast.makeText(ViwAllActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
        }
        else {

           // history.clear();
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id: " + res.getString(0) + "\n");
               buffer.append("appname: " + res.getString(1) + "\n");
               buffer.append("Number: " + res.getString(2) + "\n");
               buffer.append("MSG: " + res.getString(3) + "\n");

                String id=res.getString(0)+"\n"+res.getString(1)+"\n"+res.getString(2)+"\n"+res.getString(3)+"\n";
                history.add(id);



                // buffer.append("Date: "+res.getString(4)+"\n");
            }

            lv.setAdapter(adapter);
        }



lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),history.get(position),Toast.LENGTH_SHORT).show();
        Global3.mystring= history.get(position);
        String[] separated= Global3.mystring.split("\\n");
        Global3.id1=separated[0];
        Global3.appname1=separated[1];
        Global3.contact1=separated[2];
        Global3.message1=separated[3];
        Global3.check=true;

    }

});

button4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (Global3.check==false)
        {
            Toast.makeText(ViwAllActivity.this, "Please Select Data to Update", Toast.LENGTH_SHORT).show();
        }
        else {
            Global3.flag = true;
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }

});







    }
}
