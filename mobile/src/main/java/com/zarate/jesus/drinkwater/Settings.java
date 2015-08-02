package com.zarate.jesus.drinkwater;

/**
 * Created by Jesus Zarate on 7/25/15.
 * TODO:   Make this a singleton
 */
public class Settings
{
    private static Settings instance = new Settings();
    public static Settings getInstance(){
        return instance;
    }
    private Settings (){}

    private float reminderTime; // In seconds
    private int totalWaterNeeded = 100;

    private int cupSize = 20;
    private MeasurementUnits measurementUnit;

    private enum MeasurementUnits{
        ENGLISH,
        METRIC
    }

    public int getCupSize()
    {
        return cupSize;
    }

    public void setCupSize(int cupSize)
    {
        this.cupSize = cupSize;
    }

    public float getReminderTime()
    {
        return reminderTime;
    }

    public void setReminderTime(float reminderTime)
    {
        this.reminderTime = reminderTime;
    }

    public int getTotalWaterNeeded()
    {
        return totalWaterNeeded;
    }

    public void setTotalWaterNeeded(int totalWaterNeeded)
    {
        this.totalWaterNeeded = totalWaterNeeded;
    }

    public MeasurementUnits getMeasurementUnit()
    {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnits measurementUnit)
    {
        this.measurementUnit = measurementUnit;
    }
}
