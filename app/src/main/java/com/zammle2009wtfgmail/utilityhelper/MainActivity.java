package com.zammle2009wtfgmail.utilityhelper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.BatteryManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity  implements UsageContract.View
{




    private UsageContract.Presenter presenter;

    static String ToReturn = "";





    @Override
    protected void onCreate(Bundle savedInstanceState)

    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);











      //  startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));

        try {
            presenter = new UsagePresenter(this, this);
        }
        catch (Exception e)
        {

        }









        final ImageButton pressedhistory = (ImageButton) findViewById(R.id.history);
        pressedhistory.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View v)
            {
                Intent history = new Intent (MainActivity.this, History.class );
                startActivity(history);
            }

        });

        final ImageButton pressedclean = (ImageButton) findViewById(R.id.clean);
        pressedclean.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent history = new Intent (MainActivity.this, Something1.class );
                startActivity(history);
            }

        });
        final ImageButton pressedwhitelist = (ImageButton) findViewById(R.id.whitelist);
        pressedwhitelist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent history = new Intent (MainActivity.this, WhiteList.class );
                startActivity(history);
            }

        });
        final ImageButton pressednotifications = (ImageButton) findViewById(R.id.notifications);
        pressednotifications.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent history = new Intent (MainActivity.this, Something3.class );
                startActivity(history);
            }

        });
        final ImageButton pressedtimer = (ImageButton) findViewById(R.id.timer);
        pressedtimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent history = new Intent (MainActivity.this, CloseList.class );
                startActivity(history);
            }

        });

    //testing
        final ImageButton presseddetails = (ImageButton) findViewById(R.id.SpecDetail);
        presseddetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent hardWareDetial = new Intent (MainActivity.this, Hardware_Spec.class );
                startActivity(hardWareDetial);
            }

        });


        final ImageButton pressedsettings = (ImageButton) findViewById(R.id.settings);
        pressedsettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent history = new Intent (MainActivity.this, SettingScreen.class );
                startActivity(history);
            }

        });







        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getBaseContext().registerReceiver(null, ifilter);

        // Are we charging / charged?
       final float status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
      final boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
        float batteryPct = level / (float) scale;
        batteryPct = batteryPct * 100;

        // How are we charging?
      //  int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
      //  boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
      //  boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        TextView Text = (TextView) findViewById(R.id.DisplayBatteryPower);


        String mytext = Float.toString(batteryPct);
        Text.setText(mytext + "%");




        


            if (isCharging == true) {

                    ImageView view = (ImageView) findViewById(R.id.charge);

                    view.setVisibility(ImageView.VISIBLE);

            } else {
                ImageView view = (ImageView) findViewById(R.id.charge);

                view.setVisibility(ImageView.INVISIBLE);
            }


            if (batteryPct >= 81.00f) {
                ImageView view = (ImageView) findViewById(R.id.battery5);

                view.setVisibility(ImageView.VISIBLE);

            } else if (batteryPct >= 61.0f) {
                ImageView view = (ImageView) findViewById(R.id.battery4);

                view.setVisibility(ImageView.VISIBLE);
            } else if (batteryPct >= 41.0f) {
                ImageView view = (ImageView) findViewById(R.id.battery3);

                view.setVisibility(ImageView.VISIBLE);
            } else if (batteryPct >= 21.00f) {
                ImageView view = (ImageView) findViewById(R.id.battery2);

                view.setVisibility(ImageView.VISIBLE);
            } else {
                ImageView view = (ImageView) findViewById(R.id.battery1);

                view.setVisibility(ImageView.VISIBLE);
            }







        final TextView text = (TextView) findViewById(R.id.testview);
        text.setText(ToReturn);


        final Button Close = (Button) findViewById(R.id.closebutton);
        Close.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String[] newText = ToReturn.split(System.getProperty("line.separator"));


                for (int i = 0; i < newText.length; i = i + 4)
                {
                    try
                    {

                        if (newText[i+2] != "1" || newText[i+3] != "com.zammle2009wtfgmail.utilityhelper")
                        {
                            final ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                            am.killBackgroundProcesses(newText[i + 3]);
                            Toast.makeText(MainActivity.this,"Closed " + newText[i+3], Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"FAIL", Toast.LENGTH_SHORT).show();
                        }




                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this,"FAIL", Toast.LENGTH_SHORT).show();
                    }





                }


                Toast.makeText(MainActivity.this,"Complete", Toast.LENGTH_SHORT).show();

            }

        });




    }


    @Override
    protected void onResume() {
        try {
            super.onResume();

            presenter.retrieveUsageStats();
        }
        catch (Exception e)
        {}






    }

    /////////////// need these 3 functions to load apps, even though functions are empty. /////////////////////////////////
    @Override
    public boolean onQueryTextChange(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onEditStarted() {

    }

    @Override
    public void onEditFinished() {

    }

    @Override
    public void onUsageStatsRetrieved(List<UsageStatsWrapper> list) {

    }

    @Override
    public void onUserHasNoPermission() {


    }

    private void showProgressBar(boolean show)
    {



    }
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void saveFile(String file, String text)
    {
        try
        {
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);;

            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(MainActivity.this,"Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Error saving file!", Toast.LENGTH_SHORT).show();
        }



    }


    public String readFile (String file)
    {
        String textread = "";

        try
        {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            textread = new String(buffer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //  Toast.makeText(CloseList.this,"Error reading file!", Toast.LENGTH_SHORT).show();
        }

        return textread;
    }


}