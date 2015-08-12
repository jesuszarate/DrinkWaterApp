package com.zarate.jesus.drinkwater;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Jesus Zarate on 8/1/15.
 */
public class WaterConsumptionHistory
{
    private static WaterConsumptionHistory instance = new WaterConsumptionHistory();

    public static WaterConsumptionHistory getInstance()
    {
        return instance;
    }

    private WaterConsumptionHistory()
    {
    }

    String _currentWeek;
    HashMap<String, Week> _weeks = new HashMap<>();
    String[] MONTHS = new String[]
            {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            };
    String[] DAYS_OF_WEEK = new String[]
            {
                    "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
            };

    public String getCurrentWeek()
    {
        return _currentWeek;
    }

    public void addDay(int waterAmount)
    {
        Calendar calendar = Calendar.getInstance();
        int index = calendar.get(Calendar.MONTH);

        String month = MONTHS[index];

        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.US);

        weekDay = dayFormat.format(calendar.getTime());

        int date = calendar.get(Calendar.DATE);
        if (weekDay.equals("Mon"))
        {
            _currentWeek = month + " " + date;
            Week week = new Week();
            _weeks.put(_currentWeek, week);
        }

        Day day = new Day();
        day.dayOfWeek = weekDay;
        day.date = date;
        day.month = month;
        day.year = calendar.get(Calendar.YEAR);
        day.waterAmount = waterAmount;

        if (!_weeks.containsKey(_currentWeek))
        {
            // Get this weeks Monday
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            month = MONTHS[calendar.get(Calendar.MONTH)];
            date = calendar.get(Calendar.DATE);
            _currentWeek = month + " " + date;

            Week week = new Week();
            _weeks.put(_currentWeek, week);

        }
        _weeks.get(_currentWeek).week.put(weekDay, day);
    }

    // Only works with current week for now.
    public int getDayWaterAmount(int DayOfWeek)
    {
        String day = DAYS_OF_WEEK[DayOfWeek];
        return _weeks.get(_currentWeek).week.get(day).waterAmount;
    }

    class Day
    {
        public String dayOfWeek;
        public int waterAmount;
        public String month;
        public int year;
        public int date;
    }

    /**
     * Consists of the seven days of the week
     */
    class Week
    {
        HashMap<String, Day> week = new HashMap<>();
    }

    /**
     * Consists of four weeks
     */
    class Month
    {
        Week[] month;
    }

    class Year
    {
        HashMap<String, Month> year;
    }
}
