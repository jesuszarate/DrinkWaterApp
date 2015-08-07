package com.zarate.jesus.drinkwater;

/**
 * Created by Jesus Zarate on 7/25/15.
 */
public class User
{
    private static User instance = new User();

    public static User getInstance(){return instance;}

    private User()
    {}

    private String _name;
    private double _weight;
    private String _height;
    private int TotalWaterConsumption = 0;
    private double TotalWaterPercentage = 0;
    private double RemainingWaterConsumption = 0;
    private double _percentagePortion = 0;
    private int reminderTime = 15; // In minutes
    private int totalWaterNeeded = 100;

    private int cupSize = 20;
    private MeasurementUnits measurementUnit;

    private enum MeasurementUnits{
        ENGLISH,
        METRIC
    }

    private ActivityLevel _activityLevel = ActivityLevel.Moderate;

    private enum ActivityLevel{
        Low,
        Moderate,
        High
    }

    public ActivityLevel get_activityLevel()
    {
        return _activityLevel;
    }

    public void set_activityLevel(ActivityLevel _activityLevel)
    {
        this._activityLevel = _activityLevel;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }

    public double get_weight()
    {
        return _weight;
    }

    public void set_weight(double _weight)
    {
        this._weight = _weight;
    }

    public String get_height()
    {
        return _height;
    }

    public void set_height(String _height)
    {
        this._height = _height;
    }


    public int getTotalWaterConsumption()
    {
        return TotalWaterConsumption;
    }

    public void setTotalWaterConsumption(int totalWaterConsumption)
    {
        TotalWaterConsumption = totalWaterConsumption;
    }

    public double getTotalWaterPercentage()
    {
        return TotalWaterPercentage;
    }

    public void setTotalWaterPercentage(double totalWaterPercentage)
    {
        TotalWaterPercentage = totalWaterPercentage;
    }

    public boolean addWater(int height)
    {
        int part = (getTotalWaterNeeded() / getCupSize());
        int amount = height / part;
        _percentagePortion = User.getInstance().getTotalWaterNeeded()/ part;
        TotalWaterPercentage += _percentagePortion;

        //if ((TotalWaterConsumption + amount) < height)
        {
            TotalWaterConsumption += amount;
            return true;
        }
        //return false;
    }

    public boolean removeWater(int height){
        int part = (getTotalWaterNeeded() / getCupSize());
        int amount = height / part;
        _percentagePortion = User.getInstance().getTotalWaterNeeded() / part;


        if ((TotalWaterConsumption) > 0)
        {
            TotalWaterPercentage -= _percentagePortion;
            TotalWaterConsumption -= amount;
            return true;
        }
        return false;
    }

    public int getCupSize()
    {
        return cupSize;
    }

    public void setCupSize(int cupSize)
    {
        this.cupSize = cupSize;
    }

    public int getReminderTime()
    {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime)
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
