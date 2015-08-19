package com.zarate.jesus.drinkwater.Graph;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.zarate.jesus.drinkwater.R;

import java.util.ArrayList;

/**
 * Created by Jesus Zarate on 8/17/15.
 */
public class GraphAdapter implements ListAdapter
{
    ArrayList<String> graphs = new ArrayList<>();

    public GraphAdapter(){
        graphs.add("Scatter");
        graphs.add("Pie");
        graphs.add("Bar");
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer)
    {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer)
    {

    }

    @Override
    public int getCount()
    {
        return graphs.size();
    }

    @Override
    public Object getItem(int position)
    {
        return graphs.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout rootLayout = new LinearLayout(parent.getContext());

        ImageView imageView = new ImageView(parent.getContext());
        imageView.setBackgroundResource(R.drawable.common_signin_btn_icon_dark);

        TextView textView = new TextView(parent.getContext());
        textView.setText(graphs.get(position));
        textView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        textView.setTextColor(Color.WHITE);

        rootLayout.addView(imageView);
        rootLayout.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return rootLayout;
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        return graphs.isEmpty();
    }
}
