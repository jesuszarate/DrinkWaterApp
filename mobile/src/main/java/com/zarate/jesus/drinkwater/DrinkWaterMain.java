package com.zarate.jesus.drinkwater;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;

import com.zarate.jesus.drinkwater.CustomButtons.RoundButton;
import com.zarate.jesus.drinkwater.CustomButtons.RoundTextView;
import com.zarate.jesus.drinkwater.Settings.SettingsActivity;

import java.util.Timer;
import java.util.TimerTask;


/**
 * TODO:
 */
public class DrinkWaterMain extends ActionBarActivity
{
    NotificationCompat.Builder _mBuilder;

    // Sets an ID for the notification
    int mNotificationId = 001;
    Timer timer;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final PaintWater rootLayout = new PaintWater(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        timer = new Timer();

        // Submit Section
        LinearLayout submitSection = new LinearLayout(this);

        RoundButton settings = new RoundButton(this);
        //settings.setText("Settings");
        settings.set_image(R.mipmap.ic_menu_preferences);
        settings.setOnClickListener(new RoundButton.OnClickListener()
        {
            @Override
            public void onClick(RoundButton v)
            {
                try
                {
                    Intent intent = new Intent(DrinkWaterMain.this, SettingsActivity.class);
                    startActivity(intent);

                } catch (Exception e)
                {
                    Log.e("Settings Button", e.toString());
                }
            }
        });

        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        submitSection.addView(settings, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        LinearLayout emptySpace = new LinearLayout(this);

        // Percentage indicator Section
        LinearLayout percentageSection = new LinearLayout(this);
        final RoundTextView roundButton = new RoundTextView(this);
        roundButton.setBackgroundResource(R.drawable.oval_ripple);
        roundButton.setElevation(50);

        percentageSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        percentageSection.addView(roundButton, new LinearLayout.LayoutParams(700,
                700));
        percentageSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        // Empty space
        LinearLayout emptySpace1 = new LinearLayout(this);

        // Water Buttons
        LinearLayout WaterButtonSection= new LinearLayout(this);
        RoundButton AddButton = new RoundButton(this);
        RoundButton RemoveButton = new RoundButton(this);
        WaterButtonSection.addView(RemoveButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        WaterButtonSection.addView(AddButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        AddButton.setText(R.string.add_water);
        AddButton.setTextSize(100);
        RemoveButton.setText(R.string.remove_water);
        RemoveButton.setTextSize(100);

        AddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rootLayout.addWater();
                roundButton.invalidate();
            }
        });

        RemoveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rootLayout.removeWater();
                roundButton.invalidate();
            }
        });

        rootLayout.addView(submitSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));

        rootLayout.addView(emptySpace, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 5));

        rootLayout.addView(percentageSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 15));

        rootLayout.addView(WaterButtonSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 5));

        setContentView(rootLayout);

        timer.schedule(new SwitchPlayerTask(), getMinutes(15));
    }

    private void showNotification()
    {
        _mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_signin_btn_icon_dark)
                        .setContentTitle("Drink Water")
                        .setContentText("Drinking water is good for your health");

        Intent resultIntent = new Intent(this, DrinkWaterMain.class);

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        _mBuilder.setContentIntent(resultPendingIntent);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, _mBuilder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drink_water, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class SwitchPlayerTask extends TimerTask
    {
        public void run()
        {
            showNotification();
            timer.schedule(new SwitchPlayerTask(), getMinutes(15));
        }
    }

    private long getMinutes(int minutes)
    {
        int second = 1000;
        int minute = 60 * second;

        return minutes * minute;
    }
}