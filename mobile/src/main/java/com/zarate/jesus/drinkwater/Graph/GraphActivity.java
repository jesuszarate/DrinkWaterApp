package com.zarate.jesus.drinkwater.Graph;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zarate.jesus.drinkwater.R;

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

        _drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        _listView = (ListView) findViewById(R.id.left_drawer);

        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.content_Layout);

        GraphView graphView = new GraphView(this);
        contentLayout.addView(graphView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

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
