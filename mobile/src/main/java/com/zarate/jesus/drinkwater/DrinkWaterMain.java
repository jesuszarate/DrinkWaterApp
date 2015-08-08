package com.zarate.jesus.drinkwater;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.Space;

import com.zarate.jesus.drinkwater.CustomButtons.RoundButton;
import com.zarate.jesus.drinkwater.CustomButtons.RoundTextView;
import com.zarate.jesus.drinkwater.SavingAndLoadingState.SavingAndLoading;
import com.zarate.jesus.drinkwater.Settings.SettingsActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


/**
 * TODO:
 */
public class DrinkWaterMain extends Activity
{
    // Last drink
    String _lastTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

    NotificationCompat.Builder _mBuilder;
    AlphaAnimation _buttonClick = new AlphaAnimation(1F, 0.3F);

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
        LinearLayout settingSection = new LinearLayout(this);

        RoundButton settings = new RoundButton(this);
        settings.set_image(R.mipmap.ic_menu_preferences);
        settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
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

        settingSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        settingSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        settingSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        settingSection.addView(settings, new LinearLayout.LayoutParams(0,
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
        LinearLayout WaterButtonSection = new LinearLayout(this);
        RoundButton AddButton = new RoundButton(this);
        RoundButton RemoveButton = new RoundButton(this);

        WaterButtonSection.addView(new View(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        WaterButtonSection.addView(RemoveButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 10));
        WaterButtonSection.addView(new View(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 20));
        WaterButtonSection.addView(AddButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 10));
        WaterButtonSection.addView(new View(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 10));

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
                v.startAnimation(_buttonClick);

                rootLayout.removeWater();
                roundButton.invalidate();
            }
        });

        rootLayout.addView(new LinearLayout(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        rootLayout.addView(settingSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));

        rootLayout.addView(emptySpace, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 5));

        rootLayout.addView(percentageSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 15));

        rootLayout.addView(WaterButtonSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 5));

        setContentView(rootLayout);

        timer.schedule(new SwitchPlayerTask(), getMinutes(User.getInstance().getReminderTime()));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        File filesDir = getFilesDir();
        SavingAndLoading.LoadState(filesDir);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        File filesDir = getFilesDir();
        SavingAndLoading.SaveState(filesDir);
    }

    private void showNotification()
    {
        String current = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        _mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_signin_btn_icon_dark)
                        .setContentTitle("Drink Water")
                        .setContentText("Last time: " + _lastTime + " Now " + current);
                                //+ current + "Drinking water is good for your health");

        _lastTime = current;

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

    class SwitchPlayerTask extends TimerTask
    {
        public void run()
        {
            showNotification();
            timer.schedule(new SwitchPlayerTask(), getMinutes(User.getInstance().getReminderTime()));
        }
    }

    private long getMinutes(int minutes)
    {
        int second = 1000;
        int minute = 60 * second;
        return minutes * minute;
    }

}