package com.zarate.jesus.drinkwater.Graph;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zarate.jesus.drinkwater.Alarm.AlarmReceiverActivity;
import com.zarate.jesus.drinkwater.Alarm.AlarmStarter;
import com.zarate.jesus.drinkwater.R;

import java.util.Calendar;

public class GraphActivity extends Activity
{
    private DrawerLayout _drawerLayout;
    private ListView _listView;
    private android.support.v4.app.ActionBarDrawerToggle _drawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // This is midnight -> 0 hour 0 minute 0 second
        AlarmStarter.setAlarmByHour(this, 12345, 2, 30, 0);

        _drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        _listView = (ListView) findViewById(R.id.left_drawer);

        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.content_Layout);
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        textView.setText("August 02 - August 09");
        textView.setTextSize(25);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setBackgroundColor(Color.parseColor("#009788"));
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        contentLayout.addView(textView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        GraphView graphView = new GraphView(this);
        contentLayout.addView(graphView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 90));

//        PiGraph piGraph = new PiGraph(this);
//        contentLayout.addView(piGraph, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));


        _drawerListener = new android.support.v4.app.ActionBarDrawerToggle(GraphActivity.this, _drawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
            }
        };


        _drawerLayout.setDrawerListener(_drawerListener);
        try
        {
            getActionBar().setHomeButtonEnabled(true);

            getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e)
        {
            Log.e("action bar", e.toString());
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        _drawerListener.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(_drawerListener.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        _drawerListener.onConfigurationChanged(newConfig);
    }
}
