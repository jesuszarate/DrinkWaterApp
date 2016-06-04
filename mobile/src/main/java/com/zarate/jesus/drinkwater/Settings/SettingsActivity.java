package com.zarate.jesus.drinkwater.Settings;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zarate.jesus.drinkwater.CustomButtons.RoundButton;
import com.zarate.jesus.drinkwater.R;
import com.zarate.jesus.drinkwater.SavingAndLoadingState.SavingAndLoading;
import com.zarate.jesus.drinkwater.Settings.StartEndTime.StartEndTimeActivity;
import com.zarate.jesus.drinkwater.TransparentLinearLayout;
import com.zarate.jesus.drinkwater.User;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Jesus Zarate on 8/1/15.
 */
public class SettingsActivity extends Activity
{
    public final static int START_TIME_REQUEST_CODE = 13;

    EditText weightInput;
    TextView totalWaterNeeded;
    EditText waterNeededInput;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TransparentLinearLayout rootLayout = new TransparentLinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        rootLayout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        rootLayout.setFocusableInTouchMode(true);

        LinearLayout emptySlot = new LinearLayout(this);

        // User's weight
        LinearLayout userWeightSection = new LinearLayout(this);
        final TextView userWeight = new TextView(this);
        userWeight.setText("Weight");
        userWeight.setTextColor(Color.WHITE);
        userWeight.setTypeface(Typeface.DEFAULT_BOLD);
        weightInput = new EditText(this);
        weightInput.setText((int)User.getInstance().getWeight() + "");
        weightInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        //weightInput.setFocusable(false);
        weightInput.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                try
                {
                    double inputWeight = Double.parseDouble(weightInput.getText().toString());
                    double currWeight = User.getInstance().getWeight();

                    if(currWeight != inputWeight && inputWeight > 0)
                    {
                        User.getInstance().setTotalWaterNeeded(Integer.parseInt(weightInput.getText().toString()) / 2);

                        if (!weightInput.getText().toString().isEmpty())
                            waterNeededInput.setText(User.getInstance().getTotalWaterNeeded() + "");
                    }
                    else if(inputWeight <= 0)
                    {
                        weightInput.setText((int)currWeight + "");
                        Toast.makeText(SettingsActivity.this,
                                "Weight must be greater 0", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e)
                {
                    Log.e("Weight onFocusChange", e.toString());
                }
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);
        userWeightSection.addView(userWeight, params);
        userWeightSection.addView(weightInput, params);

        // Total Water needed Section
        LinearLayout totalWaterNeededSection = new LinearLayout(this);
        totalWaterNeeded = new TextView(this);
        totalWaterNeeded.setText("Total Water Needed");
        totalWaterNeeded.setTypeface(Typeface.DEFAULT_BOLD);
        totalWaterNeeded.setInputType(InputType.TYPE_CLASS_NUMBER);
        totalWaterNeeded.setTextColor(Color.WHITE);
        waterNeededInput = new EditText(this);
        waterNeededInput.setText(User.getInstance().getTotalWaterNeeded() + "");
        waterNeededInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);
        totalWaterNeededSection.addView(totalWaterNeeded, params);
        totalWaterNeededSection.addView(waterNeededInput, params);

