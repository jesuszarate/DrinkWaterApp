package com.zarate.jesus.drinkwater.Settings.StartEndTime;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.zarate.jesus.drinkwater.CustomButtons.RoundButton;
import com.zarate.jesus.drinkwater.R;

public class StartEndTimeActivity extends Activity//ActionBarActivity
{

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);


        TimePicker timePicker = new TimePicker(this);


        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 80);
        ll.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        rootLayout.addView(timePicker, ll);


        LinearLayout buttonLayout = new LinearLayout(this);
        RoundButton setButton = new RoundButton(this);
        setButton.setText("Set");
        setButton.setTextSize(60);
        setButton.setElevation(20);

        ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 20);
        ll.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        buttonLayout.addView(setButton);

        rootLayout.addView(buttonLayout, ll);

        setContentView(rootLayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_end_time, menu);
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
}
