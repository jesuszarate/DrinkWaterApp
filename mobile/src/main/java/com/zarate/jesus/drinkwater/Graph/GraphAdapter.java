package com.zarate.jesus.drinkwater.Graph;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jesus Zarate on 8/17/15.
 */
public class GraphAdapter implements ListAdapter
{
    ArrayList<String> graphs = graphs = new ArrayList<>();

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
        TextView textView = new TextView(parent.getContext());
        textView.setText(graphs.get(position));
        return textView;
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