        // Cup Size Section
        LinearLayout cupSizeSection = new LinearLayout(this);
        final TextView cupSize = new TextView(this);
        cupSize.setText("Cup Size");
        cupSize.setTypeface(Typeface.DEFAULT_BOLD);
        cupSize.setTextColor(Color.WHITE);
        final EditText cupSizeInput = new EditText(this);
        cupSizeInput.setText(User.getInstance().getCupSize() + "");
        cupSizeInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);
        cupSizeSection.addView(cupSize, params);
        cupSizeSection.addView(cupSizeInput, params);

        // Start Time Section
        LinearLayout startTimeSection = new LinearLayout(this);
        final TextView startTime = new TextView(this);
        startTime.setText("Start Time");
        startTime.setTypeface(Typeface.DEFAULT_BOLD);
        startTime.setTextColor(Color.WHITE);

        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);

        RoundButton startTimeButton = new RoundButton(this);
        startTimeButton.setPadding(20,20,20,20);
        startTimeButton.setElevation(20);

        StringBuilder time = new StringBuilder()
                .append(User.getInstance().getStartTimeHour())
                .append(":")
                .append(User.getInstance().getStartTimeMinString())
                .append("\n")
                .append(User.getInstance().getStartTimeMeridiem());

        startTimeButton.setText(time.toString());

        startTimeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent setTimeIntent = new Intent(SettingsActivity.this, StartEndTimeActivity.class);
                startActivity(setTimeIntent);
            }
        });

        startTimeSection.addView(startTime, params);
        startTimeSection.addView(startTimeButton, params);


        // End time section
        LinearLayout endTimeSection = new LinearLayout(this);

        RoundButton endTimeButton = new RoundButton(this);
        endTimeButton.setPadding(20,20,20,20);
        endTimeButton.setElevation(20);

        time = new StringBuilder()
                .append(User.getInstance().getEndTimeHour())
                .append(":")
                .append(User.getInstance().getEndTimeMinString())
                .append("\n")
                .append(User.getInstance().getEndTimeMeridiem());

        endTimeButton.setText(time.toString());

        startTimeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent setTimeIntent = new Intent(SettingsActivity.this, StartEndTimeActivity.class);
                startActivity(setTimeIntent);
                startActivityForResult(setTimeIntent, START_TIME_REQUEST_CODE);
            }
        });

        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);

        final TextView endTime = new TextView(this);
        endTime.setText("End Time");
        endTime.setTypeface(Typeface.DEFAULT_BOLD);
        endTime.setTextColor(Color.WHITE);

        endTimeSection.addView(endTime, params);
        endTimeSection.addView(endTimeButton, params);



        //startTimeSection.addView(startTimeButton);
        //startTimeSection.addView(endTimeButton);

        // TODO: Make an activity that will allow user to set the start and end time
        //TimePicker startTimePicker = new TimePicker(this);
        //startTimeSection.addView(startTimePicker);

        //startTimeSection.addView(startTime, params);
        //startTimeSection.addView(startTimeInput, params);



        // Reminder Frequency Section
        /*
        LinearLayout reminderFrequencySection = new LinearLayout(this);
        final TextView reminderFrequency = new TextView(this);
        reminderFrequency.setText("Reminder Frequency");
        reminderFrequency.setTypeface(Typeface.DEFAULT_BOLD);
        reminderFrequency.setTextColor(Color.WHITE);
        final Spinner reminderSpinner = new Spinner(this);

        ArrayAdapter<String> adapter;
        String[] arraySpinner = getResources().getStringArray(R.array.time);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        reminderSpinner.setAdapter(adapter);

        HashMap<Integer, Integer> map = User.getInstance().getTimeLengths();
        int position = map.get(User.getInstance().getReminderTime());
        reminderSpinner.setSelection(position, true);

        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(20, 0, 20, 0);
        reminderFrequencySection.addView(reminderFrequency, params);
        reminderFrequencySection.addView(new Space(this), params);
        reminderFrequencySection.addView(reminderSpinner, params);
        */

        // Submit/Cancel Section
        LinearLayout submitSection = new LinearLayout(this);
        RoundButton submitButton = new RoundButton(this);
        submitButton.setPadding(20,20,20,20);
        submitButton.setElevation(20);
        submitButton.setText("Submit");
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if (weightInput != null && !weightInput.getText().toString().isEmpty())
                    {
                        double d = Double.parseDouble(weightInput.getText().toString());
                        User.getInstance().setWeight(d);
                    }
                        //User.getInstance().setWeight(Double.parseDouble(weightInput.getText().toString()));
                    if (cupSizeInput != null && !cupSizeInput.getText().toString().isEmpty())
                        User.getInstance().setCupSize(Integer.parseInt(cupSizeInput.getText().toString()));
                    if (waterNeededInput != null && !waterNeededInput.getText().toString().isEmpty())
                        User.getInstance().setTotalWaterNeeded(Integer.parseInt(waterNeededInput.getText().toString()));

                    // Time Frequency
                    //int time = Integer.parseInt(reminderSpinner.getSelectedItem().toString());
                    //User.getInstance().setReminderTime(time);

                    finish();
                } catch (Exception e)
                {
                    Log.e("Submit Button", e.toString());
                }
            }
        });

        RoundButton cancelButton = new RoundButton(this);
        cancelButton.setPadding(20,20,20,20);
        cancelButton.setElevation(20);
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        cancelButton.setText("Cancel");

        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 2));
        submitSection.addView(cancelButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 3));
        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 4));
        submitSection.addView(submitButton, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 3));
        submitSection.addView(new Space(this), new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 3));

        // Add to rootLayout
        rootLayout.addView(emptySlot, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));
        rootLayout.addView(userWeightSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(totalWaterNeededSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(cupSizeSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(startTimeSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));
        rootLayout.addView(endTimeSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        // Time frequency
        //rootLayout.addView(reminderFrequencySection, new LinearLayout.LayoutParams(
        //        ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        rootLayout.addView(submitSection, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 10));

        setContentView(rootLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == START_TIME_REQUEST_CODE){
                Toast.makeText(this, "start time", Toast.LENGTH_SHORT).show();
            }
        }
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
