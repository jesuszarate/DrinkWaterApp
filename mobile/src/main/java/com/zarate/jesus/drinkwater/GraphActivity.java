package com.zarate.jesus.drinkwater;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.zarate.jesus.drinkwater.CustomButtons.RoundButton;
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
