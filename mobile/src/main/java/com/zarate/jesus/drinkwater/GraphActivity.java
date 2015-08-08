package com.zarate.jesus.drinkwater;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.zarate.jesus.drinkwater.R;

public class GraphActivity extends Activity
{
    private DrawerLayout _drawerLayout;
    private ListView _listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        _drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        _listView = (ListView) findViewById(R.id.left_drawer);


    }
}
