package com.example.qaiser.test;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Qaiser on 4/7/2016.
 */
public class CameraService2 extends BroadcastReceiver {







    public final String TAG = "CRAR"; // CheckRunningApplicationReceiver
    public boolean hh;


    public static class Global{
        public static int counter=0;
        public static String myappname;
        public static String mydatalog;
    }



    // final int startupid = 2222222;
    // public String label;
    // public int ss=0;
//    DataBaseHelper myDB;
    String fd;
    @Override
    public void onReceive(Context aContext, Intent anIntent) {
  //      myDB=new DataBaseHelper(aContext);

        try {


//get a list of installed apps.


            ActivityManager am = (ActivityManager) aContext.getSystemService(Context.ACTIVITY_SERVICE);

            List<ActivityManager.RunningAppProcessInfo> alltasks = am.getRunningAppProcesses();

            //

                for (final ActivityManager.RunningAppProcessInfo aTask : alltasks) {
                   Global.myappname= alltasks.get(0).processName;




                    // Used to check for CALL screen

                    if (Global.myappname.equals(MainActivity.myGlobal.text)) {
                        // When user on call screen show a alert message
                        hh = true;
                     //   Toast.makeText(aContext, "Equal", Toast.LENGTH_LONG).show();
                     //   Global.appname2=aTask.topActivity.getClassName();

                        Global.counter++;
                        break;



                        //
                        // continue;
                    }
                    else if (Global.myappname!=(MainActivity.myGlobal.text))
                    {
                        Global.counter=0;
                    }
                    else
                    {

                    }



                    // These are showing current running activity in logcat with
                    // the use of different methods
                    Calendar c = Calendar.getInstance();

                    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //fd = df.format(c.getTime().toString());


                    Global.mydatalog=alltasks.get(0).processName.toString();
                  //  int inserted=myDB.logdata(Global.mydatalog,fd);
                    Log.i(this.TAG, "===============================");

                    Log.i("current_app",alltasks.get(0).processName);


                }














         if (hh == true && Global.counter==1) {
                    //Toast.makeText(aContext, "Camera Screen.", Toast.LENGTH_LONG).show();
                    // startService(new Intent(getBaseContext(), SMSClass.class));
                    Intent in = new Intent(aContext, SMSClass.class);
                    //in.putExtra("interval",5001);
                    aContext.startService(in);


                    // hh = false;
                }
                else
                {
                    // Toast.makeText(aContext, "Counter.", Toast.LENGTH_LONG).show();

                }



        } catch (Throwable t) {
            Log.i(this.TAG, "Throwable caught: "
                    + t.getMessage(), t);
        }














    }







}
