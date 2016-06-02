package com.zarate.jesus.drinkwater.Settings.StartEndTime;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zarate.jesus.drinkwater.CustomButtons.RoundButton;
import com.zarate.jesus.drinkwater.R;
import com.zarate.jesus.drinkwater.SavingAndLoadingState.SavingAndLoading;
import com.zarate.jesus.drinkwater.User;

import java.io.File;

public class StartEndTimeActivity extends Activity//ActionBarActivity
{

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);


        final TimePicker timePicker = new TimePicker(this);
        timePicker.setCurrentHour(User.getInstance().getStartTimeHour());
        timePicker.setCurrentMinute(User.getInstance().getStartTimeMin());

        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 80);
        ll.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        rootLayout.addView(timePicker, ll);


        LinearLayout buttonLayout = new LinearLayout(this);
        RoundButton setButton = new RoundButton(this);
        setButton.setText("Set");
        setButton.setTextSize(60);
        setButton.setElevation(20);
        setButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User.getInstance().setStartTime(timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute());
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });

        ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 20);
        ll.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        buttonLayout.addView(setButton);

        rootLayout.addView(buttonLayout, ll);

        setContentView(rootLayout);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        File filesDir = getFilesDir();
        SavingAndLoading.SaveState(filesDir);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        File filesDir = getFilesDir();
        SavingAndLoading.LoadState(filesDir);
    }
}
