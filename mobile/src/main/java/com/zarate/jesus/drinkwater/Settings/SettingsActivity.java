package com.zarate.jesus.drinkwater.Settings;

import android.app.Activity;
import android.graphics.Color;
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
import com.zarate.jesus.drinkwater.User;

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


        // User's weight
        LinearLayout userWeightSection = new LinearLayout(this);
        final TextView userWeight = new TextView(this);
        userWeight.setText("Weight");
        userWeight.setTextColor(Color.WHITE);
        final EditText weightInput = new EditText(this);
        weightInput.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                try
                {
                    User.getInstance().setTotalWaterNeeded(Integer.parseInt(weightInput.getText().toString()));
                }
                catch (Exception e)
                {
                    Log.e("Weight onFocusChange", e.toString());
                }
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 20, 20, 0);
        userWeightSection.addView(userWeight, params);
        userWeightSection.addView(weightInput, params);

        // Total Water needed Section
        LinearLayout totalWaterNeededSection = new LinearLayout(this);
        final TextView totalWaterNeeded = new TextView(this);
        totalWaterNeeded.setText("Total Water Needed");
        totalWaterNeeded.setTextColor(Color.WHITE);
        final EditText waterNeededInput = new EditText(this);
        waterNeededInput.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {

            }
        });
        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);
        totalWaterNeededSection.addView(totalWaterNeeded, params);
        totalWaterNeededSection.addView(waterNeededInput, params);

        // Cup Size Section
        LinearLayout cupSizeSection = new LinearLayout(this);
        final TextView cupSize = new TextView(this);
        cupSize.setText("Cup Size");
        cupSize.setTextColor(Color.WHITE);
        final EditText cupSizeInput = new EditText(this);
        cupSize.setWidth(100);
        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);
        cupSizeSection.addView(cupSize, params);
        cupSizeSection.addView(cupSizeInput, params);

        // Reminder Frequency Section
        LinearLayout reminderFrequencySection = new LinearLayout(this);
        final TextView reminderFrequency = new TextView(this);
        reminderFrequency.setText("Reminder Frequency");
        reminderFrequency.setTextColor(Color.WHITE);
        final EditText reminderFrequencyInput = new EditText(this);
        reminderFrequency.setWidth(100);
        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);
        reminderFrequencySection.addView(reminderFrequency, params);
        reminderFrequencySection.addView(reminderFrequencyInput, params);


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
                    if(!weightInput.getText().toString().isEmpty())
                        User.getInstance().set_weight(Integer.parseInt(weightInput.getText().toString()));
                    if(!cupSizeInput.getText().toString().isEmpty())
                        User.getInstance().setCupSize(Integer.parseInt(cupSizeInput.getText().toString()));
                    if(!waterNeededInput.getText().toString().isEmpty())
                        User.getInstance().setTotalWaterNeeded(Integer.parseInt(waterNeededInput.getText().toString()));

                    finish();
                } catch (Exception e)
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
                } catch (Exception e)
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

        // Add to rootLayout
        rootLayout.addView(userWeightSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(totalWaterNeededSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(cupSizeSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(reminderFrequencySection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(submitSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 5));

        setContentView(rootLayout);
    }
}
