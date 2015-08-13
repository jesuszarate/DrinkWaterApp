package com.zarate.jesus.drinkwater.WaterConsumptionHistory;

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
    private static WaterConsumptionHistory _instance;

    public static WaterConsumptionHistory getInstance()
    {
        if(_instance == null)
        {
            _instance = new WaterConsumptionHistory();
        }
        return _instance;
    }

    public static void setInstance(WaterConsumptionHistory instance)
    {
        _instance = instance;
    }

    private WaterConsumptionHistory()
    {
    }

    String _currentWeekTagName;
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

    public String[] getDAYS_OF_WEEK()
    {
        return DAYS_OF_WEEK;
    }

    public String getCurrentWeekTagName()
    {
        return _currentWeekTagName;
    }

    public ArrayList<Day> getCurrentWeek()
    {
        if(_weeks.containsKey(getCurrentWeekTagName()))
            return _weeks.get(getCurrentWeekTagName()).week;

        return null;
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
            _currentWeekTagName = month + " " + date;
            Week week = new Week();
            _weeks.put(_currentWeekTagName, week);
        }

        Day day = new Day();
        day.dayOfWeek = getDayOfWeek(weekDay);
        day.date = date;
        day.month = month;
        day.year = calendar.get(Calendar.YEAR);
        day.waterAmount = waterAmount;

        if (!_weeks.containsKey(_currentWeekTagName))
        {
            // Get this weeks Monday
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            month = MONTHS[calendar.get(Calendar.MONTH)];
            date = calendar.get(Calendar.DATE);
            _currentWeekTagName = month + " " + date;

            Week week = new Week();
            _weeks.put(_currentWeekTagName, week);

        }
        _weeks.get(_currentWeekTagName).week.add(day);
        //_weeks.get(_currentWeekTagName).week.put(weekDay, day);
    }

    public int getDayOfWeek(String day)
    {
        for(int i = 0; i < DAYS_OF_WEEK.length; i++)
        {
            if(DAYS_OF_WEEK[i].equals(day))
            {
                return i;
            }
        }
        return -1;
    }

    // Only works with current week for now.
    public int getDayWaterAmount(int DayOfWeek)
    {
        String day = DAYS_OF_WEEK[DayOfWeek];
        return _weeks.get(_currentWeekTagName).week.get(DayOfWeek).waterAmount;
        //return _weeks.get(_currentWeekTagName).week.get(day).waterAmount;
    }

    /**
     * Consists of the seven days of the week
     */
    class Week
    {
        ArrayList<Day> week = new ArrayList<>();
        //HashMap<String, Day> week = new HashMap<>();
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
