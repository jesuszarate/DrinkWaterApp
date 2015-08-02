package com.zarate.jesus.drinkwater.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.zarate.jesus.drinkwater.TransparentLinearLayout;

/**
 * Created by Jesus Zarate on 8/1/15.
 */
public class SettingsActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TransparentLinearLayout rootLayout = new TransparentLinearLayout(this);
        rootLayout.setPadding(100, 300, 100, 300);
        rootLayout.setOrientation(LinearLayout.VERTICAL);


        // Total Water needed Section
        LinearLayout totalWaterNeededSection= new LinearLayout(this);
        final TextView totalWaterNeeded = new TextView(this);
        totalWaterNeeded.setText("Total Water Needed");
        final EditText waterNeededInput = new EditText(this);


        totalWaterNeededSection.addView(totalWaterNeeded, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        totalWaterNeededSection.addView(waterNeededInput, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        // Cup Size Section
        LinearLayout cupSizeSection= new LinearLayout(this);
        final TextView cupSize = new TextView(this);
        cupSize.setText("Cup Size");
        final EditText cupSizeInput = new EditText(this);
        cupSize.setWidth(100);
        cupSizeSection.addView(cupSize, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        cupSizeSection.addView(cupSizeInput, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        // Submit/Cancel Section
        LinearLayout submitSection = new LinearLayout(this);
        Button submitButton = new Button(this);
        submitButton.setText("Submit");
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {


                }catch (Exception e)
                {
                    Log.e("Submit Button", e.toString());
                }
            }
        });
        Button cancelButton = new Button(this);
        cancelButton.setText("Cancel");
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    finish();
                }catch (Exception e)
                {
                    Log.e("Submit Button", e.toString());
                }
            }
        });
        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        submitSection.addView(cancelButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        submitSection.addView(submitButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));


        rootLayout.addView(totalWaterNeededSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(cupSizeSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(submitSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 5));

        setContentView(rootLayout);
    }
}
