package com.zarate.jesus.drinkwater;

import java.util.HashMap;

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

    class Day
    {
        public String day;
        public int dayAmount;
        public int month;
        public int year;
        public int date;
    }

    /**
     * Consists of the seven days of the week
     */
    class Week
    {
        HashMap<String, Day> week;
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
